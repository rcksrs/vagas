package br.ufma.vagas.domain.geral;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.perfil.Perfil;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Usuario extends EntityBase implements Serializable {
	private static final long serialVersionUID = 7750353151568072616L;
	
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	@CPF(message = "Informe um CPF válido")
	private String cpf;
	
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	@Email(message = "Informe um email válido")
	private String email;
	
	private Boolean emailConfirmado;
	private String telefone;
	private Boolean telefoneConfirmado;
	
	//@JsonIgnore
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	private String senha;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Perfil perfil;

}
