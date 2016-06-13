package br.com.prj1.cj.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.prj1.cj.dao.ContratacaoDAO;
import br.com.prj1.cj.dao.CuidadorDAO;
import br.com.prj1.cj.dao.PessoaDAO;
import br.com.prj1.cj.domain.Contratacao;
import br.com.prj1.cj.domain.Contratante;
import br.com.prj1.cj.domain.Cuidador;
import br.com.prj1.cj.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ContratacaoBean implements Serializable {
	private List<Cuidador> cuidadores;
	private Cuidador cuidador;
	private Contratacao contratacao;
	private List<Cuidador> cuidadoresTela;
	private List<Cuidador> listaExperiencia;
	private String notificacaoAux;
	private Pessoa pessoa;
	private Contratante contratante;
	private List<Contratacao> contratacoes;

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

	public List<Cuidador> getCuidadoresTela() {
		return cuidadoresTela;
	}

	public void setCuidadoresTela(List<Cuidador> cuidadoresTela) {
		this.cuidadoresTela = cuidadoresTela;
	}

	public List<Cuidador> getListaExperiencia() {
		return listaExperiencia;
	}

	public void setListaExperiencia(List<Cuidador> listaExperiencia) {
		this.listaExperiencia = listaExperiencia;
	}

	public String getNotificacaoAux() {
		return notificacaoAux;
	}

	public void setNotificacaoAux(String notificacaoAux) {
		this.notificacaoAux = notificacaoAux;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public List<Contratacao> getContratacoes() {
		return contratacoes;
	}

	public void setContratacoes(List<Contratacao> contratacoes) {
		this.contratacoes = contratacoes;
	}

	public void novo() {
		contratacao = new Contratacao();
		contratacao.setValorFinal(new BigDecimal("0.00"));
	}

	@PostConstruct
	public void listarCuidadores() {
		cuidador = new Cuidador();
		contratacao = new Contratacao();
		contratacao.setValorFinal(new BigDecimal("0.00"));
		cuidadoresTela = new ArrayList<>();
		notificacaoAux = new String();
		pessoa = new Pessoa();

		CuidadorDAO cuidadorDAO = new CuidadorDAO();

		try {
			cuidadores = cuidadorDAO.listar();

			long codCuidadorAnterior = 0;
			for (Cuidador cuidador : cuidadores) {

				if (cuidador.getPessoa().getCodigo() != codCuidadorAnterior) {
					System.out.println("Nome Cuidador: " + cuidador.getPessoa().getNome());
					codCuidadorAnterior = cuidador.getPessoa().getCodigo();

					System.out.println("Tamanho: " + cuidador.getDisponibilidade().length());
					if (cuidador.getDisponibilidade().length() > 5) {
						if (cuidador.getDisponibilidade().length() < 11) {
							cuidador.setDisponibilidade(cuidador.getDisponibilidade().substring(0, 5).concat("/")
									+ cuidador.getDisponibilidade().substring(5, 10));
						} else
							cuidador.setDisponibilidade(cuidador.getDisponibilidade().substring(0, 5).concat("/")
									+ cuidador.getDisponibilidade().substring(5, 10).concat("/")
									+ cuidador.getDisponibilidade().substring(10, 15));
					}

					cuidadoresTela.add(cuidador);
				}
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os Cuidadores");
			erro.printStackTrace();
		}
	}

	public void buscaCuidador(ActionEvent evento) {
		cuidador = (Cuidador) evento.getComponent().getAttributes().get("selecionaCuidador");
		setNotificacaoAux(cuidador.getPessoa().getNotificacao());
		pessoa = cuidador.getPessoa();
		listaExperiencia = new ArrayList<>();

		for (Cuidador cuidador : cuidadores) {
			if (this.cuidador.getPessoa().equals(cuidador.getPessoa())) {
				listaExperiencia.add(cuidador);
			}
		}
	}

	public void notificaPessoa() {
		PessoaDAO pessoaDAO = new PessoaDAO();

		pessoa.setNotificacao(getNotificacaoAux());

		try {
			pessoaDAO.editar(pessoa);
			listarCuidadores();
			Messages.addGlobalInfo("Notificação enviada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar Enviar a notificação");
			erro.printStackTrace();
		}
	}

	public void contrataCuidador() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);

		if (contratacao.getDataInicioContratacao().before(new Date())
				|| contratacao.getDataInicioContratacao().after(contratacao.getDataFimContratacao())) {
			Messages.addGlobalError(
					"Favor inserir a data no intervalo Correto. Favor inserir a data inicio no próximo dia");
		} else {

			long dt = (contratacao.getDataFimContratacao().getTime() - contratacao.getDataInicioContratacao().getTime())
					+ 3600000;

			dt = dt / 86400000L;

			if (dt == 0) {
				dt = 1;
			} else {
				dt = dt + 1;
			}
			BigDecimal dias = new BigDecimal(dt);

			System.out.println("Dias: " + dt);

			contratacao.setValorFinal(cuidador.getValorDiaContratacao().multiply(dias));

			System.out.println("Valor Total: " + contratacao.getValorFinal());
		}
	}

	public void finalizaContratacao(ActionEvent evento) {
		BigDecimal valor = new BigDecimal("0.00");
		if (contratacao.getValorFinal().equals(valor)) {
			Messages.addGlobalError("Favor escolher as datas da contratação e clicar em Calcular.");
		} else {
//			if (buscaDataDisponivel()) {
				ContratacaoDAO contratacaoDAO = new ContratacaoDAO();
				contratacao.setCuidador(cuidador);

				try {
					contratante = contratacaoDAO.buscarContratante(
							(Long) evento.getComponent().getAttributes().get("selecionaContratante"));
					contratacao.setContratante(contratante);
				} catch (RuntimeException erro) {
					Messages.addGlobalError("Erro ao tentar buscar o Contratante");
				}

				try {
					contratacaoDAO.salvar(contratacao);
					listarCuidadores();
					Messages.addGlobalInfo("Contratacao Efetivada com Sucesso");
				} catch (RuntimeException erro) {
					Messages.addGlobalError("Erro ao tentar efetivar a contratação");
					erro.printStackTrace();
				}
			//}
		}
	}

	public boolean buscaDataDisponivel() {
		ContratacaoDAO contratacaoDAO = new ContratacaoDAO();
		boolean retorno = true;

		try {
			contratacoes = contratacaoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar as Contratações");
			erro.printStackTrace();
		}

		for (Contratacao contratacao : contratacoes) {
			if (this.contratacao.getDataInicioContratacao().before(contratacao.getDataFimContratacao())
					|| this.contratacao.getDataInicioContratacao().after(contratacao.getDataInicioContratacao())) {
				if (contratacao.getDataInicioContratacao().after(new Date()))
					Messages.addGlobalError(
							"Favor marcar no período antes do dia " + contratacao.getDataInicioContratacao().getDay());
				Messages.addGlobalError("Ou depois do dia " + contratacao.getDataFimContratacao().getDate());
				retorno = false;
				break;
			}
		}
		return retorno;
	}

}
