package br.ufma.vagas.service.geral;

import org.springframework.stereotype.Service;

import br.ufma.vagas.domain.geral.Aluno;
import br.ufma.vagas.repository.geral.AlunoRepository;
import br.ufma.vagas.service.ServiceBase;

@Service
public class AlunoService extends ServiceBase<Aluno, AlunoRepository> {

}
