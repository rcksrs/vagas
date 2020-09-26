package br.ufma.vagas.domain.geral;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Curso extends EntityBase {
	private String nome;
	private Integer chTotal;
	private Float semestres;
	
	@ManyToOne
	private TipoCurso tipo;
	
	@ManyToOne
	private Empresa empresa;

}
