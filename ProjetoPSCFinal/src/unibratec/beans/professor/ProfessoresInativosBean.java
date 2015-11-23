package unibratec.beans.professor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import unibratec.basica.Professor;
import unibratec.basica.Situacao;
import unibratec.fachada.Fachada;
import unibratec.fachada.IFachada;
import unibratec.util.Mensagens;

@SuppressWarnings({ "unused", "serial" })
@ViewScoped
@ManagedBean
public class ProfessoresInativosBean implements Serializable {
	
	private IFachada fachada;
	private List<Professor> professores;
	private Professor professorSelecionado;
	
	public ProfessoresInativosBean(){
		fachada = Fachada.getInstance();
		professores = new ArrayList<Professor>();
		professorSelecionado = new Professor();
	}

	public List<Professor> getProfessores() {
		return fachada.consultaProfessoresInativos();
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Professor getProfessorSelecionado() {
		return professorSelecionado;
	}

	public void setProfessorSelecionado(Professor professorSelecionado) {
		this.professorSelecionado = professorSelecionado;
	}
	
	public void ativarProfessor(){
		professorSelecionado.setSituacao(Situacao.ATIVO);
		fachada.alterarProfessor(professorSelecionado);
		professorSelecionado = new Professor();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Mensagens.MENSAGEM_SUCESSO_ALTERAR +" "+Mensagens.MENSAGEM_PROFESSOR_ATIVO));
	}

}
