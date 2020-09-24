package br.ufma.vagas.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Experiencia extends EntityBase {
	private String titulo;
	private String descricao;
	private String urlComprovante;
	private String local;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private TipoExperiencia tipo;	

}
