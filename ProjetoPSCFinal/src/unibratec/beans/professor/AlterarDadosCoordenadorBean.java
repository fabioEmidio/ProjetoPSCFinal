package unibratec.beans.professor;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import unibratec.basica.Professor;
import unibratec.fachada.Fachada;
import unibratec.fachada.IFachada;
import unibratec.util.Mensagens;

@SuppressWarnings({ "serial" })
@ManagedBean
@ViewScoped
public class AlterarDadosCoordenadorBean implements Serializable {
	// Atributos
	private IFachada fachada;
	private Professor coordenador;
	private PrincipalCoordenadorBean principalCoordenadorBean;

	// Getters e Setters
	public AlterarDadosCoordenadorBean() {
		fachada = Fachada.getInstance();
		coordenador = new Professor();
		principalCoordenadorBean = new PrincipalCoordenadorBean();
	}

	public Professor getCoordenador() {
		coordenador = principalCoordenadorBean.getCoordenadorLogado();
		return coordenador;
	}

	public void setCoordenador(Professor coordenador) {
		this.coordenador = coordenador;
	}

	// FIM Getters e Setters
	/**
	 * Método responsavel por alterar os dados
	 */
	public void alterarDados() {
		fachada.alterarProfessor(coordenador);
		coordenador = new Professor();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(Mensagens.MENSAGEM_SUCESSO_ALTERAR));
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
	}

}
