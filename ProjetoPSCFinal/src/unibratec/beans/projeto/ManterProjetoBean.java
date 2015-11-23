package unibratec.beans.projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

importunibratec.basica.Aluno;

import unibratec.basica.Aluno;
import unibratec.basica.Professor;
import unibratec.basica.Projeto;
import unibratec.beans.aluno.PrincipalAlunoBean;
import unibratec.fachada.Fachada;
import unibratec.fachada.IFachada;
import unibratec.util.ConfiguracaoDoSistema;
import unibratec.util.Mensagens;

@SuppressWarnings({ "serial", "unused" })
@ManagedBean
@ViewScoped
public class ManterProjetoBean implements Serializable {
	// Atributos
	private IFachada fachada;
	private Projeto projeto;
	private List<Projeto> projetos;
	private Projeto projetoSelecionado;
	private List<Aluno> alunosSelecionados;
	private List<Aluno> alunos;
	private List<Professor> professores;
	private Professor professorOrientador;
	private PrincipalAlunoBean principalAlunoBean;
	

	// Construtor
	public ManterProjetoBean() {
		fachada = Fachada.getInstance();
		projeto = new Projeto();
		projetos = new ArrayList<Projeto>();
		projetoSelecionado = new Projeto();
		alunosSelecionados = new ArrayList<Aluno>();
		alunos = new ArrayList<Aluno>();
		professores = new ArrayList<Professor>();
		professorOrientador = new Professor();
		principalAlunoBean = new PrincipalAlunoBean();
	}

	// Getters e Setters
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<Projeto> getProjetos() {
		return fachada.consultarAlunoParticipante(principalAlunoBean.getAlunoLogado());
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

	public List<Aluno> getAlunosSelecionados() {
		return alunosSelecionados;
	}

	public void setAlunosSelecionados(List<Aluno> alunosSelecionados) {
		this.alunosSelecionados = alunosSelecionados;
	}

	public List<Aluno> getAlunos() {
		return fachada.consultarTodosAluno();
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Professor> getProfessores() {
		return fachada.consultarTodosProfessor();
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Professor getProfessorOrientador() {
		return professorOrientador;
	}

	public void setProfessorOrientador(Professor professorOrientador) {
		this.professorOrientador = professorOrientador;
	}
	// FIM Getters e Setters
	/**
	 * Salva um projeto.
	 */
	public void salvarProjeto() {
		try {
			
			projeto.setOrientador(professorOrientador);
			projeto.setParticipantes(alunosSelecionados);
			fachada.inserirProjeto(projeto, principalAlunoBean.getAlunoLogado());
			professorOrientador = new Professor();
			alunosSelecionados = new ArrayList<Aluno>();
			projeto = new Projeto();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Mensagens.MENSAGEM_SUCESSO_CADASTRO));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Mensagens.MENSAGEM_FALHA_CADASTRO + " : "+ e.getMessage()));
			alunosSelecionados = new ArrayList<Aluno>();
			e.printStackTrace();
		}
	}

	/**
	 * Altera algumas informações do projeto.
	 */
	public void alterarProjeto() {
		fachada.alterarProjeto(projetoSelecionado);
		projetoSelecionado = new Projeto();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(Mensagens.MENSAGEM_SUCESSO_ALTERAR));

	}
}
