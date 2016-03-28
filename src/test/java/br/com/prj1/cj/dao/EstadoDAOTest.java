package br.com.prj1.cj.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.prj1.cj.domain.Estado;

public class EstadoDAOTest {

	// @Test
	@Ignore
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Teste");
		estado.setSigla("TE");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);

	}

	// @Test
	@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();

		List<Estado> consulta = estadoDAO.listar();

		for (Estado retorno : consulta) {

			System.out.println("Estado: " + retorno.getNome());
		}
	}

	// @Test
	@Ignore
	public void listaOrdenada() {
		EstadoDAO estadoDAO = new EstadoDAO();

		List<Estado> consulta = estadoDAO.listar("nome");

		for (Estado retorno : consulta) {

			System.out.println("Estado: " + retorno.getNome());
		}
	}

	// @Test
	@Ignore
	public void buscarPorCodigo() {
		EstadoDAO estadoDAO = new EstadoDAO();

		Estado estado = estadoDAO.buscar(4L);

		System.out.println(estado.getNome());
	}

	// @Test
	@Ignore
	public void editar() {
		EstadoDAO estadoDAO = new EstadoDAO();

		Estado estado = estadoDAO.buscar(5L);

		estado.setNome("Teste1");

		estadoDAO.editar(estado);
	}

	// @Test
	@Ignore
	public void merge() {
		EstadoDAO estadoDAO = new EstadoDAO();

		Estado estado = estadoDAO.buscar(7L);
		estado.setNome("Teste3");

		estadoDAO.merge(estado);

	}

	@Test
	public void excluir() {
		EstadoDAO estadoDAO = new EstadoDAO();

		Estado estado = estadoDAO.buscar(6L);

		estadoDAO.excluir(estado);
	}
}
