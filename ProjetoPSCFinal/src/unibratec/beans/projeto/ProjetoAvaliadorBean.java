package unibratec.beans.projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.unibratec.basica.Aluno;
import br.com.unibratec.basica.Criterio;
import br.com.unibratec.basica.Professor;
import br.com.unibratec.basica.Projeto;
import br.com.unibratec.basica.ProjetoProfessorAvaliador;
import br.com.unibratec.basica.ProjetoProfessorAvaliadorPK;
import br.com.unibratec.fachada.Fachada;
import br.com.unibratec.fachada.IFachada;
import br.com.unibratec.util.Mensagens;

@SuppressWarnings({ "unused", "serial" })
@ManagedBean
@ViewScoped
public class ProjetoAvaliadorBean implements Serializable {
	// Atributos
	private IFachada fachada;
	private List<Criterio> criterios;
	private List<Criterio> criteriosSelecionados;
	private List<Projeto> projetos;
	private Projeto projetoSelecionado;
	private List<Professor> avaliadores;
	private List<Professor> avaliadoresSelecionados;
	private ProjetoProfessorAvaliadorPK projetoProfessorAvaliadorPK;
	private ProjetoProfessorAvaliador projetoProfessorAvaliador;

	// Construtor
	public ProjetoAvaliadorBean() {
		fachada = Fachada.getInstance();
		criterios = new ArrayList<Criterio>();
		criteriosSelecionados = new ArrayList<Criterio>();
		projetos = new ArrayList<Projeto>();
		projetoSelecionado = new Projeto();
		avaliadores = new ArrayList<Professor>();
		avaliadoresSelecionados = new ArrayList<Professor>();
		projetoProfessorAvaliadorPK = new ProjetoProfessorAvaliadorPK();
		projetoProfessorAvaliador = new ProjetoProfessorAvaliador();
	}

	// Getters e Setters
	public List<Criterio> getCriterios() {

		return fachada.consultarTodosCriterio();
	}

	public void setCriterios(List<Criterio> criterios) {
		this.criterios = criterios;
	}

	public List<Criterio> getCriteriosSelecionados() {
		return criteriosSelecionados;
	}

	public void setCriteriosSelecionados(List<Criterio> criteriosSelecionados) {
		this.criteriosSelecionados = criteriosSelecionados;
	}

	public List<Projeto> getProjetos() {
		return fachada.consultarProjetosAtivos();
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

	public List<Professor> getAvaliadores() {
		List<Professor> todosOsProfessores = fachada.consultarTodosProfessor();
		for (int i = 0; i < todosOsProfessores.size(); i++) {
			Professor orientador = todosOsProfessores.get(i);
			if (orientador.getCodigo().equals(getProjetoSelecionado().getOrientador().getCodigo())) {
				todosOsProfessores.remove(i);
			}
		}
		return todosOsProfessores;
	}

	public void setAvaliadores(List<Professor> avaliadores) {
		this.avaliadores = avaliadores;
	}

	public List<Professor> getAvaliadoresSelecionados() {
		return avaliadoresSelecionados;
	}

	public void setAvaliadoresSelecionados(
			List<Professor> avaliadoresSelecionados) {
		this.avaliadoresSelecionados = avaliadoresSelecionados;
	}

	public ProjetoProfessorAvaliadorPK getProjetoProfessorAvaliadorPK() {
		return projetoProfessorAvaliadorPK;
	}

	public void setProjetoProfessorAvaliadorPK(
			ProjetoProfessorAvaliadorPK projetoProfessorAvaliadorPK) {
		this.projetoProfessorAvaliadorPK = projetoProfessorAvaliadorPK;
	}

	public ProjetoProfessorAvaliador getProjetoProfessorAvaliador() {
		return projetoProfessorAvaliador;
	}

	public void setProjetoProfessorAvaliador(
			ProjetoProfessorAvaliador projetoProfessorAvaliador) {
		this.projetoProfessorAvaliador = projetoProfessorAvaliador;
	}

	public String inserirProjetoProfessorAvaliador() {
		adicionarNoPk(projetoSelecionado, avaliadoresSelecionados,
				criteriosSelecionados);

		return null;
	}

	// FIM Getters e Setters
	/**
	 * Metodo responsavel por fazer a inserção do PROJETO, AVALIADOR e CRITERIO
	 * na tabela de PROJETO_NOTAS
	 * 
	 * @param projeto
	 * @param avaliadores
	 * @param criterios
	 */
	public void adicionarNoPk(Projeto projeto, List<Professor> avaliadores, List<Criterio> criterios) {
		try {
			
			fachada.verificarQuantidadeDeAvaliadoresComOsCriterios(avaliadores, criterios);
			
			for (int i = 0; i < avaliadores.size(); i++) {
				for (int j = 0; j < criterios.size(); j++) {
					projetoProfessorAvaliador = new ProjetoProfessorAvaliador();
					projetoProfessorAvaliadorPK = new ProjetoProfessorAvaliadorPK();
					projetoProfessorAvaliadorPK.setAvaliador(avaliadores.get(i));
					projetoProfessorAvaliadorPK.setProjeto(projeto);
					projetoProfessorAvaliadorPK.setCriterios(criterios.get(j));
					projetoProfessorAvaliador.setId(projetoProfessorAvaliadorPK);
					fachada.inserirProjetoProfessorAvaliador(projetoProfessorAvaliador);
				}
			}
			criteriosSelecionados = new ArrayList<Criterio>();
			projetoSelecionado = new Projeto();
			avaliadoresSelecionados = new ArrayList<Professor>();
			projetoProfessorAvaliador = new ProjetoProfessorAvaliador();
			projetoProfessorAvaliadorPK = new ProjetoProfessorAvaliadorPK();
			
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Mensagens.MENSAGEM_FALHA_CADASTRO + " : "+ e.getMessage()));
			criteriosSelecionados = new ArrayList<Criterio>();
			avaliadoresSelecionados = new ArrayList<Professor>();
			e.printStackTrace();
		}

	}

	/**
	 * "Remove" um projeto
	 */
	public void desativarProjeto() {
		fachada.removerProjeto(projetoSelecionado);
		projetoSelecionado = new Projeto();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(Mensagens.MENSAGEM_SUCESSO_EXCLUIR));

	}
}
