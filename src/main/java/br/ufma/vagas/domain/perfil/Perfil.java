package br.ufma.vagas.domain.perfil;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "perfil_id")
	private List<Experiencia> experiencias;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "perfil_id")
	private List<Formacao> formacoes;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "perfil_id")
	private List<Idioma> idiomas;

}
