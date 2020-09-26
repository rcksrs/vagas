package br.ufma.vagas.domain.geral;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.perfil.Perfil;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Usuario extends EntityBase {
	private String cpf;
	private String email;
	private Boolean emailConfirmado;
	private String telefone;
	private Boolean telefoneConfirmado;
	private String senha;
	
	@OneToOne
	private Perfil perfil;

}
