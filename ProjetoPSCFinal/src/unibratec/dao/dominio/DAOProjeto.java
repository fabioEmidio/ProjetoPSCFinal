package unibratec.dao.dominio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import unibratec.basica.Aluno;
import unibratec.basica.Professor;
import unibratec.basica.Projeto;
import unibratec.dao.generico.DAOGenerico;

public class DAOProjeto extends DAOGenerico<Projeto> implements IDAOProjeto<Projeto> {

	public DAOProjeto(EntityManager em) {
		super(em);
	}
	/**
	 * Consulta os alunos participantes dos projetos no qual a situação do projeto esta como ATIVO
	 */
	@Override
	public List<Projeto> consultarAlunoParticipante(Aluno aluno) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "SELECT p FROM Projeto p inner join p.participantes a where a.matricula = :matricula and p.situacao = 'ATIVO'";
			TypedQuery<Projeto> queryProjeto = this.entityManager
					.createQuery(sql, Projeto.class);
			queryProjeto.setParameter("matricula", aluno.getMatricula());
			return queryProjeto.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}
	/**
	 * Consulta os projetos no qual o professor e Orientados e o projeto esta ATIVO
	 */
	@Override
	public List<Projeto> consultarProfessorOrientador(Professor professor) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "SELECT p FROM Projeto p WHERE p.orientador.matricula = :matricula AND p.situacao = 'ATIVO'";
			TypedQuery<Projeto> queryProjeto = this.entityManager
					.createQuery(sql, Projeto.class);
			queryProjeto.setParameter("matricula", professor.getMatricula());
			return queryProjeto.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}
	/**
	 * Consulta os projetos no qual o professor e AVALIADOR
	 * 
	 */
	@Override
	public List<Projeto> consultaProfessorAvaliador(
			Professor professor) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {

			String sql = "SELECT DISTINCT ppa.id.projeto FROM ProjetoProfessorAvaliador ppa where ppa.id.avaliador = :professor and ppa.id.projeto.situacao = 'ATIVO'";
			TypedQuery<Projeto> queryProjeto = this.entityManager
					.createQuery(sql, Projeto.class);
			queryProjeto.setParameter("professor", professor);
			 

			return queryProjeto.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}	
	/**
	 * Consulta todos os projetos ativos
	 */
	@Override
	public List<Projeto> consultarProjetosAtivos() {

	  EntityTransaction tx = getEntityManager().getTransaction();
	  try {
	   String sql = "SELECT p FROM Projeto p WHERE  p.situacao = 'ATIVO'";
	   TypedQuery<Projeto> queryProjeto = this.entityManager.createQuery(
	     sql, Projeto.class);

	   return queryProjeto.getResultList();

	  } catch (Exception e) {
	   e.printStackTrace();
	   if (tx != null && tx.isActive()) {
	    tx.rollback();
	   }
	  }
	  return null;
	 }
	
	/**
	 * Consulta todos os projetos inativos
	 */
	@Override
	public List<Projeto> consultarProjetosInativos() {

	  EntityTransaction tx = getEntityManager().getTransaction();
	  try {
	   String sql = "SELECT p FROM Projeto p WHERE  p.situacao = 'INATIVO'";
	   TypedQuery<Projeto> queryProjeto = this.entityManager.createQuery(
	     sql, Projeto.class);

	   return queryProjeto.getResultList();

	  } catch (Exception e) {
	   e.printStackTrace();
	   if (tx != null && tx.isActive()) {
	    tx.rollback();
	   }
	  }
	  return null;
	 }
	
}
