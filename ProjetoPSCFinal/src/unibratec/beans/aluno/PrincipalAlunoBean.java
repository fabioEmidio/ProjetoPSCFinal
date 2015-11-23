package unibratec.beans.aluno;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import unibratec.basica.Aluno;
import unibratec.util.ConfiguracaoDoSistema;

@SuppressWarnings({"unused","serial"})
@ViewScoped
@ManagedBean
public class PrincipalAlunoBean implements Serializable {

	private Aluno alunoLogado;
	
	public PrincipalAlunoBean(){
		alunoLogado = new Aluno();
	}

	public Aluno getAlunoLogado() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		if (session != null) {
			return (Aluno) session
					.getAttribute(ConfiguracaoDoSistema.ALUNO_SESSAO);
		}
		return null;
	}

	public void setAlunoLogado(Aluno alunoLogado) {
		this.alunoLogado = alunoLogado;
	}
	
}
