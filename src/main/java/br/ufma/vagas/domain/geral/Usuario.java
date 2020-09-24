package br.ufma.vagas.domain.geral;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.perfil.Perfil;
import lombok.Data;

@Data
public class Usuario extends EntityBase {
	private String cpf;
	private String email;
	private Boolean emailConfirmado = false;
	private String telefone;
	private Boolean telefoneConfirmado = false;
	private String senha;
	private Perfil perfil;

}
