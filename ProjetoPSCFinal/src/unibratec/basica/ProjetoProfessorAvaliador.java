package unibratec.basica;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "projeto_nota")
public class ProjetoProfessorAvaliador extends ObjetoGeral implements
		Serializable {
	public ProjetoProfessorAvaliador() {
		id = new ProjetoProfessorAvaliadorPK();
	}

	private ProjetoProfessorAvaliadorPK id;

	private Double nota;

	public ProjetoProfessorAvaliadorPK getId() {
		return id;
	}

	public void setId(ProjetoProfessorAvaliadorPK id) {
		this.id = id;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return id.getProjeto() + " " + id.getAvaliador() + " "
				+ id.getAvaliador();
	}

	@Override
	public boolean equals(Object obj) {
		if (id.getAvaliador().getCodigo() == ((ProjetoProfessorAvaliador) obj)
				.getId().getAvaliador().getCodigo()) {
			return true;
		} else if (id.getProjeto().getCodigo() == ((ProjetoProfessorAvaliador) obj)
				.getId().getProjeto().getCodigo()) {
			return true;
		} else if (id.getCriterios().getCodigo() == ((ProjetoProfessorAvaliador) obj)
				.getId().getCriterios().getCodigo()) {
			return true;
		} else {
			return false;
		}
	}

}
