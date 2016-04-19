package br.com.prj1.cj.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import br.com.prj1.cj.domain.Cuidador;
import br.com.prj1.cj.util.HibernateUtil;

public class CuidadorDAO extends GenericDAO<Cuidador> {
	@SuppressWarnings("unchecked")
	public List<Cuidador> listarOrdenado() {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		List<Cuidador> consulta = new ArrayList<>();

		try {
			Criteria criteria = sessao.createCriteria(Cuidador.class);
			criteria.createAlias("cuidador", "c");
			criteria.addOrder(Order.asc("c.nome"));
			consulta = criteria.list();

			return consulta;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
