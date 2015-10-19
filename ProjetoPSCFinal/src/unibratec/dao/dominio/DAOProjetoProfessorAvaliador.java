package unibratec.dao.dominio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import unibratec.basica.Professor;
import unibratec.basica.Projeto;
import unibratec.basica.ProjetoProfessorAvaliador;
import unibratec.dao.generico.DAOGenerico;

public class DAOProjetoProfessorAvaliador extends
		DAOGenerico<ProjetoProfessorAvaliador> implements
		IDAOProjetoProfessorAvaliador<ProjetoProfessorAvaliador> {

	public DAOProjetoProfessorAvaliador(EntityManager em) {
		super(em);
	}
	/**
	 * Retorna os criterios que o professro e avaliador para aplicar a nota.
	 */
	@Override
	public List<ProjetoProfessorAvaliador> consultaCriteriosParaNota(
			Professor professor, Projeto projeto) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {

			String sql = "SELECT ppa FROM ProjetoProfessorAvaliador ppa WHERE ppa.id.avaliador = :professor AND ppa.id.projeto = :projeto";
			TypedQuery<ProjetoProfessorAvaliador> query = this.entityManager
					.createQuery(sql, ProjetoProfessorAvaliador.class);
			query.setParameter("professor", professor);
			query.setParameter("projeto", projeto);

			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}
	
	/**
	 * Query responsavel por trazer os dados para realização do calculo notaGeral
	 */
	@Override
	public List<ProjetoProfessorAvaliador> calcularNotas(Projeto projeto) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {

			String sql = "SELECT ppa FROM ProjetoProfessorAvaliador ppa WHERE ppa.id.projeto = :projeto";
			TypedQuery<ProjetoProfessorAvaliador> query = this.entityManager
					.createQuery(sql, ProjetoProfessorAvaliador.class);
			query.setParameter("projeto", projeto);

			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}
	
}
