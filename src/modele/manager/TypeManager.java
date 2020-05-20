package modele.manager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import modele.Type;
import util.HibernateUtil;
/**
 * Manager Type (Connexion à la BD)
 * 
 * @author Jean-Samuel Girard & Benjamin Couillard-Dagneau
 *
 */
public class TypeManager {

	public void ajouterType(String type) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		session.save(new Type(0, type));
		if (session.getTransaction().isActive())
			session.getTransaction().commit();
	}

	public void modifierType(String typebefore, String typeafter) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		Type temp = (Type) session.createCriteria(Type.class).add(Restrictions.eq("nom", typebefore)).uniqueResult();
		temp.setNom(typeafter);
		session.update(temp);
		if (session.getTransaction().isActive())
			session.getTransaction().commit();
	}

	public List<Type> getTypes() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		List<Type> types = session.createCriteria(Type.class).list();
		if (session.getTransaction().isActive())
			session.getTransaction().commit();
		return types;
	}

	public Type getType(String type) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		Type t = (Type) session.createQuery("FROM Type t where t.nom='" + type + "'").getSingleResult();
		if (session.getTransaction().isActive())
			session.getTransaction().commit();
		return t;

	}

}
