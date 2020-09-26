package br.ufma.vagas.domain.perfil;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Perfil extends EntityBase {
	private String resumo;
	private String urlLattes;
	private String urlImagem;
	
	@OneToMany
	private List<Experiencia> experiencias;
	
	@OneToMany
	private List<Formacao> formacoes;
	
	@OneToMany
	private List<Idioma> idiomas;

}
