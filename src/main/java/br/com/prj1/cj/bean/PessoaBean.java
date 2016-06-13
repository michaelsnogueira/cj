package br.com.prj1.cj.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.prj1.cj.dao.CidadeDAO;
import br.com.prj1.cj.dao.ContratanteDAO;
import br.com.prj1.cj.dao.CuidadorDAO;
import br.com.prj1.cj.dao.EstadoDAO;
import br.com.prj1.cj.dao.PessoaDAO;
import br.com.prj1.cj.dao.UsuarioDAO;
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
	private List<Pessoa> pessoas;

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

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
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
			cuidador = new Cuidador();
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
			pessoas = pessoaDAO.listar();

			for (Pessoa pessoaLista : pessoas) {
				if (pessoaLista.getCpf().equals(this.pessoa.getCpf())) {
					Messages.addGlobalError("Este CPF: " + this.pessoa.getCpf() + " Já existe. Favor fazer o Login");
					return;
				}
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar as pessoas");
			erro.printStackTrace();
		}
		System.out.println("entrou no contratante");
		try {
			pessoaDAO.salvar(pessoa);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar a Pessoa");
			erro.printStackTrace();
		}

		usuario.setPessoa(pessoa);

		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
		usuario.setSenha(hash.toHex());

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		try {
			usuarioDAO.salvar(usuario);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar Usuário");
		}
		contratante.setPessoa(pessoa);

		ContratanteDAO contratanteDAO = new ContratanteDAO();

		try {
			contratanteDAO.salvar(contratante);
			novo();
			Messages.addGlobalInfo("O Cadastro foi salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o cadastro Contratante");
			erro.printStackTrace();
		}

		try {
			Path origem = Paths.get(pessoa.getCaminho());
			Path destino = Paths
					.get("C:/Users/IBM_ADMIN/Documents/Documents/Pessoal/Programacao Java Web/ProgramacaoWeb_v3_SergioDelfino/Desenvolvimento/Workspace/cj/src/main/webapp/resources/images/"
							+ pessoa.getCodigo() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException | NullPointerException erro) {
			Messages.addGlobalWarn("A Imagem não foi salva");
		}
	}

	public void salvaCuidador() {
		PessoaDAO pessoaDAO = new PessoaDAO();

		try {
			pessoas = pessoaDAO.listar();

			for (Pessoa pessoaLista : pessoas) {
				if (pessoaLista.getCpf().equals(this.pessoa.getCpf())) {
					Messages.addGlobalError("Este CPF: " + this.pessoa.getCpf() + " Já existe. Favor fazer o Login");
					return;
				}
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar as pessoas");
			erro.printStackTrace();
		}

		try {
			pessoaDAO.salvar(pessoa);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar a Pessoa");
			erro.printStackTrace();
		}

		usuario.setPessoa(pessoa);

		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
		usuario.setSenha(hash.toHex());

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		try {
			usuarioDAO.salvar(usuario);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar Usuário");
		}

		cuidador.setPessoa(pessoa);
		String dispo = "";
		for (String disp : periodosSelecionados) {
			dispo = dispo + disp;
		}
		cuidador.setDisponibilidade(dispo);
		System.out.println(dispo);

		CuidadorDAO cuidadorDAO = new CuidadorDAO();

		for (Cuidador listaCuidador : cuidadores) {
			cuidador.setNomePaciente(listaCuidador.getNomePaciente());
			cuidador.setTelefonePaciente(listaCuidador.getTelefonePaciente());
			cuidador.setPeriodoInicial(listaCuidador.getPeriodoInicial());
			cuidador.setPeriodoFinal(listaCuidador.getPeriodoFinal());
			cuidador.setDescricao(listaCuidador.getDescricao());

			try {
				cuidadorDAO.salvar(cuidador);
				Path origem = Paths.get(pessoa.getCaminho());
				Path destino = Paths
						.get("C:/Users/IBM_ADMIN/Documents/Documents/Pessoal/Programacao Java Web/ProgramacaoWeb_v3_SergioDelfino/Desenvolvimento/Workspace/cj/src/main/webapp/resources/images/"
								+ pessoa.getCodigo() + ".png");
				Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
				Messages.addGlobalInfo("O Cadastro foi salvo com sucesso");
			} catch (RuntimeException | IOException erro) {
				Messages.addGlobalError("Erro ao tentar salvar o cadastro do Cuidador");
				erro.printStackTrace();
			}
		}
		novo();
	}

	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			pessoa.setCaminho(arquivoTemp.toString());

		} catch (IOException erro) {
			Messages.addGlobalInfo("Ocorreu um erro ao tentar fazer Upload do arquivo");
			erro.printStackTrace();
		}
	}
}
