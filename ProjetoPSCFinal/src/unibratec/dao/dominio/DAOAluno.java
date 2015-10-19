package unibratec.dao.dominio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import unibratec.basica.Aluno;
import unibratec.dao.generico.DAOGenerico;

public class DAOAluno extends DAOGenerico<Aluno> implements IDAOAluno<Aluno> {

	public DAOAluno(EntityManager em) {
		super(em);
	}

	/**
	 * Consulta Login do Aluno
	 */
	@Override
	public Aluno consultaLogin(Aluno aluno) throws NullPointerException {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "SELECT a FROM Aluno a where a.matricula = :matricula and a.senha = :senha and a.situacao = 'ATIVO'";
			TypedQuery<Aluno> queryAluno = this.entityManager.createQuery(sql,
					Aluno.class);
			queryAluno.setParameter("matricula", aluno.getMatricula());
			queryAluno.setParameter("senha", aluno.getSenha());
			aluno = queryAluno.getSingleResult();
			return aluno;
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}
	/**
	 * Consulta aluno por matricula
	 */
	@Override
	public Aluno consultaAlunoPorMatricula(Aluno aluno) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "SELECT a FROM Aluno a where a.matricula = :matricula";
			TypedQuery<Aluno> queryAluno = this.entityManager.createQuery(sql,
					Aluno.class);
			queryAluno.setParameter("matricula", aluno.getMatricula());
			aluno = queryAluno.getSingleResult();
			return aluno;

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}
	/**
	 * Consulta os alunos com a conta ativa
	 */
	@Override
	public List<Aluno> consultarAlunosAtivos() {

		  EntityTransaction tx = getEntityManager().getTransaction();
		  try {
		   String sql = "SELECT a FROM Aluno a WHERE  a.situacao = 'ATIVO'";
		   TypedQuery<Aluno> queryAluno = this.entityManager.createQuery(
		     sql, Aluno.class);

		   return queryAluno.getResultList();

		  } catch (Exception e) {
		   e.printStackTrace();
		   if (tx != null && tx.isActive()) {
		    tx.rollback();
		   }
		  }
		  return null;
		 }
	/**
	 * Consulta os alunos com a conta inativos
	 */
	@Override
	public List<Aluno> consultarAlunosInativos() {

		  EntityTransaction tx = getEntityManager().getTransaction();
		  try {
		   String sql = "SELECT a FROM Aluno a WHERE  a.situacao = 'INATIVO'";
		   TypedQuery<Aluno> queryAluno = this.entityManager.createQuery(
		     sql, Aluno.class);

		   return queryAluno.getResultList();

		  } catch (Exception e) {
		   e.printStackTrace();
		   if (tx != null && tx.isActive()) {
		    tx.rollback();
		   }
		  }
		  return null;
		 }
}
