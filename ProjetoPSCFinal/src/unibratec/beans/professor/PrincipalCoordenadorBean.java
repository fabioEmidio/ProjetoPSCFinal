package unibratec.beans.professor;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import unibratec.basica.Professor;
import unibratec.util.ConfiguracaoDoSistema;

@SuppressWarnings({ "unused", "serial" })
@ViewScoped
@ManagedBean
public class PrincipalCoordenadorBean implements Serializable {
	
	private Professor coordenadorLogado;

	public PrincipalCoordenadorBean() {
		coordenadorLogado = new Professor();
	}

	public Professor getCoordenadorLogado() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		if (session != null) {
			return (Professor) session
					.getAttribute(ConfiguracaoDoSistema.COORDENADOR_SESSAO);
		}
		return null;
	}

	public void setCoordenadorLogado(Professor coordenadorLogado) {
		this.coordenadorLogado = coordenadorLogado;
	}

}
