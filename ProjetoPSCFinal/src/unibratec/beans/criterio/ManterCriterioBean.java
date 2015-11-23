package unibratec.beans.criterio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import unibratec.basica.Criterio;
import unibratec.fachada.Fachada;
import unibratec.fachada.IFachada;
import unibratec.util.Mensagens;

@SuppressWarnings({ "unused", "serial" })
@ManagedBean
@ViewScoped
public class ManterCriterioBean implements Serializable {
	// Atributos
	private IFachada fachada;
	private Criterio criterio;
	private Criterio criterioSelecionado;
	private List<Criterio> criterios;

	// Construtor
	public ManterCriterioBean() {
		fachada = Fachada.getInstance();
		criterio = new Criterio();
		criterioSelecionado = new Criterio();
		criterios = fachada.consultarTodosCriterio();

	}

	// Getters e Setters
	public Criterio getCriterio() {
		return criterio;
	}

	public void setCriterio(Criterio criterio) {
		this.criterio = criterio;
	}

	public Criterio getCriterioSelecionado() {
		return criterioSelecionado;
	}

	public void setCriterioSelecionado(Criterio criterioSelecionado) {
		this.criterioSelecionado = criterioSelecionado;
	}

	public List<Criterio> getCriterios() {
		return fachada.consultarTodosCriterio();
	}

	public void setCriterios(List<Criterio> criterios) {
		this.criterios = criterios;
	}

	// FIM Getters e Setters
	/**
	 * Salva um criterio
	 */
	public void salvarCriterio() {
		try {
			fachada.inserirCriterio(criterio);
			criterio = new Criterio();
			FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(Mensagens.MENSAGEM_SUCESSO_CADASTRO));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Mensagens.MENSAGEM_FALHA_CADASTRO + " : "+ e.getMessage()));
			e.printStackTrace();
		}
		

	}
	/**
	 * Remove um criterio
	 */
	public void removerCriterio() {
		try{
		fachada.removerCriterio(criterioSelecionado);
		criterioSelecionado = new Criterio();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(Mensagens.MENSAGEM_SUCESSO_EXCLUIR));
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(Mensagens.MENSAGEM_FALHA_EXCLUIR + " "+ e.getMessage()));
			
			e.printStackTrace();
		}

	}
	/**
	 * Altera um projeto
	 */
	public void alterarCriterio() {
		fachada.alterarCriterio(criterioSelecionado);
		criterioSelecionado = new Criterio();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(Mensagens.MENSAGEM_SUCESSO_ALTERAR));
	}
}
