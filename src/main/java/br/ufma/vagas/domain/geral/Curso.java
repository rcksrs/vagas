package br.ufma.vagas.domain.geral;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.vaga.Vaga;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Curso extends EntityBase implements Serializable {
	private static final long serialVersionUID = 947975858196655768L;
	
	private String nome;
	private Integer chTotal;
	private Integer semestres;
	
	@ManyToOne
	private TipoCurso tipo;
	
	@ManyToOne
	private Empresa empresa;
	
	@ManyToOne
	@JsonIgnore
	private Vaga vaga;

}
