package manager;

import java.sql.Date;

import org.hibernate.Session;

import modele.Animal;
import modele.Permis;
import modele.Territoire;
import util.HibernateUtil;

public class PermisManager {
	AnimalManager animalManager = new AnimalManager();

	/**
	 * Ajoute le permis et son animal dans la BD
	 * 
	 * @param permis
	 */
	public void ajouterPermis(Permis permis) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		animalManager.ajouterAnimal(permis.getAnimal());
		session.save(permis);
		session.getTransaction().commit();
	}

	/**
	 * @param id
	 * @return Permis correspondant à l'id
	 */
	public Permis getPermis(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		Permis permis = session.load(Permis.class, id);
		session.getTransaction().commit();
		return permis;
	}

	/**
	 * Supprime le permis et son animal de la BD
	 * 
	 * @param id
	 */
	public void supprimerPermis(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		Permis permis = session.load(Permis.class, id);
		session.delete(permis);
		animalManager.supprimerAnimal(permis.getAnimal().getId());
	}

	/**
	 * Modifie le permis dans la BD avec le numero correspondant ainsi que son
	 * Animal
	 * 
	 * @param numero
	 * @param territoire
	 * @param dateDebut
	 * @param dateFin
	 * @param animal
	 */
	public void modifierPermis(int numero, Territoire territoire, Date dateDebut, Date dateFin, Animal animal) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		Permis permis = session.load(Permis.class, numero);
		permis.setDateDebut(dateDebut);
		permis.setDateFin(dateFin);
		permis.setTerritoire(territoire);
		permis.setAnimal(animal);
		animalManager.modifierAnimal(animal);
		session.saveOrUpdate(permis);
		session.getTransaction().commit();
	}

	/**
	 * Modifie le permis dans la BD avec un numero identique au permis en parametre,
	 * avec son Animal
	 * 
	 * @param p
	 */
	public void modifierPermis(Permis p) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		Permis permis = session.load(Permis.class, p.getNumero());
		permis.setDateDebut(p.getDateDebut());
		permis.setDateFin(p.getDateFin());
		permis.setTerritoire(p.getTerritoire());
		permis.setAnimal(p.getAnimal());
		animalManager.modifierAnimal(p.getAnimal());
		session.saveOrUpdate(permis);
		session.getTransaction().commit();
	}
}
