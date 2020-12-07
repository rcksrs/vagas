package br.ufma.vagas.domain.geral;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Funcionario extends EntityBase implements Serializable {
	private static final long serialVersionUID = 8954049745235787734L;
	
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	private String nome;
	
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	private String matricula;
	
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	private String cargo;
	
	private LocalDate dataNascimento;
	
	@Embedded
	@Valid
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private Endereco endereco;
	
	@ManyToOne
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private Empresa empresa;
	
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

}
