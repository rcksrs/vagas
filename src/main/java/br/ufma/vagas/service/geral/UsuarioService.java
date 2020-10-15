package br.ufma.vagas.service.geral;

import org.springframework.stereotype.Service;

import br.ufma.vagas.domain.geral.Usuario;
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
	
}
