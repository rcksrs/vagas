package br.ufma.vagas.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Vaga extends EntityBase {
	private String titulo;
	private String desricao;
	private Integer vagas;
	private LocalDate dataLimite;
	private TipoExperiencia tipo;
	private Empresa empresa;
	private List<Curso> cursos;

}
