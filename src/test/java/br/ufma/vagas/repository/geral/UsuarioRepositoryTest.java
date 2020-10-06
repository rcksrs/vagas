package br.ufma.vagas.repository.geral;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	@DisplayName("Deve retornar um usuário ao buscar pelo CPF")
	void testFindByCpf() {
		var usuario = usuarioRepository.findByCpf("36977063068");
		assertTrue(usuario.isPresent());
	}

	@Test
	@DisplayName("Deve retornar um usuário ao buscar pelo email")
	void testFindByEmail() {
		var usuario = usuarioRepository.findByEmail("connie.newman@example.com");
		assertTrue(usuario.isPresent());
	}

}
