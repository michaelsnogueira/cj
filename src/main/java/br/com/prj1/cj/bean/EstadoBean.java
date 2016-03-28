package br.com.prj1.cj.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.prj1.cj.dao.EstadoDAO;
import br.com.prj1.cj.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {
	private List<Estado> estados;
	private Estado estado;

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@PostConstruct
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();

		try {
			estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar Listar os Estados");
			erro.printStackTrace();
		}
	}

	public void novo() {
		estado = new Estado();
	}

	public void merge() {
		EstadoDAO estadoDAO = new EstadoDAO();

		try {
			estadoDAO.merge(estado);
			Messages.addGlobalInfo("Estado Salvo com Sucesso!");
			novo();
			estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar Salvar o Estado!");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		estado = (Estado) evento.getComponent().getAttributes().get("selecionaEstado");
		EstadoDAO estadoDAO = new EstadoDAO();

		try {
			estadoDAO.excluir(estado);
			Messages.addGlobalInfo("Estado excluído com Sucesso");
			novo();
			estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o Estado");
		}
	}

	public void buscar(ActionEvent evento) {
		System.out.println("Entrou");
		estado = (Estado) evento.getComponent().getAttributes().get("selecionaEstado");
	}

}
