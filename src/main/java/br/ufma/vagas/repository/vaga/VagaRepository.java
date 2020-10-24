package br.ufma.vagas.repository.vaga;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.ufma.vagas.domain.vaga.Vaga;
import br.ufma.vagas.repository.RepositoryBase;

public interface VagaRepository extends RepositoryBase<Vaga> {
	List<Vaga> findByEncerramentoBetween(LocalDate dataInicial, LocalDate dataFinal);
	List<Vaga> findByTipoId(Long tipoExperienciaId);
	List<Vaga> findByEmpresaId(Long empresaId);
	Page<Vaga> findByEncerramentoAfter(LocalDate data, Pageable pageable);

}
