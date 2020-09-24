package br.ufma.vagas.domain.perfil;

import java.util.List;

import br.ufma.vagas.domain.EntityBase;
import lombok.Data;

@Data
public class Perfil extends EntityBase {
	private String resumo;
	private String urlLattes;
	private String urlImagem;
	private List<Experiencia> experiencias;
	private List<Formacao> formacoes;
	private List<Idioma> idiomas;

}
