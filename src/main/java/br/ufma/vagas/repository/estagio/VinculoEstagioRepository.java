package br.ufma.vagas.repository.estagio;

import java.util.List;

import br.ufma.vagas.domain.estagio.VinculoEstagio;
import br.ufma.vagas.repository.RepositoryBase;

public interface VinculoEstagioRepository extends RepositoryBase<VinculoEstagio> {
	List<VinculoEstagio> findByAlunoId(Long alunoId);
	List<VinculoEstagio> findByEmpresaId(Long empresaId);

}
