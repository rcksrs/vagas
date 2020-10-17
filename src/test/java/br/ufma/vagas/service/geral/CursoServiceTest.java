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

import br.ufma.vagas.domain.geral.Curso;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.repository.geral.CursoRepository;

@SpringBootTest
class CursoServiceTest {
	
	@Mock
	private CursoRepository cursoRepository;
	
	@InjectMocks
	private CursoService cursoService;
	
	private static Curso curso;
	
	@BeforeAll
	static void beforeAll() {
		curso = new Curso();
		curso.setId(1L);
		curso.setNome("Ciência da Computação");
		curso.setSemestres(8);
	}

	@Test
	@DisplayName("Deve salvar os dados de um curso")
	void testSalvarCurso() {
		when(cursoRepository.save(any(Curso.class))).thenReturn(curso);
		
		var novoCurso = new Curso();
		novoCurso.setNome("Ciência da Computação");
		novoCurso.setSemestres(8);
		
		var cursoSalvo = cursoService.salvar(novoCurso);
		
		assertAll(() -> {
			assertEquals(1L, cursoSalvo.getId());
			assertEquals(novoCurso.getNome(), cursoSalvo.getNome());
			assertEquals(novoCurso.getSemestres(), cursoSalvo.getSemestres());
		});
	}

	@Test
	@DisplayName("Deve remover os dados de um curso")
	void testRemover() {
		when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
		doNothing().when(cursoRepository).deleteById(1L);
		
		assertDoesNotThrow(() -> cursoService.remover(curso));
	}
	
	@Test
	@DisplayName("Não deve remover os dados de um curso caso não seja encontrado pelo id")
	void testNaoRemover() {
		when(cursoRepository.findById(1L)).thenReturn(Optional.empty());		
		assertThrows(ResourceNotFoundException.class, () -> cursoService.remover(curso));
	}

}
