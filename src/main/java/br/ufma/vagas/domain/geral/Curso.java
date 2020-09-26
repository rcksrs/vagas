package br.ufma.vagas.domain.geral;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.vaga.Vaga;
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
	
	@ManyToOne
	@JsonIgnore
	private Vaga vaga;

}
