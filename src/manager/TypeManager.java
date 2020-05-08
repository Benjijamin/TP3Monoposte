package manager;

import org.hibernate.Session;

import modele.Type;
import util.HibernateUtil;

public class TypeManager {
	
	public void ajouterType(String type) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(new Type(0,type));
		session.getTransaction().commit();
	}
	
}
