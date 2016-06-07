package br.com.prj1.cj.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.prj1.cj.bean.AutenticacaoBean;
import br.com.prj1.cj.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();
		boolean ehPaginaAutenticacao = paginaAtual.contains("autenticacao.xhtml");
		boolean ehPaginaPrincipal = paginaAtual.contains("principal.xhtml");
		boolean ehPaginaPessoa = paginaAtual.contains("pessoas.xhtml");

		if ((!ehPaginaAutenticacao) && (!ehPaginaPrincipal) && (!ehPaginaPessoa)) {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			if (autenticacaoBean == null) {
				Messages.addFlashGlobalWarn("Favor fazer o Cadastro ou o Login para acessar as demais Funcionalidades");
				Faces.navigate("/pages/principal.xhtml");
				return;
			}
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			System.out.println("Usuário nome: " + usuario);
			
			if (usuario == null) {
				Messages.addFlashGlobalWarn("Favor fazer o Cadastro/Login para acessar as demais Funcionalidades");
				Faces.navigate("/pages/principal.xhtml");
				return;
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
