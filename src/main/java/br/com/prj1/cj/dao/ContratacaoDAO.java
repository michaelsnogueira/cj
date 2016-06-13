package br.com.prj1.cj.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.prj1.cj.domain.Contratacao;
import br.com.prj1.cj.domain.Contratante;
import br.com.prj1.cj.util.HibernateUtil;

public class ContratacaoDAO extends GenericDAO<Contratacao> {

	public Contratante buscarContratante(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria criteria = sessao.createCriteria(Contratante.class);
			criteria.add(Restrictions.eq("pessoa.codigo", codigo));
			Contratante contratante = (Contratante) criteria.uniqueResult();
			return contratante;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
