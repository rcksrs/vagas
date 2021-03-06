package br.ufma.vagas.repository.vaga;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class VagaRepositoryTest {
	
	@Autowired
	private VagaRepository vagaRepository;

	@Test
	@DisplayName("Deve retornar uma lista de vagas ao buscar por um intervalo de datas")
	void testFindByDataLimiteBetween() {
		var vagas = vagaRepository.findByEncerramentoBetween(LocalDate.of(2020, 10, 1), LocalDate.of(2020, 12, 1));
		assertFalse(vagas.isEmpty());
	}

	@Test
	@DisplayName("Deve retornar uma lista de vagas ao buscar pelo tipo de experiência")
	void testFindByTipoId() {
		var vagas = vagaRepository.findByTipoId(1L);
		assertFalse(vagas.isEmpty());
	}

	@Test
	@DisplayName("Deve retornar uma lista de vagas ao buscar pela empresa")
	void testFindByEmpresaId() {
		var vagas = vagaRepository.findByEmpresaId(1L);
		assertFalse(vagas.isEmpty());
	}

}
