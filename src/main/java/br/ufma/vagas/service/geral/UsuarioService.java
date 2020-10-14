package br.ufma.vagas.service.geral;

import org.springframework.stereotype.Service;

import br.ufma.vagas.domain.geral.Usuario;
import br.ufma.vagas.repository.geral.UsuarioRepository;
import br.ufma.vagas.service.ServiceBase;

@Service
public class UsuarioService extends ServiceBase<Usuario, UsuarioRepository> {

}
