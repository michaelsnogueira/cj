package br.com.prj1.cj.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.prj1.cj.domain.Cidade;
import br.com.prj1.cj.domain.Estado;

public class CidadeDAOTest implements GenericCRUD {

	// @Test
	@Ignore
	public void salvar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(4L);

		Cidade cidade = new Cidade();
		cidade.setEstado(estado);
		cidade.setNome("Teste1");

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);

	}

	// @Test
	@Ignore
	@Override
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();

		List<Cidade> cidades = cidadeDAO.listar();

		for (Cidade cidade : cidades) {
			System.out.println(cidade.getNome());
		}
	}

	// @Test
	@Ignore
	@Override
	public void listarOrdenada() {
		CidadeDAO cidadeDAO = new CidadeDAO();

		List<Cidade> cidades = cidadeDAO.listar("nome");
		Cidade cidade;
		for (int cont = 1; cont <= cidades.size(); cont++) {
			cidade = cidades.get(cont);
			System.out.println(cidade.getNome());
		}

	}

	// @Test
	@Ignore
	@Override
	public void buscarPorCodigo() {
		CidadeDAO cidadeDAO = new CidadeDAO();

		Cidade cidade = cidadeDAO.buscar(1L);
		System.out.println(cidade.getNome());

	}

	// @Test
	@Ignore
	@Override
	public void editar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(6L);

		cidade.setNome("Marilhia");

		cidadeDAO.editar(cidade);

	}

	@Test
	@Override
	public void excluir() {

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(9L);
		cidadeDAO.excluir(cidade);

	}

	@Override
	public void merge() {
		// TODO Auto-generated method stub

	}

}
