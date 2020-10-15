package br.ufma.vagas.service.geral;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufma.vagas.domain.geral.Funcionario;
import br.ufma.vagas.exception.BusinessException;
import br.ufma.vagas.exception.DuplicatedResourceException;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.repository.geral.FuncionarioRepository;
import br.ufma.vagas.service.ServiceBase;

@Service
public class FuncionarioService extends ServiceBase<Funcionario, FuncionarioRepository> {

	public Funcionario obterPorMatricula(String matricula) {
		return repository.findByMatricula(matricula).orElseThrow(ResourceNotFoundException::new);
	}
	
	public List<Funcionario> obterPorNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome);
	}
	
	public List<Funcionario> obterPorEmpresa(Long empresaId) {
		return repository.findByEmpresaId(empresaId);
	}
	
	@Override
	public Funcionario salvar(Funcionario funcionario) {
		var podeSalvar = funcionario.getId() == null && repository.findByMatricula(funcionario.getMatricula()).isEmpty();
		if(podeSalvar) return super.salvar(funcionario);
		throw new DuplicatedResourceException();
	}
	
	@Override
	public Funcionario editar(Funcionario funcionario) {
		var podeEditar = repository.findByIdAndMatricula(funcionario.getId(), funcionario.getMatricula()).isPresent();
		if(podeEditar) return super.editar(funcionario);
		throw new BusinessException("Não é possível alterar a matrícula do funcionario");		
	}
	
}
