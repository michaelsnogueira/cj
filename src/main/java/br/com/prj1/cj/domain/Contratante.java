package br.com.prj1.cj.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Contratante extends GenericDomain {

	@Column(nullable = false)
	private Boolean ePaciente;

	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	@Column(length = 50, nullable = false)
	private String nomePaciente;

	@Column(length = 50, nullable = false)
	private String enderecoPaciente;

	@Column(length = 14, nullable = false)
	private String telefonePaciente;

	@Column(length = 14, nullable = false)
	private String celularPaciente;

	@Column(length = 300, nullable = false)
	private String descricaoPaciente;

	@Column(length = 300, nullable = false)
	private String doencaPaciente;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataNascimentoPaciente;

	public Boolean getePaciente() {
		return ePaciente;
	}

	public void setePaciente(Boolean ePaciente) {
		this.ePaciente = ePaciente;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getEnderecoPaciente() {
		return enderecoPaciente;
	}

	public void setEnderecoPaciente(String enderecoPaciente) {
		this.enderecoPaciente = enderecoPaciente;
	}

	public String getTelefonePaciente() {
		return telefonePaciente;
	}

	public void setTelefonePaciente(String telefonePaciente) {
		this.telefonePaciente = telefonePaciente;
	}

	public String getCelularPaciente() {
		return celularPaciente;
	}

	public void setCelularPaciente(String celularPaciente) {
		this.celularPaciente = celularPaciente;
	}

	public String getDescricaoPaciente() {
		return descricaoPaciente;
	}

	public void setDescricaoPaciente(String descricaoPaciente) {
		this.descricaoPaciente = descricaoPaciente;
	}

	public String getDoencaPaciente() {
		return doencaPaciente;
	}

	public void setDoencaPaciente(String doencaPaciente) {
		this.doencaPaciente = doencaPaciente;
	}

	public Date getDataNascimentoPaciente() {
		return dataNascimentoPaciente;
	}

	public void setDataNascimentoPaciente(Date dataNascimentoPaciente) {
		this.dataNascimentoPaciente = dataNascimentoPaciente;
	}
}
