package unibratec.beans.professor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;

import unibratec.basica.Professor;
import unibratec.basica.Projeto;
import unibratec.basica.ProjetoProfessorAvaliador;
import unibratec.fachada.Fachada;
import unibratec.fachada.IFachada;
import unibratec.util.ConfiguracaoDoSistema;
import unibratec.util.Mensagens;

@SuppressWarnings({ "unused", "serial" })
@ManagedBean
@ViewScoped
public class ManterProfessorAvaliadorBean implements Serializable {
	//Atributos
	private IFachada fachada;
	private List<Projeto> projetos;
	private Projeto projetoSelecionado;
	private List<ProjetoProfessorAvaliador> projetoProfessorAvaliadores;
	private ProjetoProfessorAvaliador projetoProfessorAvaliador;
	private PrincipalProfessorBean principalProfessorBean;

	//Construtor
	public ManterProfessorAvaliadorBean() {
		fachada = Fachada.getInstance();
		projetos = new ArrayList<Projeto>();
		projetoSelecionado = new Projeto();
		projetoProfessorAvaliadores = new ArrayList<ProjetoProfessorAvaliador>();
		projetoProfessorAvaliador = new ProjetoProfessorAvaliador();
		principalProfessorBean = new PrincipalProfessorBean();

	}
	//Getters e Setters
	public List<Projeto> getProjetos() {
		return fachada.consultarProfessorAvaliador(principalProfessorBean.getProfessorLogado());
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}

	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}

	public List<ProjetoProfessorAvaliador> getProjetoProfessorAvaliadores() {
		return fachada.consultaCriteriosParaNota(principalProfessorBean.getProfessorLogado(),
				getProjetoSelecionado());
	}

	public void setProjetoProfessorAvaliadores(
			List<ProjetoProfessorAvaliador> projetoProfessorAvaliadores) {
		this.projetoProfessorAvaliadores = projetoProfessorAvaliadores;
	}

	public ProjetoProfessorAvaliador getProjetoProfessorAvaliador() {
		return projetoProfessorAvaliador;
	}

	public void setProjetoProfessorAvaliador(
			ProjetoProfessorAvaliador projetoProfessorAvaliador) {
		this.projetoProfessorAvaliador = projetoProfessorAvaliador;
	}
	//FIM Getters e Setters
	/**
	 * Evento ajax que pega os valores da linha editada
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {

		ProjetoProfessorAvaliador ppa = (ProjetoProfessorAvaliador) event
				.getObject();
		ppa.setNota(projetoProfessorAvaliador.getNota());
		fachada.alterarProjetoProfessorAvaliador(ppa);
		projetoProfessorAvaliador = new ProjetoProfessorAvaliador();
		ppa = new ProjetoProfessorAvaliador();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(Mensagens.MENSAGEM_SUCESSO_ALTERAR));

	}
	/**
	 * Evento ajax que cancela a edição.
	 * @param event
	 */
	public void onRowCancel(RowEditEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(Mensagens.MENSAGEM_FALHA_ALTERAR));
	}

}
