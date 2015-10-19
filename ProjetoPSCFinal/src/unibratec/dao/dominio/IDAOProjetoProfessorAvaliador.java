package unibratec.dao.dominio;

import java.util.List;

import unibratec.basica.Professor;
import unibratec.basica.Projeto;
import unibratec.dao.generico.IDAOGenerico;

public interface IDAOProjetoProfessorAvaliador<ProjetoProfessorAvaliador> extends IDAOGenerico<ProjetoProfessorAvaliador> {
	//(AVALIADOR)
	public List<ProjetoProfessorAvaliador> consultaCriteriosParaNota(Professor professor, Projeto projeto);
	//(CALCULO)
	public List<ProjetoProfessorAvaliador> calcularNotas(Projeto projeto);
}
