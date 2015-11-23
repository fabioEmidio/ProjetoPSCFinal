package unibratec.beans.aluno;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import unibratec.basica.Aluno;
import unibratec.basica.Situacao;
import unibratec.basica.UnidadeFederativa;
import unibratec.fachada.Fachada;
import unibratec.fachada.IFachada;
import unibratec.util.Mensagens;

@SuppressWarnings({ "unused", "serial" })
@ManagedBean
@ViewScoped
public class ManterAlunoBean implements Serializable {
	// Atributos
	private IFachada fachada;
	private Aluno aluno;
	private Aluno alunoSelecionado;
	private List<Aluno> alunos;

	// Construtor
	public ManterAlunoBean() {
		fachada = Fachada.getInstance();
		aluno = new Aluno();
		alunoSelecionado = new Aluno();
		alunos = new ArrayList<Aluno>();
	}

	// Getters e Setters
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public List<Aluno> getAlunos() {
		return fachada.consultarAlunosAtivos();
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public UnidadeFederativa[] getUnidadeFederativas() {
		return UnidadeFederativa.values();
	}

	public Situacao[] getSituacaos() {
		return Situacao.values();
	}

	// FIM Getters e Setters
	/**
	 * Cadastra um aluno.
	 */
	public void salvarAluno() {
		try {
			fachada.inserirAluno(aluno);
			aluno = new Aluno();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Mensagens.MENSAGEM_SUCESSO_CADASTRO));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Mensagens.MENSAGEM_FALHA_CADASTRO + " : "+ e.getMessage()));
			e.printStackTrace();
		}

	}

	/**
	 * Remove um aluno
	 */
	public void removerAluno() {
		fachada.removerAluno(alunoSelecionado);
		alunoSelecionado = new Aluno();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(Mensagens.MENSAGEM_SUCESSO_EXCLUIR));

	}

	/**
	 * Altera alguns dados de um aluno
	 */
	public void alterarAluno() {
		try {
			fachada.alterarAluno(alunoSelecionado);
			alunoSelecionado = new Aluno();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Mensagens.MENSAGEM_SUCESSO_ALTERAR));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(Mensagens.MENSAGEM_FALHA_CADASTRO + " : "+ e.getMessage()));
			e.printStackTrace();
		}

	}

}
