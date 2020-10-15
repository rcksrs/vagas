package br.ufma.vagas.repository.geral;

import java.util.List;
import java.util.Optional;

import br.ufma.vagas.domain.geral.Funcionario;
import br.ufma.vagas.repository.RepositoryBase;

public interface FuncionarioRepository extends RepositoryBase<Funcionario> {
	Optional<Funcionario> findByMatricula(String matricula);
	Optional<Funcionario> findByIdAndMatricula(Long id, String matricula);
	List<Funcionario> findByNomeContainingIgnoreCase(String nome);
	List<Funcionario> findByEmpresaId(Long empresaId);

}
