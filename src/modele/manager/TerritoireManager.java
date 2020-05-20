package modele.manager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import modele.Territoire;
import util.HibernateUtil;
/**
 * Manager Territoire (Connexion à la BD)
 * 
 * @author Jean-Samuel Girard & Benjamin Couillard-Dagneau
 *
 */
public class TerritoireManager {

	public void ajouterTerritoire(String territoire) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		session.save(new Territoire(0, territoire));
		if (session.getTransaction().isActive())
			session.getTransaction().commit();
	}

	public void modifierTerritoire(String territoirebefore, String territoireafter) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		Territoire temp = (Territoire) session.createCriteria(Territoire.class)
				.add(Restrictions.eq("nom", territoirebefore)).uniqueResult();
		temp.setNom(territoireafter);
		session.update(temp);
		if (session.getTransaction().isActive())
			session.getTransaction().commit();
	}

	public List<Territoire> getTerritoires() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		List<Territoire> territoires = session.createCriteria(Territoire.class).list();
		if (session.getTransaction().isActive())
			session.getTransaction().commit();
		return territoires;
	}

	public Territoire getTerritoire(String territoire) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		Territoire t;
		try {
			t = (Territoire) session.createQuery("FROM Territoire t where t.nom='" + territoire + "'")
					.getSingleResult();
		} catch (Exception e) {
			t = null;
		}
		if (session.getTransaction().isActive())
			session.getTransaction().commit();
		return t;
	}

}
