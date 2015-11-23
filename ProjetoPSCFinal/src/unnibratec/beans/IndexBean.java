package unnibratec.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import unibratec.autenticacao.LoginTipoUsuario;
import unibratec.basica.Aluno;
import unibratec.basica.Coordenador;
import unibratec.basica.Professor;
import unibratec.fachada.Fachada;
import unibratec.fachada.IFachada;
import unibratec.util.ConfiguracaoDoSistema;
import unibratec.util.Mensagens;

@ManagedBean
@SessionScoped
public class IndexBean {
	// Atributos
	private IFachada fachada;
	private String matricula;
	private String senha;

	private LoginTipoUsuario usuario;
	private Aluno aluno;
	private Professor professor;

	// Construtor
	public IndexBean() {
		// Bot.executar();
		fachada = Fachada.getInstance();
		aluno = new Aluno();
		professor = new Professor();
	}

	// Getters e Setters
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public LoginTipoUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(LoginTipoUsuario usuario) {
		this.usuario = usuario;
	}

	// FIM Getters e Setters

	/**
	 * Método que realiza o login no sistema de acordo com o usuario
	 * selecionado.
	 * 
	 * @return
	 */
	public String logar() {

		if (usuario == LoginTipoUsuario.ALUNO) {
			try {
				
				aluno.setMatricula(matricula);
				aluno.setSenha(senha);
				aluno = fachada.efetuarLoginAluno(aluno);
				adicionarSessaoAluno(aluno);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(Mensagens.MENSAGEM_SUCESSO_LOGIN));
				aluno = new Aluno();
				matricula = null;
				senha = null;
				return "/web/aluno/principal.xhtml??faces-redirect=true";
				
			} catch (NullPointerException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(Mensagens.MENSAGEM_FALHA_LOGIN));
				e.printStackTrace();
			}
		} else if (usuario == LoginTipoUsuario.PROFESSOR) {
			try {
				
				professor.setMatricula(matricula);
				professor.setSenha(senha);
				professor = fachada.efetuarLoginProfessor(professor);
				adicionarSessaoProfessor(professor);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(Mensagens.MENSAGEM_SUCESSO_LOGIN));
				professor = new Professor();
				matricula = null;
				senha = null;
				return "/web/professor/principal.xhtml?faces-redirect=true";
				
			} catch (NullPointerException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(Mensagens.MENSAGEM_FALHA_LOGIN));
				e.printStackTrace();
			}
		} else if (usuario == LoginTipoUsuario.COORDENADOR) {
			try {
				
				professor.setMatricula(matricula);
				professor.setSenha(senha);
				professor.setCoordenador(Coordenador.SIM);
				professor = fachada.efetuarLoginProfessorCoordenador(professor);
				adicionarSessaoCoordenador(professor);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(Mensagens.MENSAGEM_SUCESSO_LOGIN));
				professor = new Professor();
				matricula = null;
				senha = null;
				return "/web/coordenador/principal.xhtml?faces-redirect=true";
				
			} catch (NullPointerException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(Mensagens.MENSAGEM_FALHA_LOGIN));
				e.printStackTrace();
			}
		}
		return null;
	}

	public LoginTipoUsuario[] getTipoUsuarios() {
		return LoginTipoUsuario.values();
	}

	/**
	 * Coloca o aluno na sessão.
	 * 
	 * @param aluno
	 */
	public void adicionarSessaoAluno(Aluno aluno) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.setAttribute(ConfiguracaoDoSistema.ALUNO_SESSAO, aluno);
	}
	
	/**
	 * Coloca o professor na sessão.
	 * 
	 * @param professor
	 */
	public static void adicionarSessaoProfessor(Professor professor) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.setAttribute(ConfiguracaoDoSistema.PROFESSOR_SESSAO, professor);
	}

	/**
	 * Coloca o coordenador na sessão.
	 * 
	 * @param professor
	 */
	public static void adicionarSessaoCoordenador(Professor professor) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.setAttribute(ConfiguracaoDoSistema.COORDENADOR_SESSAO, professor);
	}
	
	/**
	 * Deslogar o usuario
	 * 
	 * @return
	 */
	public void deslogar() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}
