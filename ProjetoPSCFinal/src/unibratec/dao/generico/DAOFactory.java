package unibratec.dao.generico;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import unibratec.dao.dominio.DAOAluno;
import unibratec.dao.dominio.DAOCriterio;
import unibratec.dao.dominio.DAOProfessor;
import unibratec.dao.dominio.DAOProjeto;
import unibratec.dao.dominio.DAOProjetoProfessorAvaliador;
import unibratec.dao.dominio.IDAOAluno;
import unibratec.dao.dominio.IDAOCriterio;
import unibratec.dao.dominio.IDAOProfessor;
import unibratec.dao.dominio.IDAOProjeto;
import unibratec.dao.dominio.IDAOProjetoProfessorAvaliador;

@SuppressWarnings("rawtypes")
public abstract class DAOFactory {

	private static final EntityManagerFactory factory;

	private static IDAOAluno daoAluno;
	private static IDAOProfessor daoProfessor;
	private static IDAOProjeto daoProjeto;
	private static IDAOProjetoProfessorAvaliador daoProjetoProfessorAvaliador;
	private static IDAOCriterio daoCriterio;

	static {
		factory = Persistence.createEntityManagerFactory("escola");
	}

	public static IDAOAluno getAlunoDAO() {
		daoAluno = new DAOAluno(factory.createEntityManager());
		return daoAluno;
	}

	public static IDAOProfessor getProfessorDao() {
		daoProfessor = new DAOProfessor(factory.createEntityManager());
		return daoProfessor;
	}

	public static IDAOProjeto getProjetoDao() {
		daoProjeto = new DAOProjeto(factory.createEntityManager());
		return daoProjeto;
	}

	public static IDAOProjetoProfessorAvaliador getProjetoProfessorAvaliador() {
		daoProjetoProfessorAvaliador = new DAOProjetoProfessorAvaliador(
				factory.createEntityManager());
		return daoProjetoProfessorAvaliador;

	}

	public static IDAOCriterio getCriterio() {
		daoCriterio = new DAOCriterio(factory.createEntityManager());
		return daoCriterio;
	}

	public static void close() {
		if (factory != null && factory.isOpen()) {
			factory.close();
		}
	}

}
