package br.com.prj1.cj.main;

import org.hibernate.Session;

import br.com.prj1.cj.util.HibernateUtil;

public class HibernateUtilTeste {

	public static void main(String[] args) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();

	}

}
