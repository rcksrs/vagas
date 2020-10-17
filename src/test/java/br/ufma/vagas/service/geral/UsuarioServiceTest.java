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

import br.ufma.vagas.domain.geral.Usuario;
import br.ufma.vagas.exception.BusinessException;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.repository.geral.UsuarioRepository;

@SpringBootTest
class UsuarioServiceTest {
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@InjectMocks
	private UsuarioService usuarioService;
	
	private static Usuario usuario;

	@BeforeAll
	static void beforeAll() {
		usuario = new Usuario();
		usuario.setId(1L);
		usuario.setCpf("123000");
		usuario.setEmail("user@email.com");
	}

	@Test
	@DisplayName("Deve salvar os dados de um usuário")
	void testSalvarUsuario() {
		when(usuarioRepository.findByCpf("123000")).thenReturn(Optional.empty());
		when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
		
		var novoUsuario = new Usuario();
		novoUsuario.setCpf("123000");
		novoUsuario.setEmail("user@email.com");
		
		var usuarioSalvo = usuarioService.salvar(novoUsuario);
		
		assertAll(() -> {
			assertEquals(1L, usuarioSalvo.getId());
			assertEquals(novoUsuario.getCpf(), usuarioSalvo.getCpf());
			assertEquals(novoUsuario.getEmail(), usuarioSalvo.getEmail());
		});
	}
	
	@Test
	@DisplayName("Não deve salvar os dados de um usuário com um email ou CPF já cadastrados")
	void testNaoSalvarUsuario() {
		when(usuarioRepository.findByCpf("123000")).thenReturn(Optional.empty());
		when(usuarioRepository.findByEmail("user@email.com")).thenReturn(Optional.of(usuario));
		when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
		
		var novoUsuario = new Usuario();
		novoUsuario.setCpf("123000");
		novoUsuario.setEmail("user@email.com");
		
		assertThrows(BusinessException.class, () -> usuarioService.salvar(usuario));
	}

	@Test
	@DisplayName("Deve remover os dados de um usuário")
	void testRemover() {
		when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
		doNothing().when(usuarioRepository).deleteById(1L);
		
		assertDoesNotThrow(() -> usuarioService.remover(usuario));
	}
	
	@Test
	@DisplayName("Não deve remover os dados de um usuário caso não seja encontrado pelo id")
	void testNaoRemover() {
		when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());		
		assertThrows(ResourceNotFoundException.class, () -> usuarioService.remover(usuario));
	}

}
