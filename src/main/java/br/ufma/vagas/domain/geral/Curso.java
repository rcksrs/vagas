package br.ufma.vagas.domain.geral;

import br.ufma.vagas.domain.EntityBase;
import lombok.Data;

@Data
public class Curso extends EntityBase {
	private String nome;
	private Integer chTotal;
	private Float semestres;
	private TipoCurso tipo;
	private Empresa empresa;

}
