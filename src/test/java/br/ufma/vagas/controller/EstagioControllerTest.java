package br.ufma.vagas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ufma.vagas.domain.estagio.VinculoEstagio;
import br.ufma.vagas.domain.geral.Aluno;
import br.ufma.vagas.domain.geral.Empresa;
import br.ufma.vagas.service.estagio.EstagioService;

@WebMvcTest(EstagioController.class)
class EstagioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EstagioService estagioService;
	
	private static ObjectMapper mapper;
	
	private static VinculoEstagio vinculoEstagio;	
	
	@BeforeAll
	static void beforeAll() {
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		
		vinculoEstagio = new VinculoEstagio();
		vinculoEstagio.setId(1L);
		vinculoEstagio.setDataInicio(LocalDate.now());
		vinculoEstagio.setDataFim(LocalDate.now().plusYears(1L));
		vinculoEstagio.setEmpresa(new Empresa());
		vinculoEstagio.setAluno(new Aluno());
		
	}

	@Test
	@DisplayName("Deve retornar 200 ao buscar um vínculo de estágio pelo id cadastrado")
	void testObterPorId() throws Exception {
		when(estagioService.obterPorId(1L)).thenReturn(vinculoEstagio);
		
		mockMvc.perform(get("/estagio/1")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(vinculoEstagio)));
	}

	@Test
	@DisplayName("Deve retornar 201 ao salvar um vínculo de estágio")
	void testSalvar() throws Exception {
		when(estagioService.salvar(any(VinculoEstagio.class))).thenReturn(vinculoEstagio);
		
		var novoVinculoEstagio = new VinculoEstagio();
		novoVinculoEstagio.setDataInicio(LocalDate.now());
		novoVinculoEstagio.setDataFim(LocalDate.now().plusYears(1L));
		novoVinculoEstagio.setEmpresa(new Empresa());
		novoVinculoEstagio.setAluno(new Aluno());
		
		mockMvc.perform(post("/estagio")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(novoVinculoEstagio)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(vinculoEstagio)))
                .andExpect(header().string("Location", "http://localhost/estagio/1"));
	}
	
	@Test
	@DisplayName("Deve retornar 400 ao tentar salvar um vínculo de estágio inválido")
	void testNaoSalvar() throws Exception {
		mockMvc.perform(post("/estagio")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new VinculoEstagio())))
                .andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Deve retornar 200 ao editar os dados de um vínculo de estágio")
	void testEditar() throws Exception {
		when(estagioService.editar(any(VinculoEstagio.class))).thenReturn(vinculoEstagio);
		
		mockMvc.perform(put("/estagio")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(vinculoEstagio)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(vinculoEstagio)));
	}

	@Test
	@DisplayName("Deve retornar 200 ao remover um vínculo de estágio da base de dados")
	void testRemover() throws Exception {
		doNothing().when(estagioService).remover(any(VinculoEstagio.class));
		
        mockMvc.perform(delete("/estagio")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(mapper.writeValueAsString(vinculoEstagio)))
                .andExpect(status().isOk());
	}

}
