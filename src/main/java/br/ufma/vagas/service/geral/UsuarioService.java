package br.ufma.vagas.service.geral;

import org.springframework.stereotype.Service;

import br.ufma.vagas.domain.geral.Usuario;
import br.ufma.vagas.exception.BusinessException;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.repository.geral.UsuarioRepository;
import br.ufma.vagas.service.ServiceBase;

@Service
public class UsuarioService extends ServiceBase<Usuario, UsuarioRepository> {

	public Usuario obterPorCpf(String cpf) {
		return repository.findByCpf(cpf).orElseThrow(ResourceNotFoundException::new);
	}
	
	public Usuario obterPorEmail(String email) {
		return repository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
	}
	
	@Override
	public Usuario salvar(Usuario entity) {
		var podeSalvar = repository.findByCpf(entity.getCpf()).isEmpty() &&
				repository.findByEmail(entity.getEmail()).isEmpty();
		if(podeSalvar) return super.salvar(entity);
		throw new BusinessException("O Email ou CPF informado já está cadastrado");
	}
	
}
