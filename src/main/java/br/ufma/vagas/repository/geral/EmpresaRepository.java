package br.ufma.vagas.repository.geral;

import java.util.List;
import java.util.Optional;

import br.ufma.vagas.domain.geral.Empresa;
import br.ufma.vagas.repository.RepositoryBase;

public interface EmpresaRepository extends RepositoryBase<Empresa> {
	Optional<Empresa> findByCnpj(String cnpj);
	Optional<Empresa> findByIdAndCnpj(Long id, String cnpj);
	List<Empresa> findByNomeContainingIgnoreCase(String nome);

}
