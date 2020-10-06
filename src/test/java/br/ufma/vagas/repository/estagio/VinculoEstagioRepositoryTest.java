package br.ufma.vagas.repository.estagio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class VinculoEstagioRepositoryTest {
	
	@Autowired
	private VinculoEstagioRepository vinculoRepository;

	@Test
	@DisplayName("Deve retornar os vínculos de estágio do aluno a partir do id informado")
	void testFindByAlunoId() {
		var vinculos = vinculoRepository.findByAlunoId(1L);
		assertFalse(vinculos.isEmpty());
	}

	@Test
	@DisplayName("Deve retornar todos os vínculos de estágio em uma empresa")
	void testFindByEmpresaId() {
		var vinculos = vinculoRepository.findByEmpresaId(1L);
		assertFalse(vinculos.isEmpty());
	}

}
