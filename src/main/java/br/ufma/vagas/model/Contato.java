package br.ufma.vagas.model;

import lombok.Data;

@Data
public class Contato extends EntityBase {
	private String descricao;
	private String numero;

}
