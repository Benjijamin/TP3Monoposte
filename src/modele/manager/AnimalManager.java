package modele.manager;

import java.sql.Date;

import org.hibernate.Session;

import modele.Animal;
import modele.Type;
import util.HibernateUtil;

public class AnimalManager {
	/**
	 * Ajoute un Animal à la BD
	 * 
	 * @param animal
	 */
	public void ajouterAnimal(Animal animal) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		session.save(animal);
		if (session.getTransaction().isActive())
			session.getTransaction().commit();
	}

	/**
	 * @param id
	 * @return Animal correspondant à l'id
	 */
	public Animal getAnimal(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		if (!session.getTransaction().isActive())
			session.beginTransaction();
		Animal animal = session.load(Animal.class, id);
		if (session.getTransaction().isActive())
			session.getTransaction().commit();
		return animal;
	}

	/**
	 * Supprime l'animal de la BD
	 * 
	 * @param id
	 */
	public void supprimerAnimal(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		if (!session.getTransaction().isActive())
			session.beginTransaction();
		Animal animal = session.load(Animal.class, id);
		session.delete(animal);
		if (session.getTransaction().isActive())
			session.getTransaction().commit();
	}

	/**
	 * Modifie l'animal dans la BD avec l'id correspondant
	 * 
	 * @param id
	 * @param nom
	 * @param type
	 * @param sexe
	 * @param poids
	 * @param dateNaissance
	 * @param couleur
	 * @param vaccine
	 * @param sterelise
	 * @param micropuce
	 * @param dangereux
	 */
	public void modifierAnimal(int id, String nom, Type type, String sexe, float poids, Date dateNaissance,
			String couleur, boolean vaccine, boolean sterelise, boolean micropuce, boolean dangereux) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		if (!session.getTransaction().isActive())
			session.beginTransaction();
		Animal animal = session.load(Animal.class, id);
		animal.setCouleur(couleur);
		animal.setDateNaissance(dateNaissance);
		animal.setNom(nom);
		animal.setPoids(poids);
		animal.setSexe(sexe);
		animal.setType(type);
		animal.setDangereux(dangereux);
		animal.setSterelise(sterelise);
		animal.setVaccine(vaccine);
		animal.setMicropuce(micropuce);
		session.saveOrUpdate(animal);
		if (session.getTransaction().isActive())
			session.getTransaction().commit();
	}

	/**
	 * Modifie l'animal dans la BD avec un id identique à l'animal en paramètre
	 * 
	 * @param animal
	 */
	public void modifierAnimal(Animal a) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		if (!session.getTransaction().isActive())
			session.beginTransaction();
		Animal animal = session.load(Animal.class, a.getId());
		animal.setCouleur(a.getCouleur());
		animal.setDateNaissance(a.getDateNaissance());
		animal.setNom(a.getNom());
		animal.setPoids(a.getPoids());
		animal.setSexe(a.getSexe());
		animal.setType(a.getType());
		animal.setDangereux(a.isDangereux());
		animal.setSterelise(a.isSterelise());
		animal.setVaccine(a.isVaccine());
		animal.setMicropuce(a.isMicropuce());
		session.saveOrUpdate(animal);
		if (session.getTransaction().isActive())
			session.getTransaction().commit();
	}
}
