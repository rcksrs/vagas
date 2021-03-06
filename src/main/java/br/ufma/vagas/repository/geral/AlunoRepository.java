package br.ufma.vagas.repository.geral;

import java.util.List;
import java.util.Optional;

import br.ufma.vagas.domain.geral.Aluno;
import br.ufma.vagas.repository.RepositoryBase;

public interface AlunoRepository extends RepositoryBase<Aluno> {
	Optional<Aluno> findByMatricula(String matricula);
	Optional<Aluno> findByIdAndMatricula(Long id, String matricula);
	List<Aluno> findByNomeContainingIgnoreCase(String nome);
	List<Aluno> findByCursoId(Long cursoId);

}
