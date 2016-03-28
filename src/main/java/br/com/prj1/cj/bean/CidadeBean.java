package br.com.prj1.cj.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.prj1.cj.dao.CidadeDAO;
import br.com.prj1.cj.dao.EstadoDAO;
import br.com.prj1.cj.domain.Cidade;
import br.com.prj1.cj.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {
	private List<Cidade> cidades;
	private Cidade cidade;
	private List<Estado> estados;

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	@PostConstruct
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();

		try {
			cidades = cidadeDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar as 'Cidades'");
			erro.printStackTrace();
		}
	}

	public void novo() {
		cidade = new Cidade();

		EstadoDAO estadoDAO = new EstadoDAO();

		try {
			estados = estadoDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar Listar os 'Estados'");
			erro.printStackTrace();
		}
	}

	public void merge() {
		CidadeDAO cidadeDAO = new CidadeDAO();

		try {
			cidadeDAO.merge(cidade);
			Messages.addGlobalInfo("Cidade Salva com Sucesso");
			cidades = cidadeDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar a 'Cidade'");
			erro.printStackTrace();
		}
	}

}
