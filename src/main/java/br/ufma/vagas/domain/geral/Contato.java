package br.ufma.vagas.domain.geral;

import br.ufma.vagas.domain.EntityBase;
import lombok.Data;

@Data
public class Contato extends EntityBase {
	private String descricao;
	private String numero;

}
