package br.com.prj1.cj.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Contratacao extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Cuidador cuidador;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Contratante contratante;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataInicioContratacao;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataFimContratacao;

	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal valorFinal;

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

	public Date getDataInicioContratacao() {
		return dataInicioContratacao;
	}

	public void setDataInicioContratacao(Date dataInicioContratacao) {
		this.dataInicioContratacao = dataInicioContratacao;
	}

	public Date getDataFimContratacao() {
		return dataFimContratacao;
	}

	public void setDataFimContratacao(Date dataFimContratacao) {
		this.dataFimContratacao = dataFimContratacao;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

}
