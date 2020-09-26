package br.ufma.vagas.domain.geral;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Aluno extends EntityBase implements Serializable {
	private static final long serialVersionUID = -2546938448239522253L;
	
	private String nome;
	private String matricula;
	private LocalDate dataNascimento;
	private LocalDate dataIngresso;
	
	@Embedded
	private Endereco endereco;
	
	@ManyToOne
	private Curso curso;
	
	@OneToOne
	private Usuario usuario;

}
