package br.ufma.vagas.domain.estagio;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.geral.Aluno;
import br.ufma.vagas.domain.geral.Empresa;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class VinculoEstagio extends EntityBase {
	private LocalDate dataInicio;
	private LocalDate dataFim;
	
	@ManyToOne
	private Aluno aluno;
	
	@ManyToOne
	private Empresa empresa;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "vinculo_estagio_id")
	private List<Aditivo> aditivos;

}
