package unibratec.basica;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class Usuario extends ObjetoGeral {
	
	public Usuario(){
		this.endereco = new Endereco();
	}
	
	@Column(length = 250, nullable = false)
	private String nome;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(unique = true, length = 11, nullable = false)
	private String cpf;

	@Column(length = 7, nullable = false, unique = true)
	private String rg;

	private Endereco endereco;

	@Column(unique = true, nullable = false)
	private String matricula;

	@Column(nullable = false, length = 20)
	private String senha;

	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.cpf == ((Usuario) obj).cpf) {
			return true;
		}else if (this.matricula == ((Usuario) obj).matricula) {
			return true;
		} else if (this.rg == ((Usuario) obj).rg) {
			return true;
		} else if (this.situacao == ((Usuario) obj).situacao) {
			return true;
		} else if (this.nome == ((Usuario) obj).nome) {
			return true;
		} else if (this.senha == ((Usuario) obj).senha) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String toString() {
		return nome;
	}

}
