package br.ufma.vagas.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Formacao extends EntityBase {
	private String local;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private TipoCurso tipo;

}
