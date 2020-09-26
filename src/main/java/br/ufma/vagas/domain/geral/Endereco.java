package br.ufma.vagas.domain.geral;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Endereco {
	private String cep;
	private String pais;
	private String estado;
	private String cidade;
	private String bairro;
	private String logradouro;
	private String numero;
	private String complemento;
	private String referencia;

}
