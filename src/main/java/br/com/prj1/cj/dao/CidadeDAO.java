package br.com.prj1.cj.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.prj1.cj.domain.Cidade;
import br.com.prj1.cj.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {

	@SuppressWarnings("unchecked")
	public List<Cidade> buscarCidade(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Cidade> consulta = new ArrayList<>();

		try {
			Criteria criteria = sessao.createCriteria(Cidade.class);
			criteria.add(Restrictions.eq("estado.codigo", codigo));
			criteria.addOrder(Order.asc("nome"));
			consulta = criteria.list();
			System.out.println(consulta.size());
			return consulta;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
