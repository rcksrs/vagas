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

import br.ufma.vagas.domain.geral.Empresa;
import br.ufma.vagas.domain.geral.Endereco;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.service.geral.EmpresaService;

@WebMvcTest(EmpresaController.class)
class EmpresaControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmpresaService empresaService;
	
	private static Empresa empresa;
	private static Endereco endereco;
	
	private static ObjectMapper mapper;
	
	@BeforeAll
	static void beforeAll() {
		mapper = new ObjectMapper();
		
		endereco = new Endereco();
		endereco.setCep("65000000");
		
		empresa = new Empresa();
		empresa.setId(1L);
		empresa.setCnpj("09469471000118");
		empresa.setNome("Lorem Ipsum");
		empresa.setRepresentante("Lorem Ipsum");
		empresa.setEmail("email@empresa.com");
		empresa.setTelefone("32222222");
		empresa.setEndereco(endereco);
		
	}

	@Test
	@DisplayName("Deve retornar 200 ao buscar uma empresa pelo id cadastrado")
	void testObterPorId() throws Exception {
		when(empresaService.obterPorId(1L)).thenReturn(empresa);
		
		mockMvc.perform(get("/empresa/1")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(empresa)));
	}
	
	@Test
	@DisplayName("Deve retornar 404 ao buscar uma empresa pelo id inexistente")
	void testNaoObterPorId() throws Exception {
		when(empresaService.obterPorId(any(Long.class))).thenThrow(ResourceNotFoundException.class);
		
		mockMvc.perform(get("/empresa/1")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Deve retornar 201 ao salvar uma empresa")
	void testSalvar() throws Exception {
		when(empresaService.salvar(any(Empresa.class))).thenReturn(empresa);
		
		var novaEmpresa = new Empresa();
		novaEmpresa = new Empresa();
		novaEmpresa.setCnpj("09469471000118");
		novaEmpresa.setNome("Lorem Ipsum");
		novaEmpresa.setRepresentante("Lorem Ipsum");
		novaEmpresa.setEmail("email@empresa.com");
		novaEmpresa.setTelefone("32222222");
		novaEmpresa.setEndereco(endereco);
		
		mockMvc.perform(post("/empresa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(novaEmpresa)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(empresa)))
                .andExpect(header().string("Location", "http://localhost/empresa/1"));
	}
	
	@Test
	@DisplayName("Deve retornar 400 ao tentar salvar uma empresa inválida")
	void testNaoSalvar() throws Exception {
		mockMvc.perform(post("/empresa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new Empresa())))
                .andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Deve retornar 200 ao editar os dados de uma empresa")
	void testEditar() throws Exception {
		when(empresaService.editar(any(Empresa.class))).thenReturn(empresa);
		
		mockMvc.perform(put("/empresa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(empresa)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(empresa)));
	}

	@Test
	@DisplayName("Deve retornar 200 ao remover uma empresa da base de dados")
	void testRemover() throws Exception {
		doNothing().when(empresaService).remover(any(Empresa.class));
		
        mockMvc.perform(delete("/empresa")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(mapper.writeValueAsString(empresa)))
                .andExpect(status().isOk());
	}

}
