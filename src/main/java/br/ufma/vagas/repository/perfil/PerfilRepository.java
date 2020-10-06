package br.ufma.vagas.repository.perfil;

import java.util.List;

import br.ufma.vagas.domain.perfil.Perfil;
import br.ufma.vagas.repository.RepositoryBase;

public interface PerfilRepository extends RepositoryBase<Perfil> {
	List<Perfil> findByResumoContainingIgnoreCase(String resumo);

}
