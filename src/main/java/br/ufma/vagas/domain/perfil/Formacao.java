package br.ufma.vagas.domain.perfil;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.geral.TipoCurso;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Formacao extends EntityBase {
	private String local;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	
	@ManyToOne
	private TipoCurso tipo;

}
