package br.ufma.vagas.domain.estagio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.geral.Aluno;
import br.ufma.vagas.domain.geral.Empresa;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class VinculoEstagio extends EntityBase implements Serializable{
	private static final long serialVersionUID = -9017735405928874520L;
	
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private LocalDate dataInicio;
	
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private LocalDate dataFim;
	
	@ManyToOne
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private Aluno aluno;
	
	@ManyToOne
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private Empresa empresa;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vinculoEstagio")
	private List<Aditivo> aditivos;

}
