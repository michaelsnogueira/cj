package br.com.prj1.cj.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.prj1.cj.dao.CidadeDAO;
import br.com.prj1.cj.dao.ContratanteDAO;
import br.com.prj1.cj.dao.EstadoDAO;
import br.com.prj1.cj.dao.PessoaDAO;
import br.com.prj1.cj.domain.Cidade;
import br.com.prj1.cj.domain.Contratante;
import br.com.prj1.cj.domain.Cuidador;
import br.com.prj1.cj.domain.Estado;
import br.com.prj1.cj.domain.Pessoa;
import br.com.prj1.cj.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	private Pessoa pessoa;
	private List<Estado> estados;
	private Estado estado;
	private List<Cidade> cidades;
	private Cuidador cuidador;
	private List<String> periodos;
	private List<String> periodosSelecionados;
	private List<Cuidador> cuidadores;
	private Integer contador;
	private Contratante contratante;
	private Usuario usuario;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Cuidador getCuidador() {
		return cuidador;
	}

	public void setCuidador(Cuidador cuidador) {
		this.cuidador = cuidador;
	}

	public List<String> getPeriodosSelecionados() {
		return periodosSelecionados;
	}

	public void setPeriodosSelecionados(List<String> periodosSelecionados) {
		this.periodosSelecionados = periodosSelecionados;
	}

	public List<String> getPeriodos() {
		return periodos;
	}

	public List<Cuidador> getCuidadores() {
		return cuidadores;
	}

	public void setCuidadores(List<Cuidador> cuidadores) {
		this.cuidadores = cuidadores;
	}

	public Integer getContador() {
		return contador;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@PostConstruct
	public void novo() {
		EstadoDAO estadoDAO = new EstadoDAO();

		pessoa = new Pessoa();
		estado = new Estado();
		cuidador = new Cuidador();
		contratante = new Contratante();
		usuario = new Usuario();

		cuidadores = new ArrayList<>();
		contador = new Integer(0);

		periodos = new ArrayList<>();

		periodos.add("Manhã");
		periodos.add("Tarde");
		periodos.add("Noite");

		try {
			estados = estadoDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar Listar os Estados");
		}
	}

	public void carrega() {
		System.out.println("Entrou no carrega");
		cuidador.setPessoa(pessoa);
		System.out.println("Nome da pessoa: " + cuidador.getPessoa().getNome());

	}

	public void popular() {
		CidadeDAO cidadeDAO = new CidadeDAO();

		try {
			cidades = cidadeDAO.buscarCidade(estado.getCodigo());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar Listar as cidades");
			erro.printStackTrace();
		}
	}

	public void merge() {
		PessoaDAO pessoaDAO = new PessoaDAO();

		try {
			pessoaDAO.merge(pessoa);
			Messages.addGlobalInfo("Pessoa salva com sucesso");
			novo();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar a Pessoa");
		}
	}

	public void listaDisponibilidade() {

		for (String disp : periodosSelecionados) {
			System.out.println("Periodo: " + disp);
		}
	}

	public void armazenaPaciente() {
		System.out.println("Cuidador: " + cuidador.getNomePaciente());
		if (cuidador.getNomePaciente() == "" || cuidador.getPeriodoInicial() == null
				|| cuidador.getPeriodoFinal() == null || cuidador.getTelefonePaciente() == ""
				|| cuidador.getDescricao() == "") {
			Messages.addGlobalError("Para adicionar preencha todos os dados da Experiência!");
		} else {
			cuidadores.add(cuidador);

			cuidador.setNomePaciente(null);
			cuidador.setPeriodoInicial(null);
			cuidador.setPeriodoFinal(null);
			cuidador.setTelefonePaciente(null);
			cuidador.setTrabalhoAqui(null);
			cuidador.setDescricao(null);
			contador = contador + 1;

			for (Cuidador listaCuidador : cuidadores) {
				System.out.println("Nome Paciente: " + listaCuidador.getNomePaciente());
			}

		}

	}

	public void cancelarCadastro() {
		cuidador = new Cuidador();
		cuidadores = new ArrayList<>();
	}

	public void salvaContratante() {
		PessoaDAO pessoaDAO = new PessoaDAO();

		try {
			pessoaDAO.salvar(pessoa);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar a Pessoa");
			erro.printStackTrace();
		}

		usuario.setPessoa(pessoa);
		contratante.setPessoa(pessoa);

		ContratanteDAO contratanteDAO = new ContratanteDAO();

		try {
			contratanteDAO.salvar(contratante);
			novo();
			Messages.addGlobalInfo("O Cadastro foi salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o cadastro");
			erro.printStackTrace();
		}
	}
}
