package br.com.prj1.cj.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.prj1.cj.domain.Usuario;
import br.com.prj1.cj.util.HibernateUtil; 

public class UsuarioDAO extends GenericDAO<Usuario> {
	public Usuario autenticar(String cpf, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p");

			SimpleHash simpleHash = new SimpleHash("md5", senha);

			consulta.add(Restrictions.eq("p.cpf", cpf));
			consulta.add(Restrictions.eq("senha", simpleHash.toHex()));

			Usuario resultado = (Usuario) consulta.uniqueResult();

			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
