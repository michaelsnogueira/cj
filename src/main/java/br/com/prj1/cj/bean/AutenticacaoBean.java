package br.com.prj1.cj.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.prj1.cj.dao.UsuarioDAO;
import br.com.prj1.cj.domain.Pessoa;
import br.com.prj1.cj.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable {

	private Usuario usuario;
	private Usuario usuarioLogado;
	private Pessoa pessoa;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@PostConstruct
	public void novo() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
		pessoa = new Pessoa();
		
	}

	public String autenticar() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		String retorno = null;

		try {
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), usuario.getSenhaSemCriptografia());
			pessoa = usuarioLogado.getPessoa();

			if (usuarioLogado == null) {
				Messages.addGlobalWarn("CPF/Senha, não cadastrado");
				retorno = " ";
			} else {
				Messages.addFlashGlobalInfo("Usuário autenticado com sucesso");
				retorno = "/pages/principal.xhtml?faces-redirect=true";
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar Autenticar.");
			erro.printStackTrace();
		}
		return retorno;
	}

	public String paginaPrincipal() {
		return "/pages/principal.xhtml?faces-redirect=true";
	}

	public String sairAplicacao() {
		if (usuarioLogado == null) {
			Messages.addFlashGlobalInfo("Não foi feito o Login");
			return "/pages/autenticacao.xhtml?faces-redirect=true";
		} else {
			novo();
			usuarioLogado = null;
			System.out.println("Usuario logaddooooo: " + usuarioLogado);
			Messages.addFlashGlobalInfo("Logout Feito com Sucesso");
			return "/pages/principal.xhtml?faces-redirect=true";
		}
	}
}
