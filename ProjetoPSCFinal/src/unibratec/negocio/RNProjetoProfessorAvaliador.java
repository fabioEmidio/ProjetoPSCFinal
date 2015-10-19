package unibratec.negocio;

import java.util.List;

import unibratec.basica.Criterio;
import unibratec.basica.Professor;
import unibratec.basica.Projeto;
import unibratec.basica.ProjetoProfessorAvaliador;
import unibratec.dao.dominio.IDAOProjetoProfessorAvaliador;
import unibratec.dao.generico.DAOFactory;
//import unibratec.util.ConfiguracaoDoSistema;

@SuppressWarnings("unchecked")
public class RNProjetoProfessorAvaliador {
	@SuppressWarnings("rawtypes")
	IDAOProjetoProfessorAvaliador daoProjetoProfessorAvaliador;

	public RNProjetoProfessorAvaliador() {
		daoProjetoProfessorAvaliador = DAOFactory
				.getProjetoProfessorAvaliador();
	}

	public void inserirProjetoProfessorAvaliador(ProjetoProfessorAvaliador projetoProfessorAvaliador) {
		daoProjetoProfessorAvaliador.inserir(projetoProfessorAvaliador);
	}

	public void alterarProjetoProfessorAvaliador(
			ProjetoProfessorAvaliador projetoProfessorAvaliador) {
		daoProjetoProfessorAvaliador.alterar(projetoProfessorAvaliador);
		try {
			double notaGeral = calcularNotas(projetoProfessorAvaliador.getId().getProjeto());
			if (notaGeral != 0) {
				notaGeral = (notaGeral / ConfiguracaoDoSistema.QTD_ALUNO_POR_PROJETO);
				notaGeral = (notaGeral / ConfiguracaoDoSistema.QTD_CRITERIOS_POR_PROJETO);
				
				if(Math.ceil(notaGeral) > 10){
					notaGeral = 10;
					RNProjeto rn = new RNProjeto();
					Projeto projeto = rn.consultarProjetoPorId(projetoProfessorAvaliador.getId().getProjeto().getCodigo());
					projeto.setNotaGeral(notaGeral);
					rn.alterarProjeto(projeto);
				}else{
					RNProjeto rn = new RNProjeto();
					Projeto projeto = rn.consultarProjetoPorId(projetoProfessorAvaliador.getId().getProjeto().getCodigo());
					projeto.setNotaGeral(notaGeral);
					rn.alterarProjeto(projeto);
				}
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void removerProjetoProfessorAvaliador(
			ProjetoProfessorAvaliador projetoProfessorAvaliador) throws Exception {
		daoProjetoProfessorAvaliador.remover(projetoProfessorAvaliador);

	}

	public ProjetoProfessorAvaliador consultarProjetoProfessorAvaliadorPorId(
			Integer id) {
		return (ProjetoProfessorAvaliador) daoProjetoProfessorAvaliador
				.consultarPorId(id);
	}

	public List<ProjetoProfessorAvaliador> consultarTodosProjetoProfessorAvaliador() {

		return daoProjetoProfessorAvaliador.consultarTodos();
	}

	public List<ProjetoProfessorAvaliador> consultarTodosProjetoProfessorAvaliador(
			Integer indiceInicial, Integer quantidade) {
		return daoProjetoProfessorAvaliador.consultarTodos(indiceInicial,
				quantidade);
	}

	public List<ProjetoProfessorAvaliador> consultaCriteriosParaNota(
			Professor professor, Projeto projeto) {
		return daoProjetoProfessorAvaliador.consultaCriteriosParaNota(
				professor, projeto);
	}

	public Double calcularNotas(Projeto projeto) {
		List<ProjetoProfessorAvaliador> valores = daoProjetoProfessorAvaliador
				.calcularNotas(projeto);
		double notaParaCalcular = 0;
		for (ProjetoProfessorAvaliador ppa : valores) {
			if (ppa.getNota() != null) {
				notaParaCalcular += ppa.getNota();

			} else {
				return notaParaCalcular = 0;
			}
		}
		return notaParaCalcular;

	}
	/**
	 * Verifica e a quantidade de Professores e Criterios não iguais as determinadas na configuração do Sistema.
	 * @param avaliadores
	 * @param criterios
	 * @throws Exception
	 */
	public void verificarQuantidadeDeAvaliadoresComOsCriterios(List<Professor> avaliadores, List<Criterio> criterios) throws Exception{
		if(avaliadores.size() < ConfiguracaoDoSistema.QTD_AVALIADOR_POR_PROJETO){
			throw new Exception("Quantidade de avaliadores menor que a permitida, escolha 4 avaliadores");
		}if(avaliadores.size() > ConfiguracaoDoSistema.QTD_AVALIADOR_POR_PROJETO){
			throw new Exception("Quantidade de avaliadores maior que a permitida, escolha 4 avaliadores");
		}if(criterios.size() < ConfiguracaoDoSistema.QTD_CRITERIOS_POR_PROJETO){
			throw new Exception("Quantidade de criterios menor que a permitida, escolha 4 criterios");
		}if(criterios.size() > ConfiguracaoDoSistema.QTD_CRITERIOS_POR_PROJETO){
			throw new Exception("Quantidade de criterios maior que a permitida, escolha 4 criterios");
		}
	}

}
