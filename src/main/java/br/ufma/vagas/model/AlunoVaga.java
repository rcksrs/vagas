package br.ufma.vagas.model;

import lombok.Data;

@Data
public class AlunoVaga extends EntityBase {
	private Integer pontuacao;
	private Aluno aluno;
	private Vaga vaga;
	private Status status;

}
