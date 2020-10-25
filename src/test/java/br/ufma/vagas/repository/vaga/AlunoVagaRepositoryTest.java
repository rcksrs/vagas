package br.ufma.vagas.repository.vaga;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufma.vagas.domain.geral.Aluno;
import br.ufma.vagas.domain.vaga.AlunoVaga.AlunoVagaId;
import br.ufma.vagas.domain.vaga.Vaga;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AlunoVagaRepositoryTest {
	
	@Autowired
	private AlunoVagaRepository alunoVagaRepository;
	
	@Test
	@DisplayName("Deve retornar a relaçao entre aluno e vaga ao buscar pelo aluno e pela vaga")
	void testFindByIdAlunoIdAndIdVagaId() {
		var alunoVaga = alunoVagaRepository.findByIdAlunoIdAndIdVagaId(1L, 1L);
		assertTrue(alunoVaga.isPresent());
	}

	@Test
	@DisplayName("Deve retornar as relações entre aluno e vaga ao buscar pelo aluno")
	void testFindByIdAlunoId() {
		var alunoVagas = alunoVagaRepository.findByIdAlunoId(1L);
		assertFalse(alunoVagas.isEmpty());
	}

	@Test
	@DisplayName("Deve retornar as relações entre aluno e vaga ao buscar pela vaga")
	void testFindByIdVagaId() {
		var alunoVagas = alunoVagaRepository.findByIdVagaId(1L);
		assertFalse(alunoVagas.isEmpty());
	}
	
	@Test
	@DisplayName("Deve retornar a relaçao entre aluno e vaga ao buscar pelo id")
	void testFindById() {
		var aluno = new Aluno();
		aluno.setId(1L);
		
		var vaga = new Vaga();
		vaga.setId(1L);
		
		var id = new AlunoVagaId(aluno, vaga);
		
		var alunoVaga = alunoVagaRepository.findById(id);
		assertTrue(alunoVaga.isPresent());
	}

}
