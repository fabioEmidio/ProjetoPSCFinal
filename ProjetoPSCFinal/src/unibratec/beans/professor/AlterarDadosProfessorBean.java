package unibratec.beans.professor;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import unibratec.basica.Professor;
import unibratec.fachada.IFachada;
import unibratec.util.Mensagens;

@SuppressWarnings({ "serial" })
@ManagedBean
@ViewScoped
public class AlterarDadosProfessorBean implements Serializable {
	// Getters e Setters
	private IFachada fachada;
	private Professor professor;
	private PrincipalProfessorBean principalProfessorBean;

	// Construtor
	public AlterarDadosProfessorBean() {
		professor = new Professor();
		principalProfessorBean = new PrincipalProfessorBean();
	}

	// Getters e Setters
	public Professor getProfessor() {
		professor = principalProfessorBean.getProfessorLogado();
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	// FIM Getters e Setters
	/**
	 * Método responsavel por alterar os dados
	 */
	public void alterarDados() {
		fachada.alterarProfessor(professor);
		professor = new Professor();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(Mensagens.MENSAGEM_SUCESSO_ALTERAR));
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
	}

}
