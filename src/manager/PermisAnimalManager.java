package manager;

import org.hibernate.Session;

import modele.Animal;
import modele.Permis;
import util.HibernateUtil;

public class PermisAnimalManager {

	public void ajouterPermis(Permis permis) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		Animal animal = permis.getAnimal();
		session.save(animal);
		session.save(permis);
		session.getTransaction().commit();
	}
}
