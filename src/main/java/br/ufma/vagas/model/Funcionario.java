package br.ufma.vagas.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Funcionario extends EntityBase {
	private String nome;
	private String matricula;
	private String cargo;
	private LocalDate dataNascimento;
	private Endereco endereco;
	private Empresa empresa;
	private Usuario usuario;

}
