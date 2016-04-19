package br.com.prj1.cj.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable {

	public String paginaPrincipal() {
		System.out.println("Entrou");
		return "/pages/principal.xhtml?faces-redirect=true";
	}

}
