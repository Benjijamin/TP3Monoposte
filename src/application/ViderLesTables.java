package application;

import org.hibernate.Session;

import util.HibernateUtil;

/**
 * Une Classe avec un Main pour vider les tables (commentez ce que l'on ne veux pas vider)
 * 
 * @author Jean-Samuel Girard & Benjamin Couillard-Dagneau
 *
 */
public class ViderLesTables {
	public static void main(String[] args) {

		// - Territoires -
		viderTerritoire();

		// - Types -
		viderType();

		// - Animaux et Permis -
		viderPermis();

	}

	private static void viderPermis() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.createQuery("DELETE FROM Animal");
		session.createQuery("DELETE FROM Permis");
		session.getTransaction().commit();
	}

	private static void viderType() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.createQuery("DELETE FROM Type");
		session.getTransaction().commit();
	}

	private static void viderTerritoire() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.createQuery("DELETE FROM Territoire");
		session.getTransaction().commit();
	}
}
