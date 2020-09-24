package br.ufma.vagas.model;

import lombok.Data;

@Data
public class HistoricoVaga extends EntityBase {
	private String descricao;
	private Vaga vaga;
	private Status status;

}
