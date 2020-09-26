package br.ufma.vagas.repository.geral;

import java.util.List;
import java.util.Optional;

import br.ufma.vagas.domain.geral.Aluno;
import br.ufma.vagas.repository.RepositoryBase;

public interface AlunoRepository extends RepositoryBase<Aluno> {
	Optional<Aluno> findByMatricula(String matricula);
	List<Aluno> findByNomeContaining(String nome);
	List<Aluno> findByCursoId(Long cursoId);

}
