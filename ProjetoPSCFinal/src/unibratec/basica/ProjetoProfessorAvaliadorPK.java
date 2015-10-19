package unibratec.basica;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class ProjetoProfessorAvaliadorPK implements Serializable {
	public ProjetoProfessorAvaliadorPK() {
		avaliador = new Professor();
		projeto = new Projeto();
		criterios = new Criterio();
	}

	@ManyToOne
	@JoinColumn(name = "cod_professor_avaliador")
	private Professor avaliador;
	@ManyToOne
	@JoinColumn(name = "cod_projeto")
	private Projeto projeto;
	@ManyToOne
	@JoinColumn(name = "cod_criterio")
	private Criterio criterios;

	public Professor getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(Professor avaliador) {
		this.avaliador = avaliador;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Criterio getCriterios() {
		return criterios;
	}

	public void setCriterios(Criterio criterios) {
		this.criterios = criterios;
	}

	@Override
	public String toString() {
		return "Avaliador : " + avaliador.getNome() + " Projeto : "
				+ projeto.getNome() + " Criterio : " + criterios.getDescricao();
	}

}
