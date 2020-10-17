package br.ufma.vagas.service.geral;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.vagas.domain.geral.Aluno;
import br.ufma.vagas.exception.BusinessException;
import br.ufma.vagas.exception.DuplicatedResourceException;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.repository.geral.AlunoRepository;

@SpringBootTest
class AlunoServiceTest {
	
	@Mock
	private AlunoRepository alunoRepository;
	
	@InjectMocks
	private AlunoService alunoService;
	
	private static Aluno aluno;
	
	@BeforeAll
	static void beforeAll() {
		aluno = new Aluno();
		aluno.setId(1L);
		aluno.setMatricula("100200");
		aluno.setNome("Lorem Ipsum");
		aluno.setDataNascimento(LocalDate.of(2000, 10, 01));
	}

	@Test
	@DisplayName("Deve salvar os dados de um aluno")
	void testSalvarAluno() {
		when(alunoRepository.findByMatricula("100200")).thenReturn(Optional.empty());
		when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);
		
		var novoAluno = new Aluno();
		novoAluno.setMatricula("100200");
		novoAluno.setNome("Lorem Ipsum");
		novoAluno.setDataNascimento(LocalDate.of(2000, 10, 01));
		
		var alunoSalvo = alunoService.salvar(novoAluno);
		
		assertAll(() -> {
			assertEquals(1L, alunoSalvo.getId());
			assertEquals(novoAluno.getMatricula(), alunoSalvo.getMatricula());
			assertEquals(novoAluno.getDataNascimento(), alunoSalvo.getDataNascimento());
			assertEquals(novoAluno.getNome(), alunoSalvo.getNome());
		});
	}

	@Test
	@DisplayName("Não deve salvar um aluno com uma matrícula já cadastrada")
	void testNaoSalvarAluno() {
		when(alunoRepository.findByMatricula("100200")).thenReturn(Optional.of(aluno));
		
		var novoAluno = new Aluno();
		novoAluno.setMatricula("100200");
		novoAluno.setNome("Lorem Ipsum");
		novoAluno.setDataNascimento(LocalDate.of(2000, 10, 01));
		
		assertThrows(DuplicatedResourceException.class, () -> alunoService.salvar(novoAluno));
	}
	
	@Test
	@DisplayName("Deve editar os dados de um aluno")
	void testEditarAluno() {
		when(alunoRepository.findByIdAndMatricula(1L, "100200")).thenReturn(Optional.of(aluno));
		when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);
		
		var novoAluno = new Aluno();
		novoAluno.setId(1L);
		novoAluno.setMatricula("100200");
		novoAluno.setNome("Lorem Ipsum");
		novoAluno.setDataNascimento(LocalDate.of(2000, 10, 01));
		
		var alunoSalvo = alunoService.editar(novoAluno);
		
		assertAll(() -> {
			assertEquals(1L, alunoSalvo.getId());
			assertEquals(novoAluno.getMatricula(), alunoSalvo.getMatricula());
			assertEquals(novoAluno.getDataNascimento(), alunoSalvo.getDataNascimento());
			assertEquals(novoAluno.getNome(), alunoSalvo.getNome());
		});
	}
	
	@Test
	@DisplayName("Não deve editar os dados de um aluno que teve a matrícula alterada")
	void testNaoEditarAluno() {
		when(alunoRepository.findByIdAndMatricula(1L, "100200")).thenReturn(Optional.empty());
		
		var novoAluno = new Aluno();
		novoAluno.setId(1L);
		novoAluno.setMatricula("100200");
		novoAluno.setNome("Lorem Ipsum");
		novoAluno.setDataNascimento(LocalDate.of(2000, 10, 01));
		
		assertThrows(BusinessException.class, () -> alunoService.editar(novoAluno));
	}

	@Test
	@DisplayName("Deve remover os dados de um aluno")
	void testRemover() {
		when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
		doNothing().when(alunoRepository).deleteById(1L);
		
		assertDoesNotThrow(() -> alunoService.remover(aluno));
	}
	
	@Test
	@DisplayName("Não deve remover os dados de um aluno caso não seja encontrado pelo id")
	void testNaoRemover() {
		when(alunoRepository.findById(1L)).thenReturn(Optional.empty());		
		assertThrows(ResourceNotFoundException.class, () -> alunoService.remover(aluno));
	}

}
