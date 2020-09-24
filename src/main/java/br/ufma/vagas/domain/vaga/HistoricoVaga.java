package br.ufma.vagas.domain.vaga;

import br.ufma.vagas.domain.EntityBase;
import lombok.Data;

@Data
public class HistoricoVaga extends EntityBase {
	private String descricao;
	private Vaga vaga;
	private Status status;

}
