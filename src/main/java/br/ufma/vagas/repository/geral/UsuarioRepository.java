package br.ufma.vagas.repository.geral;

import java.util.Optional;

import br.ufma.vagas.domain.geral.Usuario;
import br.ufma.vagas.repository.RepositoryBase;

public interface UsuarioRepository extends RepositoryBase<Usuario> {
	Optional<Usuario> findByCpf(String cpf);
	Optional<Usuario> findByEmail(String email);

}
