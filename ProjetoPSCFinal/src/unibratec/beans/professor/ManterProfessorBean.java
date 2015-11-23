package unibratec.beans.professor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import unibratec.basica.Aluno;
import unibratec.basica.Coordenador;
import unibratec.basica.Professor;
import unibratec.basica.Situacao;
import unibratec.basica.UnidadeFederativa;
import unibratec.fachada.Fachada;
import unibratec.fachada.IFachada;
import unibratec.util.Mensagens;

@SuppressWarnings({ "unused", "serial" })
@ManagedBean
@ViewScoped
public class ManterProfessorBean implements Serializable {
	//Atributos
	private IFachada fachada;
	private Professor professor;
	private Professor professorSelecionado;
	private List<Professor> professores;
	//Construtor
	public ManterProfessorBean() {
		fachada = Fachada.getInstance();
		professor = new Professor();
		professorSelecionado = new Professor();
		professores = new ArrayList<Professor>();
	}
	//Getters e Setters
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Professor getProfessorSelecionado() {
		return professorSelecionado;
	}

	public void setProfessorSelecionado(Professor professorSelecionado) {
		this.professorSelecionado = professorSelecionado;
	}

	public List<Professor> getProfessores() {
		return fachada.consultaProfessoresAtivos();
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public UnidadeFederativa[] getUnidadeFederativas() {
		return UnidadeFederativa.values();
	}

	public Situacao[] getSituacaos() {
		return Situacao.values();
	}
	
	public Coordenador[] getCoordenadors(){
		return Coordenador.values();
	}
	//FIM Getters e Setters
	/**
	 * Salva um professor
	 */
	public void salvarProfessor() {
		try {
			fachada.inserirProfessor(professor);
			professor = new Professor();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(Mensagens.MENSAGEM_SUCESSO_CADASTRO));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Mensagens.MENSAGEM_FALHA_CADASTRO + " : "+ e.getMessage()));
			e.printStackTrace();
		}
		;

	}
	/**
	 * Remove um professor
	 */
	public void removerProfessor() {
		fachada.removerProfessor(professorSelecionado);
		professorSelecionado = new Professor();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(Mensagens.MENSAGEM_SUCESSO_EXCLUIR));

	}
	/**
	 * Altera alguns dados do professor
	 */
	public void alterarProfessor() {
		fachada.alterarProfessor(professorSelecionado);
		professorSelecionado = new Professor();
		;
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(Mensagens.MENSAGEM_SUCESSO_ALTERAR));

	}
}
