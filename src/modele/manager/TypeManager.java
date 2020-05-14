package modele.manager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import modele.Type;
import util.HibernateUtil;

public class TypeManager {
	
	public void ajouterType(String type) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(new Type(0,type));
		session.getTransaction().commit();
	}
	
	public void modifierType(String typebefore, String typeafter) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Type temp = (Type) session.createCriteria(Type.class).add(Restrictions.eq("nom", typebefore)).uniqueResult();
		temp.setNom(typeafter);
		session.update(temp);
		session.getTransaction().commit();
	}
	
	public List<Type> getTypes(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Type> types = session.createCriteria(Type.class).list();
		session.close();
		return types;
	}
	
}
