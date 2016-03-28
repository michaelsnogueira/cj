package br.com.prj1.cj.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Cuidador extends GenericDomain {

	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	@Column(nullable = false)
	private Boolean liberado;

	@Column(nullable = false)
	private Character disponibilidade;

	@Column(nullable = false, precision = 4, scale = 2)
	private BigDecimal valorDiaContratacao;

	@Column(nullable = false, length = 50)
	private String nomePaciente;

	@Column(length = 50, nullable = false)
	private String telefonePaciente;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date periodoInicial;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date periodoFinal;

	@Column(nullable = false)
	private Boolean trabalhoAqui;

	@Column(nullable = false, length = 300)
	private String descricao;

	@Column(nullable = false, length = 300)
	private String notificacao;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Boolean getLiberado() {
		return liberado;
	}

	public void setLiberado(Boolean liberado) {
		this.liberado = liberado;
	}

	public Character getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Character disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public BigDecimal getValorDiaContratacao() {
		return valorDiaContratacao;
	}

	public void setValorDiaContratacao(BigDecimal valorDiaContratacao) {
		this.valorDiaContratacao = valorDiaContratacao;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getTelefonePaciente() {
		return telefonePaciente;
	}

	public void setTelefonePaciente(String telefonePaciente) {
		this.telefonePaciente = telefonePaciente;
	}

	public Date getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(Date periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public Date getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(Date periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public Boolean getTrabalhoAqui() {
		return trabalhoAqui;
	}

	public void setTrabalhoAqui(Boolean trabalhoAqui) {
		this.trabalhoAqui = trabalhoAqui;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNotificacao() {
		return notificacao;
	}

	public void setNotificacao(String notificacao) {
		this.notificacao = notificacao;
	}

}
