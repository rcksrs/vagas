package br.ufma.vagas.domain.geral;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Curso extends EntityBase implements Serializable {
	private static final long serialVersionUID = 947975858196655768L;
	
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	private String nome;
	
	private Integer chTotal;
	
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private Integer semestres;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private Modalidade modalidade;
	
	@ManyToOne
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private TipoCurso tipo;
	
	@ManyToOne
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private Empresa empresa;

}
