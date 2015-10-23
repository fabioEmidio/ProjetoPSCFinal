package unibratec.fachada;

import java.util.List;

import unibratec.fachada.Fachada;
import unibratec.basica.Aluno;
import unibratec.basica.Criterio;
import unibratec.basica.Professor;
import unibratec.basica.Projeto;
import unibratec.basica.ProjetoProfessorAvaliador;
import unibratec.fachada.IFachada;
import unibratec.negocio.RNAluno;
import unibratec.negocio.RNCriterio;
import unibratec.negocio.RNProfessor;
import unibratec.negocio.RNProjeto;
import unibratec.negocio.RNProjetoProfessorAvaliador;

public class Fachada implements IFachada{

	// Atributos
		RNAluno rnAluno;
		RNProjeto rnProjeto;
		RNCriterio rnCriterio;
		RNProfessor rnProfessor;
		RNProjetoProfessorAvaliador rnProjetoProfessorAvaliador;
		private static IFachada facade = null;
		
		// Construtor
		private Fachada() {
			rnAluno = new RNAluno();
			rnCriterio = new RNCriterio();
			rnProfessor = new RNProfessor();
			rnProjeto = new RNProjeto();
			rnProjetoProfessorAvaliador = new RNProjetoProfessorAvaliador();
		}
	
		public static IFachada getInstance() {
			if (facade == null) {
				facade = new Fachada();
			}
			return facade;
		}
		
		
	@Override
	public Aluno efetuarLoginAluno(Aluno aluno) throws NullPointerException {
		// TODO Auto-generated method stub
		return rnAluno.efetuarLoginAluno(aluno);
	}

	@Override
	public Professor efetuarLoginProfessor(Professor professor) throws NullPointerException {
		// TODO Auto-generated method stub
		return rnProfessor.efetuarLoginProfessorCoordenador(professor);
	}

	@Override
	public Professor efetuarLoginProfessorCoordenador(Professor professor)
			throws NullPointerException {
		// TODO Auto-generated method stub
		return rnProfessor.efetuarLoginProfessorCoordenador(professor);
	}

	// -------- ALUNO -------- \\
	@Override
	public void inserirAluno(Aluno aluno) throws Exception {
		
		rnAluno.inserirAluno(aluno);
	}

	@Override
	public void alterarAluno(Aluno aluno) {
		
		rnAluno.alterarAluno(aluno);
	}

	@Override
	public void removerAluno(Aluno aluno) {
		// TODO Auto-generated method stub
		rnAluno.removerAluno(aluno);
	}

	@Override
	public Aluno consultarAlunoPorId(Integer id) {
		
		return rnAluno.consultarAlunoPorId(id);
	}

	@Override
	public Aluno consultarAlunoPorMatricula(Aluno aluno) {
		// TODO Auto-generated method stub
		return rnAluno.consultarAlunoPorMatricula(aluno);
	}

	@Override
	public List<Aluno> consultarTodosAluno() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aluno> consultarTodosAluno(
			Integer indiceInicial, Integer quantidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aluno> consultarAlunosAtivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aluno> consultarAlunosInativos() {
		// TODO Auto-generated method stub
		return null;
	}

	// ----- PROFESSOR -----\\	
	@Override
	public void inserirProfessor(Professor professor) throws Exception {
		rnProfessor.inserirProfessor(professor);
		
	}

	@Override
	public void alterarProfessor(Professor professor) {
		
		rnProfessor.alterarProfessor(professor);
	}

	@Override
	public void removerProfessor(Professor professor) {
		
		rnProfessor.removerProfessor(professor);
	}

	@Override
	public Professor consultarProfessorPorId(Integer id) {
		// TODO Auto-generated method stub
		return rnProfessor.consultarProfessorPorId(id);
	}

	@Override
	public Professor consultaProfessorPorMatricula(Professor professor) {
		
		return rnProfessor.consultaProfessorPorMatricula(professor);
	}

	@Override
	public List<Professor> consultarTodosProfessor() {
		
		return rnProfessor.consultarTodosProfessor();
	}

	@Override
	public List<Professor> consultarTodosProfessor(Integer indiceInicial, Integer quantidade) {
		
		return rnProfessor.consultarTodosProfessor(indiceInicial, quantidade);
	}

	@Override
	public List<Professor> consultaProfessoresAtivos() {
		
		return rnProfessor.consultaProfessoresAtivos();
	}

	@Override
	public List<Professor> consultaProfessoresInativos() {
		
		return rnProfessor.consultaProfessoresInativos();
	}

	// ------ CRITERIO ------ \\
	@Override
	public void inserirCriterio(Criterio criterio) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterarCriterio(Criterio criterio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removerCriterio(Criterio criterio) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Criterio consultarCriterioPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Criterio> consultarTodosCriterio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Criterio> consultarTodosCriterio(
			Integer indiceInicial, Integer quantidade) {
		
		return null;
	}

	// ------ PROJETO ------ \\
	@Override
	public void inserirProjeto(Projeto projeto, Aluno aluno) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterarProjeto(Projeto projeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removerProjeto(Projeto projeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Projeto consultarProjetoPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Projeto> consultarTodosProjeto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Projeto> consultarTodosProjeto(
			Integer indiceInicial, Integer quantidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Projeto> consultarAlunoParticipante(
			Aluno aluno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Projeto> consultarProfessorOrientador(
			Professor professor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Projeto> consultarProfessorAvaliador(
			Professor professor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Projeto> consultarProjetosAtivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Projeto> consultarProjetosInativos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserirProjetoProfessorAvaliador(
			ProjetoProfessorAvaliador projetoProfessorAvaliador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterarProjetoProfessorAvaliador(
			ProjetoProfessorAvaliador projetoProfessorAvaliador) {
		
		
	}

	@Override
	public void removerProjetoProfessorAvaliador(
			ProjetoProfessorAvaliador projetoProfessorAvaliador)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProjetoProfessorAvaliador consultarProjetoProfessorAvaliadorPorId(
			Integer id) {
		
		return null;
	}

	@Override
	public List<ProjetoProfessorAvaliador> consultarTodosProjetoProfessorAvaliador() {
		
		return null;
	}

	@Override
	public List<ProjetoProfessorAvaliador> consultarTodosProjetoProfessorAvaliador(
			Integer indiceInicial, Integer quantidade) {
		
		return null;
	}

	@Override
	public List<ProjetoProfessorAvaliador> consultaCriteriosParaNota(
			Professor professor, Projeto projeto) {
		
		return null;
	}

	@Override
	public void verificarQuantidadeDeAvaliadoresComOsCriterios(
			List<Professor> avaliadores,
			List<Criterio> criterios) throws Exception {
		
		
	}
	
}
