package br.ufma.vagas.domain.estagio;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Aditivo extends EntityBase implements Serializable {
	private static final long serialVersionUID = -6478645298265875303L;
	
	private String termo;
	private String observacao;
	private LocalDate dataInicio;
	private LocalDate dataFim;

}
