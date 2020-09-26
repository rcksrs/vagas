package br.ufma.vagas.domain.perfil;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Experiencia extends EntityBase {
	private String titulo;
	private String descricao;
	private String urlComprovante;
	private String local;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	
	@ManyToOne
	private TipoExperiencia tipo;

}
