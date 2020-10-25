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

import br.ufma.vagas.domain.geral.Aluno;
import br.ufma.vagas.domain.geral.Curso;
import br.ufma.vagas.domain.geral.Endereco;
import br.ufma.vagas.domain.geral.Usuario;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.service.geral.AlunoService;

@WebMvcTest(AlunoController.class)
class AlunoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AlunoService alunoService;
	
	private static ObjectMapper mapper;
	
	private static Aluno aluno;
	private static Endereco endereco;
	
	@BeforeAll
	static void beforeAll() {
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
				
		endereco = new Endereco();
		endereco.setCep("65000000");
		
		aluno = new Aluno();
		aluno.setId(1L);
		aluno.setMatricula("100200");
		aluno.setNome("Lorem Ipsum");
		aluno.setDataNascimento(LocalDate.of(2000, 10, 1));
		aluno.setDataIngresso(LocalDate.of(2020, 1, 1));
		aluno.setEndereco(endereco);
		aluno.setCurso(new Curso());
		aluno.setUsuario(new Usuario());
	}

	@Test
	@DisplayName("Deve retornar 200 ao buscar um aluno pelo id cadastrado")
	void testObterPorId() throws Exception {
		when(alunoService.obterPorId(1L)).thenReturn(aluno);
		
		mockMvc.perform(get("/aluno/1")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(aluno)));
	}
	
	@Test
	@DisplayName("Deve retornar 404 ao buscar um aluno pelo id inexistente")
	void testNaoObterPorId() throws Exception {
		when(alunoService.obterPorId(any(Long.class))).thenThrow(ResourceNotFoundException.class);
		
		mockMvc.perform(get("/aluno/1")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Deve retornar 201 ao salvar um aluno")
	void testSalvar() throws Exception {
		when(alunoService.salvar(any(Aluno.class))).thenReturn(aluno);
		
		var novoAluno = new Aluno();
		novoAluno.setMatricula("100200");
		novoAluno.setNome("Lorem Ipsum");
		novoAluno.setDataNascimento(LocalDate.of(2000, 10, 1));
		novoAluno.setDataIngresso(LocalDate.of(2020, 1, 1));
		novoAluno.setEndereco(endereco);
		novoAluno.setCurso(new Curso());
		novoAluno.setUsuario(new Usuario());
		
		mockMvc.perform(post("/aluno")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(novoAluno)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(aluno)))
                .andExpect(header().string("Location", "http://localhost/aluno/1"));
	}
	
	@Test
	@DisplayName("Deve retornar 400 ao tentar salvar um aluno inválido")
	void testNaoSalvar() throws Exception {
		var novoAluno = new Aluno();
		novoAluno.setNome("Lorem Ipsum");
		novoAluno.setDataNascimento(LocalDate.of(2000, 10, 1));
		novoAluno.setDataIngresso(LocalDate.of(2020, 1, 1));
		
		mockMvc.perform(post("/aluno")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(novoAluno)))
                .andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Deve retornar 200 ao editar os dados de um aluno")
	void testEditar() throws Exception {
		when(alunoService.editar(any(Aluno.class))).thenReturn(aluno);
		
		mockMvc.perform(put("/aluno")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(aluno)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(aluno)));
	}

	@Test
	@DisplayName("Deve retornar 200 ao remover um aluno da base de dados")
	void testRemover() throws Exception {
		doNothing().when(alunoService).remover(any(Aluno.class));
		
        mockMvc.perform(delete("/aluno")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(mapper.writeValueAsString(aluno)))
                .andExpect(status().isOk());
	}

}
