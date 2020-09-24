package br.ufma.vagas.domain.vaga;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.geral.Aluno;
import lombok.Data;

@Data
public class AlunoVaga extends EntityBase {
	private Integer pontuacao;
	private Aluno aluno;
	private Vaga vaga;
	private Status status;

}
