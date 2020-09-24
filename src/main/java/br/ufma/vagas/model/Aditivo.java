package br.ufma.vagas.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Aditivo extends EntityBase {
	private String termo;
	private String observacao;
	private LocalDate dataInicio;
	private LocalDate dataFim;

}
