package unibratec.dao.dominio;

import java.util.List;

import unibratec.basica.Aluno;
import unibratec.basica.Professor;
import unibratec.dao.generico.IDAOGenerico;

public interface IDAOProjeto<Projeto> extends IDAOGenerico<Projeto> {
	//(ALUNO)
	public List<Projeto> consultarAlunoParticipante(Aluno aluno);
	//(PROFESSOR-ORIENTADOR)
	public List<Projeto> consultarProfessorOrientador(Professor professor);
	//(PROFESSOR-AVALIADOR)
	public List<Projeto> consultaProfessorAvaliador(Professor professor);
	//(COORDENADOR)
	public List<Projeto> consultarProjetosAtivos();
	//(COORDENADOR)
	public List<Projeto> consultarProjetosInativos();
}
