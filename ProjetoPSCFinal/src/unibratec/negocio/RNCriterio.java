package unibratec.negocio;

import java.util.List;

import unibratec.basica.Criterio;
import unibratec.dao.dominio.IDAOCriterio;
import unibratec.dao.generico.DAOFactory;

@SuppressWarnings("unchecked")
public class RNCriterio {

	@SuppressWarnings("rawtypes")
	IDAOCriterio daoCriterio;

	public RNCriterio() {
		daoCriterio = DAOFactory.getCriterio();
	}

	public void inserirCriterio(Criterio criterio) throws Exception {
		if (criterio.getDescricao().length() < 1) {
			throw new Exception("A descrição não pode ficar em branco.");
		}if (criterio.getDescricao().length() > 100) {
			throw new Exception("A descrição não pode ser maior que 100 caracteres.");
		}
		
		daoCriterio.inserir(criterio);
	}

	public void alterarCriterio(Criterio criterio) {
		daoCriterio.alterar(criterio);
	}

	public void removerCriterio(Criterio criterio) throws Exception {
		daoCriterio.remover(criterio);
	}

	public Criterio consultarCriterioPorId(Integer id) {
		return (Criterio) daoCriterio.consultarPorId(id);
	}

	public List<Criterio> consultarTodosCriterio() {
		return daoCriterio.consultarTodos();
	}

	public List<Criterio> consultarTodosCriterio(Integer indiceInicial,
			Integer quantidade) {
		return daoCriterio.consultarTodos(indiceInicial,
				quantidade);
	}
}
