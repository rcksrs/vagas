package br.ufma.vagas.domain.vaga;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.geral.Curso;
import br.ufma.vagas.domain.geral.Empresa;
import br.ufma.vagas.domain.perfil.TipoExperiencia;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Vaga extends EntityBase implements Serializable {
	private static final long serialVersionUID = 5275967908923321137L;
	
	private String titulo;
	private String descricao;
	private Integer vagas;
	private LocalDate abertura;
	private LocalDate encerramento;
	
	@ManyToOne
	private TipoExperiencia tipo;
	
	@ManyToOne
	private Empresa empresa;
	
	@ManyToMany
	@JoinTable(name = "vaga_curso", 
		joinColumns = @JoinColumn(name="vaga_id"),
		inverseJoinColumns = @JoinColumn(name="curso_id"))
	private List<Curso> cursos;

}
