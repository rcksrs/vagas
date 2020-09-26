package br.ufma.vagas.domain.geral;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Curso extends EntityBase implements Serializable {
	private static final long serialVersionUID = 947975858196655768L;
	
	private String nome;
	private Integer chTotal;
	private Integer semestres;
	
	@Enumerated(EnumType.STRING)
	private Modalidade modalidade;
	
	@ManyToOne
	private TipoCurso tipo;
	
	@ManyToOne
	private Empresa empresa;

}
