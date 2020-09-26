package br.ufma.vagas.domain.vaga;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.geral.Curso;
import br.ufma.vagas.domain.geral.Empresa;
import br.ufma.vagas.domain.perfil.TipoExperiencia;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Vaga extends EntityBase {
	private String titulo;
	private String desricao;
	private Integer vagas;
	private LocalDate dataLimite;
	
	@ManyToOne
	private TipoExperiencia tipo;
	
	@ManyToOne
	private Empresa empresa;
	
	@OneToMany
	private List<Curso> cursos;

}
