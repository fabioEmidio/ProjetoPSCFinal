package unibratec.fachada;
 
import java.util.List;
import unibratec.basica.Criterio;
import unibratec.basica.Projeto;
import unibratec.basica.ProjetoProfessorAvaliador;
import unibratec.basica.Aluno;
import unibratec.basica.Professor;


public interface IFachada {

	// --------Login

		public Aluno efetuarLoginAluno(Aluno aluno) throws NullPointerException;
		public Professor efetuarLoginProfessor(Professor professor) throws NullPointerException;
		public Professor efetuarLoginProfessorCoordenador(Professor professor) throws NullPointerException;

		// --------ALUNO
		public void inserirAluno(Aluno aluno) throws Exception;
		public void alterarAluno(Aluno aluno);
		public void removerAluno(Aluno aluno);
		public Aluno consultarAlunoPorId(Integer id);
		public Aluno consultarAlunoPorMatricula(Aluno aluno);
		public List<Aluno> consultarTodosAluno();
		public List<Aluno> consultarTodosAluno(Integer indiceInicial,
				Integer quantidade);
		public List<Aluno> consultarAlunosAtivos();
		public List<Aluno> consultarAlunosInativos();
		
		// --------PROFESSOR
		public void inserirProfessor(Professor professor)throws Exception;
		public void alterarProfessor(Professor professor);
		public void removerProfessor(Professor professor);
		public Professor consultarProfessorPorId(Integer id);
		public Professor consultaProfessorPorMatricula(Professor professor);
		public List<Professor> consultarTodosProfessor();
		public List<Professor> consultarTodosProfessor(Integer indiceInicial,
				Integer quantidade);
		public List<Professor> consultaProfessoresAtivos();
		public List<Professor> consultaProfessoresInativos();
		
		// --------CRITERIO
		public void inserirCriterio(Criterio criterio)throws Exception;
		public void alterarCriterio(Criterio criterio);
		public void removerCriterio(Criterio criterio) throws Exception;
		public Criterio consultarCriterioPorId(Integer id);
		public List<Criterio> consultarTodosCriterio();
		public List<Criterio> consultarTodosCriterio(Integer indiceInicial,
				Integer quantidade);
		
		// --------PROJETO
		public void inserirProjeto(Projeto projeto , Aluno aluno)throws Exception;
		public void alterarProjeto(Projeto projeto);
		public void removerProjeto(Projeto projeto);
		public Projeto consultarProjetoPorId(Integer id);
		public List<Projeto> consultarTodosProjeto();
		public List<Projeto> consultarTodosProjeto(Integer indiceInicial,
				Integer quantidade);
		public List<Projeto> consultarAlunoParticipante(Aluno aluno);
		public List<Projeto> consultarProfessorOrientador(Professor professor);
		public List<Projeto> consultarProfessorAvaliador(Professor professor);
		public List<Projeto> consultarProjetosAtivos();
		public List<Projeto> consultarProjetosInativos();
		
		
		// --------PROJETO PROFESSOR AVALIADOR
		public void inserirProjetoProfessorAvaliador(
				ProjetoProfessorAvaliador projetoProfessorAvaliador);
		public void alterarProjetoProfessorAvaliador(
				ProjetoProfessorAvaliador projetoProfessorAvaliador);
		public void removerProjetoProfessorAvaliador(
				ProjetoProfessorAvaliador projetoProfessorAvaliador) throws Exception;
		public ProjetoProfessorAvaliador consultarProjetoProfessorAvaliadorPorId(
				Integer id);
		public List<ProjetoProfessorAvaliador> consultarTodosProjetoProfessorAvaliador();
		public List<ProjetoProfessorAvaliador> consultarTodosProjetoProfessorAvaliador(
				Integer indiceInicial, Integer quantidade);
		public List<ProjetoProfessorAvaliador> consultaCriteriosParaNota(
				Professor professor, Projeto projeto);
		
		public void verificarQuantidadeDeAvaliadoresComOsCriterios(List<Professor> avaliadores, List<Criterio> criterios)throws Exception;

	
}
