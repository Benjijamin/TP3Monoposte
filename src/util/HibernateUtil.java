package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Classe utilitaire pour initialiser et obtenir la session de hibernate
 * 
 * @author Jean-Samuel Girard & Benjamin Couillard-Dagneau
 *
 */
public abstract class HibernateUtil {

	public static final SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initialation de SessionFactory  a échoué." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
