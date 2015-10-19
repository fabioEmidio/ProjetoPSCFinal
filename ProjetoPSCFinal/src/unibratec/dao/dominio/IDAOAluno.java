package unibratec.dao.dominio;

import java.util.List;

import unibratec.dao.generico.IDAOGenerico;

public interface IDAOAluno<Aluno> extends IDAOGenerico<Aluno> {
	//(ALUNO)
	public Aluno consultaLogin(Aluno aluno) throws NullPointerException;
	//(ALUNO)
	public Aluno consultaAlunoPorMatricula(Aluno aluno);
	//(ALUNO)
	public List<Aluno> consultarAlunosAtivos();
	//(COORDENADOR)
	public List<Aluno> consultarAlunosInativos();
}
