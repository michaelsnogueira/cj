package br.com.prj1.cj.util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static final SessionFactory fabricaDeSessoes = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuracao = new Configuration().configure();

			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties())
					.build();

			SessionFactory fabrica = configuracao.buildSessionFactory(registro);
			return fabrica;
		} catch (Throwable erro) {
			System.out.println("Erro ao tentar criar a fabroca de sessão " + erro);
			throw new ExceptionInInitializerError(erro);
		}
	}

	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}
}
