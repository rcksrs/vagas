package br.ufma.vagas.repository.geral;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CursoRepositoryTest {
	
	@Autowired
	private CursoRepository cursoRepository;

	@Test
	@DisplayName("Deve retornar uma lista de cursos ao procurar pelo nome")
	void testFindByNomeContainingIgnoreCase() {
		var cursos = cursoRepository.findByNomeContainingIgnoreCase("ciência");
		assertFalse(cursos.isEmpty());
		
	}

}
