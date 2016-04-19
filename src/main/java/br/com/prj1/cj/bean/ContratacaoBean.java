package br.com.prj1.cj.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.prj1.cj.dao.CuidadorDAO;
import br.com.prj1.cj.domain.Contratacao;
import br.com.prj1.cj.domain.Cuidador;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ContratacaoBean implements Serializable {
	private List<Cuidador> cuidadores;
	private Cuidador cuidador;	
	private Contratacao contratacao;
	
	public List<Cuidador> getCuidadores() {
		return cuidadores;
	}

	public void setCuidadores(List<Cuidador> cuidadores) {
		this.cuidadores = cuidadores;
	}
	
	public Cuidador getCuidador() {
		return cuidador;
	}
	
	public void setCuidador(Cuidador cuidador) {
		this.cuidador = cuidador;
	}
	
	public Contratacao getContratacao() {
		return contratacao;
	}
	
	public void setContratacao(Contratacao contratacao) {
		this.contratacao = contratacao;
	}

	@PostConstruct
	public void listarCuidadores() {
		cuidador = new Cuidador();
		contratacao = new Contratacao();
		
		CuidadorDAO cuidadorDAO = new CuidadorDAO();

		try {
			cuidadores = cuidadorDAO.listar();

			for (Cuidador cuidador : cuidadores) {
				System.out.println("Nome Cuidador: " + cuidador.getPessoa().getNome());
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os Cuidadores");
			erro.printStackTrace();
		}
	}
	
	public void buscaCuidador(ActionEvent evento) {
		cuidador = (Cuidador) evento.getComponent().getAttributes().get("selecionaCuidador");
	}
}
