package unibratec.beans.projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.unibratec.basica.Aluno;
import br.com.unibratec.basica.Projeto;
import br.com.unibratec.basica.Situacao;
import br.com.unibratec.fachada.Fachada;
import br.com.unibratec.fachada.IFachada;
import br.com.unibratec.util.Mensagens;


@SuppressWarnings({ "unused", "serial" })
@ViewScoped
@ManagedBean
public class ProjetosInativosBean implements Serializable {

	private IFachada fachada;
	private List<Projeto> projetos;
	private Projeto projetoSelecionado;
	
	public ProjetosInativosBean(){
		fachada = Fachada.getInstance();
		projetos = new ArrayList<Projeto>();
		projetoSelecionado = new Projeto();
	}

	public List<Projeto> getProjetos() {
		return fachada.consultarProjetosInativos();
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
	
	public void ativaProjeto(){
		projetoSelecionado.setSituacao(Situacao.ATIVO);
		fachada.alterarProjeto(projetoSelecionado);
		projetoSelecionado = new Projeto();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Mensagens.MENSAGEM_SUCESSO_ALTERAR +" "+Mensagens.MENSAGEM_PROJETO_ATIVO));
	}
	
}
