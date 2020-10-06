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
class AlunoRepositoryTest {
	
	@Autowired
	private AlunoRepository alunoRepository;

	@Test
	@DisplayName("Deve retornar um aluno ao buscar pela matrícula")
	void testFindByMatricula() {
		var aluno = alunoRepository.findByMatricula("202010123");
		assertTrue(aluno.isPresent());
	}

	@Test
	@DisplayName("Deve retornar uma lista de alunos ao buscar pelo nome")
	void testFindByNomeContainingIgnoreCase() {
		var alunos = alunoRepository.findByNomeContainingIgnoreCase("Con");
		assertFalse(alunos.isEmpty());
	}

	@Test
	@DisplayName("Deve retornar uma lista de alunos ao buscar pelo id do curso")
	void testFindByCursoId() {
		var aluno = alunoRepository.findByCursoId(6L);
		assertFalse(aluno.isEmpty());
	}

}
