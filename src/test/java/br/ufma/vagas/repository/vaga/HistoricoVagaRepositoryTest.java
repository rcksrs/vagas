package br.ufma.vagas.repository.vaga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class HistoricoVagaRepositoryTest {
	
	@Autowired
	private HistoricoVagaRepository historicoRepository;

	@Test
	@DisplayName("Deve retornar o histórico de uma vaga")
	void testFindByVagaId() {
		var historico = historicoRepository.findByVagaId(1L);
		assertFalse(historico.isEmpty());
	}

}
