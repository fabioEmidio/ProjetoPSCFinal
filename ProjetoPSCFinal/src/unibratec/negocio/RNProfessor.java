package unibratec.negocio;

import java.util.Date;
import java.util.List;

import unibratec.basica.Coordenador;
import unibratec.basica.Professor;
import unibratec.basica.Situacao;
import unibratec.dao.dominio.IDAOProfessor;
import unibratec.dao.generico.DAOFactory;

@SuppressWarnings("unchecked")
public class RNProfessor {

	@SuppressWarnings("rawtypes")
	IDAOProfessor daoProfessor;

	public RNProfessor() {
		daoProfessor = DAOFactory.getProfessorDao();
	}

	public void inserirProfessor(Professor professor) throws Exception {
		// Professor ATIVO
		professor.setSituacao(Situacao.ATIVO);
		// Nome
		if (professor.getNome().length() > 250) {
			throw new Exception(
					"Nome do professor não pode ser maior que 250 caracteres.");
		}
		if (professor.getNome().length() <= 10) {
			throw new Exception(
					"Nome do professor não pode ser menor que 10 caracteres.");
		}
		// CPF
		if (professor.getCpf().length() > 11) {
			throw new Exception(
					"O campo do CPF não pode ser maior que 11 digitos.");
		}
		if (professor.getCpf().length() < 11) {
			throw new Exception(
					"O campo do CPF não pode ser menor que 11 digitos.");
		}
		// RG
		if (professor.getRg().length() > 7) {
			throw new Exception(
					"O campo do RG não pode ser maior que 7 digitos.");
		}
		if (professor.getRg().length() < 7) {
			throw new Exception(
					"O campo do RG não pode ser menor que 7 digitos.");
		}
		// Matricula
		if (professor.getMatricula().length() > 10) {
			throw new Exception(
					"O campo do Matricula não pode ser maior que 10 digitos.");
		}
		if (professor.getMatricula().length() < 10) {
			throw new Exception(
					"O campo do Matricula não pode ser menor que 10 digitos.");
		}
		// Senha
		if (professor.getSenha().length() > 10) {
			throw new Exception(
					"O campo do Senha não pode ser maior que 10 digitos.");
		}
		if (professor.getSenha().length() < 10) {
			throw new Exception(
					"O campo do Senha não pode ser menor que 10 digitos.");
		}
		// Data de Nascimento
		if (professor.getDataNascimento().toString().equals(null)) {
			professor.setDataNascimento(new Date());
		}
		// Cidade
		if (professor.getEndereco().getCidade().length() < 1) {
			throw new Exception("O campo Cidade não pode ficar em branco.");
		}
		if (professor.getEndereco().getCidade().length() > 50) {
			throw new Exception(
					"O campo Cidade não pode ser maior que 50 caracteres.");
		}
		// Bairro
		if (professor.getEndereco().getBairro().length() < 1) {
			throw new Exception("O campo Bairro não pode ficar em branco.");
		}
		if (professor.getEndereco().getBairro().length() > 100) {
			throw new Exception(
					"O campo Bairro não pode ser maior que 50 caracteres.");
		}
		// Bairro
		if (professor.getEndereco().getLogradouro().length() < 1) {
			throw new Exception("O campo Logradouro não pode ficar em branco.");
		}
		if (professor.getEndereco().getLogradouro().length() > 150) {
			throw new Exception(
					"O campo Logradouro não pode ser maior que 150 caracteres.");
		}
		// Numero
		if (professor.getEndereco().getNumero().length() < 1) {
			throw new Exception("O campo Numero não pode ficar em branco.");
		}
		if (professor.getEndereco().getNumero().length() > 150) {
			throw new Exception(
					"O campo Numero não pode ser maior que 10 caracteres.");
		}
		// Faz a inserção
		daoProfessor.inserir(professor);
	}

	public void alterarProfessor(Professor professor) {
		daoProfessor.alterar(professor);
	}

	public void removerProfessor(Professor professor) {
		professor.setSituacao(Situacao.INATIVO);
		daoProfessor.alterar(professor);
	}

	public Professor consultarProfessorPorId(Integer id) {
		return (Professor) daoProfessor.consultarPorId(id);
	}

	public List<Professor> consultarTodosProfessor() {
		return daoProfessor.consultarTodos();
	}

	public List<Professor> consultarTodosProfessor(Integer indiceInicial,
			Integer quantidade) {
		return daoProfessor.consultarTodos(indiceInicial, quantidade);
	}

	public Professor consultaProfessorPorMatricula(Professor professor) {
		professor = (Professor) daoProfessor
				.consultaProfessorPorMatricula(professor);
		if (professor.getMatricula().equals(null)) {
			throw new NullPointerException();
		} else {
			return professor;
		}
	}

	public Professor consultarLoginProfessor(Professor professor)
			throws NullPointerException {
		professor = (Professor) daoProfessor.consultarLoginProfessor(professor);
		if (professor.getMatricula().equals(null)) {
			throw new NullPointerException();
		} else {
			return professor;
		}
	}

	public Professor efetuarLoginProfessorCoordenador(Professor professor)
			throws NullPointerException {
		professor = consultaProfessorPorMatricula(professor);
		professor = (Professor) daoProfessor
				.efetuarLoginProfessorCoordenador(professor);

		if (professor.getMatricula().equals(null)) {
			throw new NullPointerException();
		}
		if (professor.getCoordenador().equals(Coordenador.NAO)) {
			throw new NullPointerException();
		} else {
			return professor;
		}

	}

	public List<Professor> consultaProfessoresAtivos() {
		return daoProfessor.consultaProfessoresAtivos();
	}

	public List<Professor> consultaProfessoresInativos() {
		return daoProfessor.consultaProfessoresInativos();
	}
}
