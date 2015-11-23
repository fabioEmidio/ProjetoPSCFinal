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
public class PrincipalProfessorBean implements Serializable {

	private Professor professorLogado;

	public PrincipalProfessorBean() {
		professorLogado = new Professor();
	}

	public Professor getProfessorLogado() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		if (session != null) {
			return (Professor) session
					.getAttribute(ConfiguracaoDoSistema.PROFESSOR_SESSAO);
		}
		return null;
	}

	public void setProfessorLogado(Professor professorLogado) {
		this.professorLogado = professorLogado;
	}

}
