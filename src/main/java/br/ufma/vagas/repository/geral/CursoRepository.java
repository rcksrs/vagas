package br.ufma.vagas.repository.geral;

import java.util.List;

import br.ufma.vagas.domain.geral.Curso;
import br.ufma.vagas.repository.RepositoryBase;

public interface CursoRepository extends RepositoryBase<Curso> {
	List<Curso> findByNomeContaining(String nome);

}
