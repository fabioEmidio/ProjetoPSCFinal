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
import unibratec.fachada.Fachada;
import unibratec.fachada.IFachada;
import unibratec.util.Mensagens;

@SuppressWarnings({ "unused", "serial" })
@ViewScoped
@ManagedBean
public class AlunosInativosBean implements Serializable {
	private IFachada fachada;
	private List<Aluno> alunos;
	private Aluno alunoSelecionado;

	public AlunosInativosBean() {
		fachada = Fachada.getInstance();
		alunos = new ArrayList<Aluno>();
		alunoSelecionado = new Aluno();
	}

	public List<Aluno> getAlunos() {
		return fachada.consultarAlunosInativos();
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	
	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public void ativarAluno(){
		alunoSelecionado.setSituacao(Situacao.ATIVO);
		fachada.alterarAluno(alunoSelecionado);
		alunoSelecionado = new Aluno();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Mensagens.MENSAGEM_SUCESSO_ALTERAR +" "+Mensagens.MENSAGEM_ALUNO_ATIVO));
	}

}
