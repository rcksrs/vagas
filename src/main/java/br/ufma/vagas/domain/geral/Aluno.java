package br.ufma.vagas.domain.geral;

import java.time.LocalDate;

import br.ufma.vagas.domain.EntityBase;
import lombok.Data;

@Data
public class Aluno extends EntityBase {
	private String nome;
	private String matricula;
	private LocalDate dataNascimento;
	private Endereco endereco;
	private Curso curso;
	private LocalDate dataIngresso;
	private Usuario usuario;

}
