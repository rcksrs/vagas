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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufma.vagas.domain.geral.Curso;
import br.ufma.vagas.domain.geral.Empresa;
import br.ufma.vagas.domain.geral.Modalidade;
import br.ufma.vagas.domain.geral.TipoCurso;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.service.geral.CursoService;

@WebMvcTest(CursoController.class)
class CursoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CursoService cursoService;
	
	private static Curso curso;
	
	private static ObjectMapper mapper;
	
	@BeforeAll
	static void beforeAll() {
		mapper = new ObjectMapper();
		
		curso = new Curso();
		curso.setId(1L);
		curso.setNome("Ciência da Computação");
		curso.setSemestres(8);
		curso.setModalidade(Modalidade.PRESENCIAL);
		curso.setTipo(new TipoCurso());
		curso.setEmpresa(new Empresa());
		
	}

	@Test
	@DisplayName("Deve retornar 200 ao obter um curso pelo id")
	void testObterPorId() throws Exception {
		when(cursoService.obterPorId(1L)).thenReturn(curso);
		
		mockMvc.perform(get("/curso/1")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(curso)));
	}
	
	@Test
	@DisplayName("Deve retornar 404 quando o curso buscado não for encontrado")
	void testNaoObterPorId() throws Exception {
		when(cursoService.obterPorId(any(Long.class))).thenThrow(ResourceNotFoundException.class);
		
		mockMvc.perform(get("/curso/1")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Deve retornar 201 quando salvar um novo curso")
	void testSalvar() throws Exception {
		when(cursoService.salvar(any(Curso.class))).thenReturn(curso);
		
		var novoCurso = new Curso();
		novoCurso.setNome("Ciência da Computação");
		novoCurso.setSemestres(8);
		novoCurso.setModalidade(Modalidade.PRESENCIAL);
		novoCurso.setTipo(new TipoCurso());
		novoCurso.setEmpresa(new Empresa());
		
		mockMvc.perform(post("/curso")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(novoCurso)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(curso)))
                .andExpect(header().string("Location", "http://localhost/curso/1"));
	}

	@Test
	@DisplayName("Deve retornar 200 ao editar um curso")
	void testEditar() throws Exception {
		when(cursoService.editar(any(Curso.class))).thenReturn(curso);
		
		mockMvc.perform(put("/curso")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(curso)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(curso)));
	}

	@Test
	@DisplayName("Deve retornar 200 ao remover um curso válido")
	void testRemover() throws Exception {
		doNothing().when(cursoService).remover(any(Curso.class));
		
        mockMvc.perform(delete("/curso")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(mapper.writeValueAsString(curso)))
                .andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Deve retornar 400 ao tentar remover um curso inválido")
	void testNaoRemover() throws Exception {
		mockMvc.perform(delete("/curso")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(mapper.writeValueAsString(new Curso())))
                .andExpect(status().isBadRequest());
	}

}
