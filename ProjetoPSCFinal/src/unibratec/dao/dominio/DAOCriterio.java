package unibratec.dao.dominio;

import javax.persistence.EntityManager;

import unibratec.basica.Criterio;
import unibratec.dao.generico.DAOGenerico;

public class DAOCriterio extends DAOGenerico<Criterio> implements
		IDAOCriterio<Criterio> {

	public DAOCriterio(EntityManager em) {
		super(em);
	}

}
