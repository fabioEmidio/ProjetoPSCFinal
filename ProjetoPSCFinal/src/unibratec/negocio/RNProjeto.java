package unibratec.negocio;

import java.util.Date;
import java.util.List;

import unibratec.basica.Aluno;
import unibratec.basica.Professor;
import unibratec.basica.Projeto;
import unibratec.basica.Situacao;
import unibratec.dao.dominio.IDAOProjeto;
import unibratec.dao.generico.DAOFactory;
import unibratec.util.ConfiguracaoDoSistema;

@SuppressWarnings("unchecked")
public class RNProjeto {
	@SuppressWarnings("rawtypes")
	IDAOProjeto daoProjeto;

	public RNProjeto() {
		daoProjeto = DAOFactory.getProjetoDao();
	}

	public void inserirProjeto(Projeto projeto, Aluno aluno) throws Exception {
		//Projeto Ativo
		projeto.setSituacao(Situacao.ATIVO);
		//Pega os participantes dentro do Projeto
		List<Aluno> listaComAlunosParticipantes = projeto.getParticipantes();
		//Verifica se a quantidade de participantes e maior que a permitida
		if (listaComAlunosParticipantes.size() > ConfiguracaoDoSistema.QTD_ALUNO_POR_PROJETO) {
			throw new Exception("Quantidade de alunos maior que a permitida, escolha 4 alunos.");
		}
		//Verifica se a quantidade de participantes e menor que a permitida
		if (listaComAlunosParticipantes.size() < ConfiguracaoDoSistema.QTD_ALUNO_POR_PROJETO) {
			throw new Exception("Quantidade de alunos é menor que a permitida, escolha 4 alunos.");
		} else {
			//Inicio
			//Verifica se o aluno que está cadastrando o projeto está no projeto
			boolean temNaLista = false;
			
			for (int i = 0; i < listaComAlunosParticipantes.size(); i++) {
				Aluno alunoParticipante = listaComAlunosParticipantes.get(i);
				if (alunoParticipante.getCodigo().equals(aluno.getCodigo())) {
					temNaLista = true;
					break;
				}
			}
			
			if(temNaLista == false){ 
				throw new Exception("Para o projeto ser cadastrado o aluno " + aluno.getNome() +" com a matricula : "+aluno.getMatricula()+" deverá participar do projeto.");
			}

		}
		//Fim
		//Verifica se o nome do projeto está em branco.
		if (projeto.getNome().length() < 1) {
			throw new Exception("Nome do projeto não pode ficar em branco.");
		}
		//Verifica se o nome do projeto está e maior que 100 caracteres.
		if (projeto.getNome().length() > 100) {
			throw new Exception(
					"Nome do projeto não pode ser maior que 100 caracteres.");
		}
		projeto.setData(new Date());
		daoProjeto.inserir(projeto);
	}

	public void alterarProjeto(Projeto projeto) {
		daoProjeto.alterar(projeto);

	}

	public void removerProjeto(Projeto projeto) {
		projeto.setSituacao(Situacao.INATIVO);
		daoProjeto.alterar(projeto);
	}

	public Projeto consultarProjetoPorId(Integer id) {
		return (Projeto) daoProjeto.consultarPorId(id);
	}

	public List<Projeto> consultarTodosProjeto() {
		return daoProjeto.consultarTodos();
	}

	public List<Projeto> consultarTodosProjeto(Integer indiceInicial,
			Integer quantidade) {
		return daoProjeto.consultarTodos();
	}

	public List<Projeto> consultarAlunoParticipante(Aluno aluno) {
		return daoProjeto.consultarAlunoParticipante(aluno);
	}

	public List<Projeto> consultarProfessorOrientador(Professor professor) {
		return daoProjeto.consultarProfessorOrientador(professor);
	}

	public List<Projeto> consultarProfessorAvaliador(Professor professor) {
		return daoProjeto.consultaProfessorAvaliador(professor);
	}

	public List<Projeto> consultarProjetosAtivos() {
		return daoProjeto.consultarProjetosAtivos();
	}
	
	public List<Projeto> consultarProjetosInativos(){
		return daoProjeto.consultarProjetosInativos();
	}
	
}
