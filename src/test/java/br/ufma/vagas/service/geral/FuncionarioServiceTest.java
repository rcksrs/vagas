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

import br.ufma.vagas.domain.geral.Funcionario;
import br.ufma.vagas.exception.BusinessException;
import br.ufma.vagas.exception.DuplicatedResourceException;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.repository.geral.FuncionarioRepository;

@SpringBootTest
class FuncionarioServiceTest {

	@Mock
	private FuncionarioRepository funcionarioRepository;

	@InjectMocks
	private FuncionarioService funcionarioService;

	private static Funcionario funcionario;

	@BeforeAll
	static void beforeAll() {
		funcionario = new Funcionario();
		funcionario.setId(1L);
		funcionario.setMatricula("100200");
		funcionario.setNome("Lorem Ipsum");
		funcionario.setDataNascimento(LocalDate.of(2000, 10, 01));
	}

	@Test
	@DisplayName("Deve salvar os dados de um funcionário")
	void testSalvarFuncionario() {
		when(funcionarioRepository.findByMatricula("100200")).thenReturn(Optional.empty());
		when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

		var novoFuncionario = new Funcionario();
		novoFuncionario.setMatricula("100200");
		novoFuncionario.setNome("Lorem Ipsum");
		novoFuncionario.setDataNascimento(LocalDate.of(2000, 10, 01));

		var funcionarioSalvo = funcionarioService.salvar(novoFuncionario);

		assertAll(() -> {
			assertEquals(1L, funcionarioSalvo.getId());
			assertEquals(novoFuncionario.getMatricula(), funcionarioSalvo.getMatricula());
			assertEquals(novoFuncionario.getDataNascimento(), funcionarioSalvo.getDataNascimento());
			assertEquals(novoFuncionario.getNome(), funcionarioSalvo.getNome());
		});
	}

	@Test
	@DisplayName("Não deve salvar um funcionário com uma matrícula já cadastrada")
	void testNaoSalvarFuncionario() {
		when(funcionarioRepository.findByMatricula("100200")).thenReturn(Optional.of(funcionario));

		var novoFuncionario = new Funcionario();
		novoFuncionario.setMatricula("100200");
		novoFuncionario.setNome("Lorem Ipsum");
		novoFuncionario.setDataNascimento(LocalDate.of(2000, 10, 01));

		assertThrows(DuplicatedResourceException.class, () -> funcionarioService.salvar(novoFuncionario));
	}

	@Test
	@DisplayName("Deve editar os dados de um funcionário")
	void testEditarFuncionario() {
		when(funcionarioRepository.findByIdAndMatricula(1L, "100200")).thenReturn(Optional.of(funcionario));
		when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

		var novoFuncionario = new Funcionario();
		novoFuncionario.setId(1L);
		novoFuncionario.setMatricula("100200");
		novoFuncionario.setNome("Lorem Ipsum");
		novoFuncionario.setDataNascimento(LocalDate.of(2000, 10, 01));

		var funcionarioSalvo = funcionarioService.editar(novoFuncionario);

		assertAll(() -> {
			assertEquals(1L, funcionarioSalvo.getId());
			assertEquals(novoFuncionario.getMatricula(), funcionarioSalvo.getMatricula());
			assertEquals(novoFuncionario.getDataNascimento(), funcionarioSalvo.getDataNascimento());
			assertEquals(novoFuncionario.getNome(), funcionarioSalvo.getNome());
		});
	}

	@Test
	@DisplayName("Não deve editar os dados de um funcionário que teve a matrícula alterada")
	void testNaoEditarFuncionario() {
		when(funcionarioRepository.findByIdAndMatricula(1L, "100200")).thenReturn(Optional.empty());

		var novoFuncionario = new Funcionario();
		novoFuncionario.setId(1L);
		novoFuncionario.setMatricula("100200");
		novoFuncionario.setNome("Lorem Ipsum");
		novoFuncionario.setDataNascimento(LocalDate.of(2000, 10, 01));

		assertThrows(BusinessException.class, () -> funcionarioService.editar(novoFuncionario));
	}

	@Test
	@DisplayName("Deve remover os dados de um funcionário")
	void testRemover() {
		when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
		doNothing().when(funcionarioRepository).deleteById(1L);

		assertDoesNotThrow(() -> funcionarioService.remover(funcionario));
	}

	@Test
	@DisplayName("Não deve remover os dados de um funcionário caso não seja encontrado pelo id")
	void testNaoRemover() {
		when(funcionarioRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> funcionarioService.remover(funcionario));
	}

}
