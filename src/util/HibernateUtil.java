package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
