package br.com.prj1.cj.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.prj1.cj.dao.ContratacaoDAO;
import br.com.prj1.cj.domain.Contratacao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AvaliacaoBean implements Serializable {
	private List<Contratacao> contratacoes;

	public List<Contratacao> getContratacoes() {
		return contratacoes;
	}

	public void setContratacoes(List<Contratacao> contratacoes) {
		this.contratacoes = contratacoes;
	}

	@PostConstruct
	public void listar() {
		ContratacaoDAO contratacaoDAO = new ContratacaoDAO();

		try {
			contratacoes = contratacaoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar Listar as Contratações");
			erro.printStackTrace();
		}
	}
}
