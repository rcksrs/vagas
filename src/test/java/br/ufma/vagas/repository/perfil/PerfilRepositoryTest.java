package br.ufma.vagas.repository.perfil;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PerfilRepositoryTest {
	
	@Autowired
	private PerfilRepository perfilRepository;

	@Test
	@DisplayName("Deve retornar uma lista de perfis ao buscar pelo resumo")
	void testFindByResumoContainingIgnoreCase() {
		var perfis = perfilRepository.findByResumoContainingIgnoreCase("ipsum dolor sit");
		assertFalse(perfis.isEmpty());
	}

}
