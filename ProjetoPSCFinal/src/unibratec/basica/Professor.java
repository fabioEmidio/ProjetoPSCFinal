package unibratec.basica;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@SuppressWarnings("serial")
@Entity
public class Professor extends Usuario implements Serializable {
	@Enumerated(EnumType.STRING)
	private Coordenador coordenador;

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

}
