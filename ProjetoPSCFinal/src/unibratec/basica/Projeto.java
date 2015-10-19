package unibratec.basica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Projeto extends ObjetoGeral implements Serializable {
	
	public Projeto(){
		participantes = new ArrayList<Aluno>();
		orientador = new Professor();
	}
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@ManyToMany
	private List<Aluno> participantes;
	
	@ManyToOne
	@JoinColumn(name = "prof_orientador")
	private Professor orientador;

	@Temporal(TemporalType.DATE)
	private Date data;
	
	private Double notaGeral;
	
	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aluno> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Aluno> participantes) {
		this.participantes = participantes;
	}

	public Professor getOrientador() {
		return orientador;
	}

	public void setOrientador(Professor orientador) {
		this.orientador = orientador;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getNotaGeral() {
		return notaGeral;
	}

	public void setNotaGeral(Double notaGeral) {
		this.notaGeral = notaGeral;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
	
	@Override
	public String toString() {
		return nome;
	}
}
