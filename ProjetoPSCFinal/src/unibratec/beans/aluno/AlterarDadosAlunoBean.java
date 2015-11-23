package unibratec.beans.aluno;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import unibratec.basica.Aluno;
import unibratec.fachada.Fachada;
import unibratec.fachada.IFachada;
import unibratec.util.Mensagens;

@SuppressWarnings({"serial"})
@ManagedBean
@ViewScoped
public class AlterarDadosAlunoBean implements Serializable {
	//Atributos
	private IFachada fachada;
	private Aluno aluno;
	private PrincipalAlunoBean principalAlunoBean;
	//Construtor
	public AlterarDadosAlunoBean(){
		fachada = Fachada.getInstance();
		aluno = new Aluno();
		principalAlunoBean = new PrincipalAlunoBean();
	
	}

	//Getters e Setters
	public Aluno getAluno() {
		aluno = principalAlunoBean.getAlunoLogado();
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	//FIM Getters e Setters
	
	/**
	 *Método responsavel por alterar os dados.
	 */
	public void alterarDados(){
		fachada.alterarAluno(aluno);
		aluno = new Aluno();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(Mensagens.MENSAGEM_SUCESSO_ALTERAR));
		FacesContext.getCurrentInstance().getExternalContext()
		.invalidateSession();
	}
	
	
	
}
