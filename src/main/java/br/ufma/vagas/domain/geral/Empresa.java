package br.ufma.vagas.domain.geral;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Empresa extends EntityBase implements Serializable {
	private static final long serialVersionUID = -2639131072190392289L;
	
	private String nome;
	private String cnpj;
	private String natureza;
	private String representante;
	private String email;
	private String site;
	private String telefone;
	
	@Embedded
	private Endereco endereco;

}
