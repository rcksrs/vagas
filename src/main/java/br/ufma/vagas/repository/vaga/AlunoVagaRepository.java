package br.ufma.vagas.repository.vaga;

import java.util.List;

import br.ufma.vagas.domain.vaga.AlunoVaga;
import br.ufma.vagas.repository.RepositoryBase;

public interface AlunoVagaRepository extends RepositoryBase<AlunoVaga> {
	List<AlunoVaga> findByIdAlunoId(Long alunoId);
	List<AlunoVaga> findByIdVagaId(Long vagaId);

}
