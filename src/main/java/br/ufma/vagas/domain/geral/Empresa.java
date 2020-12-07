package br.ufma.vagas.domain.geral;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Empresa extends EntityBase implements Serializable {
	private static final long serialVersionUID = -2639131072190392289L;
	
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	private String nome;
	
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	@CNPJ(message = "Informe um CNPJ válido")
	private String cnpj;
	
	private String natureza;
	
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	private String representante;
	
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	@Email(message = "Informe um email válido")
	private String email;
	
	private String site;
	
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	private String telefone;
	
	@Embedded
	@Valid
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private Endereco endereco;

}
