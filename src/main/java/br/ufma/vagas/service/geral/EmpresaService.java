package br.ufma.vagas.service.geral;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufma.vagas.domain.geral.Empresa;
import br.ufma.vagas.exception.BusinessException;
import br.ufma.vagas.exception.DuplicatedResourceException;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.repository.geral.EmpresaRepository;
import br.ufma.vagas.service.ServiceBase;

@Service
public class EmpresaService extends ServiceBase<Empresa, EmpresaRepository> {
	
	public Empresa obterPorCnpj(String cnpj) {
		return repository.findByCnpj(cnpj).orElseThrow(ResourceNotFoundException::new);
	}
	
	public List<Empresa> obterPorNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome);
	}
	
	@Override
	public Empresa salvar(Empresa empresa) {
		var podeSalvar = empresa.getId() == null && repository.findByCnpj(empresa.getCnpj()).isEmpty();
		if(podeSalvar) return super.salvar(empresa);
		throw new DuplicatedResourceException();
	}
	
	@Override
	public Empresa editar(Empresa empresa) {
		var podeEditar = repository.findByIdAndCnpj(empresa.getId(), empresa.getCnpj()).isPresent();
		if(podeEditar) return super.editar(empresa);
		throw new BusinessException("Não é possível alterar o CNPJ da empresa");
	}

}
