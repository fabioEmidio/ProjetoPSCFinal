package unibratec.util;

import java.util.Calendar;
import java.util.Date;

import unibratec.basica.Aluno;
import unibratec.basica.Criterio;
import unibratec.basica.Endereco;
import unibratec.basica.Professor;
import unibratec.basica.Situacao;
import unibratec.basica.UnidadeFederativa;
import unibratec.fachada.Fachada;
import unibratec.fachada.IFachada;

public class Bot {
	static IFachada fachada;

	static{
	 try {
		executar();
	} catch (Exception e) {

		e.printStackTrace();
	}
	}

	public static void executar() throws Exception{
		fachada = Fachada.getInstance();
		inserirAluno();
		inserirProfessor();
		inserirCriterios();

	}

	public static void inserirAluno() throws Exception {
		for (int i = 0; i < 9; i++) {
			Aluno aluno = new Aluno();
			aluno.setCpf(i+"");
			aluno.setRg(i+"");
			aluno.setDataNascimento(retornaData(31, 12, 2005));
			aluno.setEndereco(retornaEnd());
			aluno.setMatricula(i+"");
			aluno.setNome(i+"");
			aluno.setSenha(i+"");
			aluno.setSituacao(Situacao.ATIVO);
			fachada.inserirAluno(aluno);
		}
	}

	public static void inserirProfessor() throws Exception {
		for (int i = 0; i < 9; i++) {
			Professor professor = new Professor();
			professor.setCpf(i+"");
			professor.setRg(i+"");
			professor.setDataNascimento(retornaData(1, 1, 197 + i));
			professor.setEndereco(retornaEnd());
			professor.setMatricula(i+"");
			professor.setNome(i+"");
			professor.setSenha(i+"");
			professor.setSituacao(Situacao.ATIVO);
			fachada.inserirProfessor(professor);
		}
	}

	public static void inserirCriterios() throws Exception {
		for (int i = 0; i < 9; i++) {
			Criterio criterio = new Criterio();
			criterio.setDescricao("C" + i);
			fachada.inserirCriterio(criterio);
		}
	}

	public static Endereco retornaEnd() {
		Endereco endereco = new Endereco();
		endereco.setBairro("Bairro");
		endereco.setCidade("Cidade");
		endereco.setLogradouro("Logradouro");
		endereco.setNumero("Numero");
		endereco.setComplemento("Complemento");
		endereco.setUnidadeFederativa(UnidadeFederativa.PE);
		return endereco;
	}

	public static Date retornaData(int dia, int mes, int ano) {
		Calendar c = Calendar.getInstance();
		c.set(ano, mes - 1, dia);
		Date data = c.getTime();
		return data;
	}
}
