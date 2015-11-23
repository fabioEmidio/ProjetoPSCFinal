package unibratec.beans.professor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import unibratec.basica.Professor;
import unibratec.basica.Projeto;
import unibratec.fachada.Fachada;
import unibratec.fachada.IFachada;
import unibratec.util.ConfiguracaoDoSistema;

@SuppressWarnings({ "unused", "serial" })
@ManagedBean
@ViewScoped
public class ManterProfessorOrientadorBean implements Serializable {
	// Atributos
	private IFachada fachada;
	private List<Projeto> projetos;
	private PrincipalProfessorBean principalProfessorBean;

	// Construtor
	public ManterProfessorOrientadorBean() {
		fachada = Fachada.getInstance();
		projetos = new ArrayList<Projeto>();
		principalProfessorBean = new PrincipalProfessorBean();
	}

	// Getters e Setters
	public List<Projeto> getProjetos() {
		return fachada.consultarProfessorOrientador(principalProfessorBean.getProfessorLogado());
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	// FIM Getters e Setters
}
