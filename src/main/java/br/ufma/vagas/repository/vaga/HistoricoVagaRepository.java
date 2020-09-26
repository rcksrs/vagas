package br.ufma.vagas.repository.vaga;

import java.util.List;

import br.ufma.vagas.domain.vaga.HistoricoVaga;
import br.ufma.vagas.repository.RepositoryBase;

public interface HistoricoVagaRepository extends RepositoryBase<HistoricoVaga> {
	List<HistoricoVaga> findByVagaId(Long vagaId);

}
