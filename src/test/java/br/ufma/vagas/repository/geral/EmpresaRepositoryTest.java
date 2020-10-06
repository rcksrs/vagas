package br.ufma.vagas.repository.geral;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class EmpresaRepositoryTest {
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Test
	@DisplayName("Deve retornar uma empresa ao buscar pelo CNPJ")
	void testFindByCnpj() {
		var empresa = empresaRepository.findByCnpj("06279103000119");
		assertTrue(empresa.isPresent());
	}

	@Test
	@DisplayName("Deve retornar uma lista de empresas ao buscar pelo nome")
	void testFindByNomeContainingIgnoreCase() {
		var empresas = empresaRepository.findByNomeContainingIgnoreCase("ufma");
		assertFalse(empresas.isEmpty());
	}

}
