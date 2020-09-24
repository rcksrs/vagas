package br.ufma.vagas.domain.estagio;

import java.time.LocalDate;
import java.util.List;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.geral.Aluno;
import br.ufma.vagas.domain.geral.Empresa;
import lombok.Data;

@Data
public class VinculoEstagio extends EntityBase {
	private Aluno aluno;
	private Empresa empresa;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private List<Aditivo> aditivos;

}
