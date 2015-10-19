package unibratec.dao.dominio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import unibratec.basica.Professor;
import unibratec.dao.generico.DAOGenerico;

public class DAOProfessor extends DAOGenerico<Professor> implements
		IDAOProfessor<Professor> {

	public DAOProfessor(EntityManager em) {
		super(em);
	}
	/**
	 * Consulta os professores por matricula
	 */
	@Override
	public Professor consultaProfessorPorMatricula(Professor professor)throws NullPointerException {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "SELECT p FROM Professor p where p.matricula = :matricula";
			TypedQuery<Professor> queryProfessor = this.entityManager
					.createQuery(sql, Professor.class);
			queryProfessor.setParameter("matricula", professor.getMatricula());
			professor = queryProfessor.getSingleResult();
			return professor;

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}
	/**
	 * Consultar Login do professor
	 */
	@Override
	public Professor consultarLoginProfessor(Professor professor)
			throws NullPointerException {

		EntityTransaction et = getEntityManager().getTransaction();

		try {
			String sql = "Select prof FROM Professor prof where prof.matricula = :matricula and prof.senha = :senha and prof.situacao = 'ATIVO'";
			TypedQuery<Professor> queryProfessor = this.entityManager
					.createQuery(sql, Professor.class);
			queryProfessor.setParameter("matricula", professor.getMatricula());
			queryProfessor.setParameter("senha", professor.getSenha());
			professor = queryProfessor.getSingleResult();

			return professor;

		} catch (Exception ex) {
			ex.printStackTrace();
			if (et != null && et.isActive()) {
				et.rollback();
			}

		}
		return null;
	}
	/**
	 * Consulta login Professor Coordenador
	 */
	@Override
	public Professor efetuarLoginProfessorCoordenador(Professor professor)
			throws NullPointerException{
		EntityTransaction et = getEntityManager().getTransaction();
		try {
			String sql = "select p from Professor p where p.matricula = :cmatricula and p.senha = :csenha and p.coordenador = :ccoordenador and p.situacao = 'ATIVO' ";
			TypedQuery<Professor> queryCoordenador = this.entityManager
					.createQuery(sql, Professor.class);
			queryCoordenador.setParameter("cmatricula",
					professor.getMatricula());
			queryCoordenador.setParameter("csenha", professor.getSenha());
			queryCoordenador.setParameter("ccoordenador",
					professor.getCoordenador());
			professor = queryCoordenador.getSingleResult();

			return professor;
		} catch (Exception ex) {

			ex.printStackTrace();
			if (et != null && et.isActive()) {
				et.rollback();
			}
			
		}
		return null;

	}
	/**
	 * Consulta os professores ativos
	 */
	@Override
	public List<Professor> consultaProfessoresAtivos() {
		EntityTransaction et = getEntityManager().getTransaction();
		try {
			String sql = "SELECT p FROM Professor p WHERE p.situacao = 'ATIVO' ";
			TypedQuery<Professor> queryProfessorAtivo = this.entityManager
					.createQuery(sql, Professor.class);

			return queryProfessorAtivo.getResultList();
		} catch (Exception ex) {

			ex.printStackTrace();
			if (et != null && et.isActive()) {
				et.rollback();
			}
			return null;
		}
	}
	
	/**
	 * Consulta os professores inativos
	 */
	@Override
	public List<Professor> consultaProfessoresInativos() {
		EntityTransaction et = getEntityManager().getTransaction();
		try {
			String sql = "SELECT p FROM Professor p WHERE p.situacao = 'INATIVO' ";
			TypedQuery<Professor> queryProfessorAtivo = this.entityManager
					.createQuery(sql, Professor.class);

			return queryProfessorAtivo.getResultList();
		} catch (Exception ex) {

			ex.printStackTrace();
			if (et != null && et.isActive()) {
				et.rollback();
			}
			return null;
		}
	}

}
