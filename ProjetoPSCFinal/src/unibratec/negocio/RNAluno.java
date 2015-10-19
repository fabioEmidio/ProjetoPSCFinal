package unibratec.negocio;

import java.util.Date;
import java.util.List;

import unibratec.basica.Aluno;
import unibratec.basica.Situacao;
import unibratec.dao.dominio.IDAOAluno;
import unibratec.dao.generico.DAOFactory;

@SuppressWarnings("unchecked")
public class RNAluno {
	@SuppressWarnings("rawtypes")
	IDAOAluno daoAluno;

	public RNAluno() {
		daoAluno = DAOFactory.getAlunoDAO();
	}

	public void inserirAluno(Aluno aluno) throws Exception {
		// Aluno ATIVO
		aluno.setSituacao(Situacao.ATIVO);
		// Nome
		if (aluno.getNome().length() > 250) {
			throw new Exception(
					"Nome do alumo não pode ser maior que 250 caracteres.");
		}
		if (aluno.getNome().length() <= 10) {
			throw new Exception(
					"Nome do aluno não pode ser menor que 10 caracteres.");
		}
		// CPF
		if (aluno.getCpf().length() > 11) {
			throw new Exception(
					"O campo do CPF não pode ser maior que 11 digitos.");
		}
		if (aluno.getCpf().length() < 11) {
			throw new Exception(
					"O campo do CPF não pode ser menor que 11 digitos.");
		}
		// RG
		if (aluno.getRg().length() > 7) {
			throw new Exception(
					"O campo do RG não pode ser maior que 7 digitos.");
		}
		if (aluno.getRg().length() < 7) {
			throw new Exception(
					"O campo do RG não pode ser menor que 7 digitos.");
		}
		// Matricula
		if (aluno.getMatricula().length() > 10) {
			throw new Exception(
					"O campo do Matricula não pode ser maior que 10 digitos.");
		}
		if (aluno.getMatricula().length() < 10) {
			throw new Exception(
					"O campo do Matricula não pode ser menor que 10 digitos.");
		}
		// Senha
		if (aluno.getSenha().length() > 10) {
			throw new Exception(
					"O campo do Senha não pode ser maior que 10 digitos.");
		}
		if (aluno.getSenha().length() < 10) {
			throw new Exception(
					"O campo do Senha não pode ser menor que 10 digitos.");
		}
		//Data de Nascimento
		if(aluno.getDataNascimento().toString().equals(null)){
			aluno.setDataNascimento(new Date());
		}
		// Cidade
		if (aluno.getEndereco().getCidade().length() < 1) {
			throw new Exception("O campo Cidade não pode ficar em branco.");
		}
		if (aluno.getEndereco().getCidade().length() > 50) {
			throw new Exception(
					"O campo Cidade não pode ser maior que 50 caracteres.");
		}
		// Bairro
		if (aluno.getEndereco().getBairro().length() < 1) {
			throw new Exception("O campo Bairro não pode ficar em branco.");
		}
		if (aluno.getEndereco().getBairro().length() > 100) {
			throw new Exception(
					"O campo Bairro não pode ser maior que 50 caracteres.");
		}
		// Bairro
		if (aluno.getEndereco().getLogradouro().length() < 1) {
			throw new Exception("O campo Logradouro não pode ficar em branco.");
		}
		if (aluno.getEndereco().getLogradouro().length() > 150) {
			throw new Exception(
					"O campo Logradouro não pode ser maior que 150 caracteres.");
		}
		// Numero
		if (aluno.getEndereco().getNumero().length() < 1) {
			throw new Exception("O campo Numero não pode ficar em branco.");
		}
		if (aluno.getEndereco().getNumero().length() > 150) {
			throw new Exception(
					"O campo Numero não pode ser maior que 10 caracteres.");
		}
		// Faz a inserção
		daoAluno.inserir(aluno);
	}

	public void alterarAluno(Aluno aluno){
		daoAluno.alterar(aluno);
	
	}

	public void removerAluno(Aluno aluno) {
		aluno.setSituacao(Situacao.INATIVO);
		daoAluno.alterar(aluno);

	}

	public Aluno consultarAlunoPorId(Integer id) {
		return (Aluno) daoAluno.consultarPorId(id);
	}

	public List<Aluno> consultarTodosAluno() {
		return daoAluno.consultarTodos();
	}

	public List<Aluno> consultarTodosAluno(Integer indiceInicial,
			Integer quantidade) {
		return daoAluno.consultarTodos(indiceInicial, quantidade);
	}

	public Aluno efetuarLoginAluno(Aluno aluno) throws NullPointerException {
		aluno = (Aluno) daoAluno.consultaLogin(aluno);
		if (aluno.getMatricula().equals(null)) {
			throw new NullPointerException();
		} else {
			return aluno;
		}

	}

	public Aluno consultarAlunoPorMatricula(Aluno aluno) {
		aluno = (Aluno) daoAluno.consultaAlunoPorMatricula(aluno);
		if (aluno.getMatricula().equals(null)) {
			throw new NullPointerException();
		} else {
			return aluno;
		}
	}

	public List<Aluno> consultarAlunosAtivos() {
		return daoAluno.consultarAlunosAtivos();
	}
	
	public List<Aluno> consultarAlunosInativos() {
		return daoAluno.consultarAlunosInativos();
	}
	

}
