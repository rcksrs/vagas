package br.ufma.vagas.service.geral;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.vagas.domain.geral.Empresa;
import br.ufma.vagas.exception.BusinessException;
import br.ufma.vagas.exception.DuplicatedResourceException;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.repository.geral.EmpresaRepository;

@SpringBootTest
class EmpresaServiceTest {
	
	@Mock
	private EmpresaRepository empresaRepository;
	
	@InjectMocks
	private EmpresaService empresaService;
	
	private static Empresa empresa;
	
	@BeforeAll
	static void beforeAll() {
		empresa = new Empresa();
		empresa.setId(1L);
		empresa.setCnpj("123000");
		empresa.setNome("Lorem Ipsum");
	}

	@Test
	@DisplayName("Deve salvar os dados de um empresa")
	void testSalvarEmpresa() {
		when(empresaRepository.findByCnpj("123000")).thenReturn(Optional.empty());
		when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);
		
		var novaEmpresa = new Empresa();
		novaEmpresa.setCnpj("123000");
		novaEmpresa.setNome("Lorem Ipsum");
		
		var empresaSalva = empresaService.salvar(novaEmpresa);
		
		assertAll(() -> {
			assertEquals(1L, empresaSalva.getId());
			assertEquals(novaEmpresa.getNome(), empresaSalva.getNome());
			assertEquals(novaEmpresa.getCnpj(), empresaSalva.getCnpj());
		});
	}
	
	@Test
	@DisplayName("Não deve salvar uma empresa com CNPJ já cadastrado")
	void testNaoSalvarEmpresa() {
		when(empresaRepository.findByCnpj("123000")).thenReturn(Optional.of(empresa));
		
		var novaEmpresa = new Empresa();
		novaEmpresa.setCnpj("123000");
		novaEmpresa.setNome("Lorem Ipsum");
		
		assertThrows(DuplicatedResourceException.class, () -> empresaService.salvar(novaEmpresa));
	}

	@Test
	@DisplayName("Deve editar os dados de uma empresa")
	void testEditarEmpresa() {
		when(empresaRepository.findByIdAndCnpj(1L, "123000")).thenReturn(Optional.of(empresa));
		when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);
		
		var novaEmpresa = new Empresa();
		novaEmpresa.setId(1L);
		novaEmpresa.setCnpj("123000");
		novaEmpresa.setNome("Lorem Ipsum");
		
		var empresaSalva = empresaService.editar(novaEmpresa);
		
		assertAll(() -> {
			assertEquals(1L, empresaSalva.getId());
			assertEquals(novaEmpresa.getNome(), empresaSalva.getNome());
			assertEquals(novaEmpresa.getCnpj(), empresaSalva.getCnpj());
		});
	}
	
	@Test
	@DisplayName("Não deve editar os dados de uma empresa que teve o CNPJ alterado")
	void testNaoEditarEmpresa() {
		when(empresaRepository.findByIdAndCnpj(1L, "123000")).thenReturn(Optional.empty());
		
		var novaEmpresa = new Empresa();
		novaEmpresa.setId(1L);
		novaEmpresa.setCnpj("123000");
		novaEmpresa.setNome("Lorem Ipsum");
		
		assertThrows(BusinessException.class, () -> empresaService.editar(novaEmpresa));
	}

	@Test
	@DisplayName("Deve remover os dados de uma empresa")
	void testRemover() {
		when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));
		doNothing().when(empresaRepository).deleteById(1L);
		
		assertDoesNotThrow(() -> empresaService.remover(empresa));
	}
	
	@Test
	@DisplayName("Não deve remover os dados de uma empresa caso não seja encontrada pelo id")
	void testNaoRemover() {
		when(empresaRepository.findById(1L)).thenReturn(Optional.empty());		
		assertThrows(ResourceNotFoundException.class, () -> empresaService.remover(empresa));
	}

}
