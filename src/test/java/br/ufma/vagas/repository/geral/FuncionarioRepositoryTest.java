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
class FuncionarioRepositoryTest {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Test
	@DisplayName("Deve retornar um funcionário ao buscar pela matrícula")
	void testFindByMatricula() {
		var funcionario = funcionarioRepository.findByMatricula("123456");
		assertTrue(funcionario.isPresent());
	}

	@Test
	@DisplayName("Deve retornar uma lista de funcionários ao buscar pelo nome")
	void testFindByNomeContainingIgnoreCase() {
		var funcionario = funcionarioRepository.findByNomeContainingIgnoreCase("Con");
		assertFalse(funcionario.isEmpty());
	}

	@Test
	@DisplayName("Deve retornar uma lista de funcionários ao buscar pela empresa")
	void testFindByEmpresaId() {
		var funcionario = funcionarioRepository.findByEmpresaId(1L);
		assertFalse(funcionario.isEmpty());
	}

}
