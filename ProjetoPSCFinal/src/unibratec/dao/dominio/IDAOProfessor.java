package unibratec.dao.dominio;

import java.util.List;

import unibratec.dao.generico.IDAOGenerico;

public interface IDAOProfessor<Professor> extends IDAOGenerico<Professor> {
	//(PROFESSOR)
	public Professor consultaProfessorPorMatricula(Professor professor);
	//(PROFESSOR)
	public Professor consultarLoginProfessor(Professor professor)
			throws NullPointerException;
	//(COORDENADOR)
	public Professor efetuarLoginProfessorCoordenador(Professor professor)
			throws NullPointerException;
	//(COORDENADOR)
	public List<Professor> consultaProfessoresAtivos();
	//(COORDENADOR)
	public List<Professor> consultaProfessoresInativos();
	
}
