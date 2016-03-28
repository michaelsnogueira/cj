package br.com.prj1.cj.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Avaliacao extends GenericDomain {

	@Column(length = 1, nullable = false)
	private Integer estrelas;

	@Column(length = 300, nullable = false)
	private String descricao;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Cuidador cuidador;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Contratante contratante;

	public Integer getEstrelas() {
		return estrelas;
	}

	public void setEstrelas(Integer estrelas) {
		this.estrelas = estrelas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Cuidador getCuidador() {
		return cuidador;
	}

	public void setCuidador(Cuidador cuidador) {
		this.cuidador = cuidador;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

}
