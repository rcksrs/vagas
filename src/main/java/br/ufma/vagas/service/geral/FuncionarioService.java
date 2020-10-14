package br.ufma.vagas.service.geral;

import org.springframework.stereotype.Service;

import br.ufma.vagas.domain.geral.Funcionario;
import br.ufma.vagas.repository.geral.FuncionarioRepository;
import br.ufma.vagas.service.ServiceBase;

@Service
public class FuncionarioService extends ServiceBase<Funcionario, FuncionarioRepository> {

}
