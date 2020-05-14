package modele.manager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import modele.Territoire;
import util.HibernateUtil;

public class TerritoireManager {

	public void ajouterTerritoire(String territoire) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(new Territoire(0,territoire));
		session.getTransaction().commit();
	}
	
	public void modifierTerritoire(String territoirebefore, String territoireafter) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Territoire temp = (Territoire) session.createCriteria(Territoire.class).add(Restrictions.eq("nom", territoirebefore)).uniqueResult();
		temp.setNom(territoireafter);
		session.update(temp);
		session.getTransaction().commit();
	}
	
	public List<Territoire> getTerritoire(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Territoire> territoires = session.createCriteria(Territoire.class).list();
		session.close();
		return territoires;
	}
	
}
