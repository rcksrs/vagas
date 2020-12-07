package br.ufma.vagas.domain.vaga;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	private String titulo;
	
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	private String descricao;
	
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private Integer vagas;
	
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private LocalDate abertura;
	
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private LocalDate encerramento;
	
	@ManyToOne
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private TipoExperiencia tipo;
	
	@ManyToOne
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private Empresa empresa;
	
	@ManyToMany
	@JoinTable(name = "vaga_curso", 
		joinColumns = @JoinColumn(name="vaga_id"),
		inverseJoinColumns = @JoinColumn(name="curso_id"))
	private List<Curso> cursos;

}
