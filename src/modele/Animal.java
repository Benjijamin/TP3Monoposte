package modele;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.DateTimeException;
import java.util.List;
import java.util.Queue;

import javax.persistence.NoResultException;

import org.hibernate.ObjectNotFoundException;

import modele.manager.AnimalManager;
import modele.manager.TypeManager;

/**
 * Modèle Animal
 * 
 * @author Jean-Samuel Girard & Benjamin Couillard-Dagneau
 *
 */
public class Animal {
	private int id;
	private String nom;
	private Type type;
	private String sexe;
	private float poids;
	private Date dateNaissance;
	private String couleur;
	private boolean vaccine;
	private boolean sterelise;
	private boolean micropuce;
	private boolean dangereux;
	private static TypeManager typeManager = new TypeManager();
	private static AnimalManager animalManager = new AnimalManager();

	public Animal() {}
	
	public Animal(String nom, Type type, String sexe, float poids, Date dateNaissance, String couleur, boolean vaccine,
			boolean sterelise, boolean micropuce, boolean dangereux) {
		setNom(nom);
		setType(type);
		setSexe(sexe);
		setPoids(poids);
		setDateNaissance(dateNaissance);
		setCouleur(couleur);
		setVaccine(vaccine);
		setSterelise(sterelise);
		setMicropuce(micropuce);
		setDangereux(dangereux);
	}

	public Animal(String nom, String type, String sexe, float poids, Date dateNaissance, String couleur,
			boolean vaccine, boolean sterelise, boolean micropuce, boolean dangereux) {
		setNom(nom);
		setType(validerType(type));
		setSexe(sexe);
		setPoids(poids);
		setDateNaissance(dateNaissance);
		setCouleur(couleur);
		setVaccine(vaccine);
		setSterelise(sterelise);
		setMicropuce(micropuce);
		setDangereux(dangereux);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	/**
	 * Set le nom, fixe les erreurs d'encodage
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = validerNom(nom);
	}

	/**
	 * Valide si le nom n'est pas plus long que 40 caractères
	 * 
	 * @param nom
	 * @return nom coupé après 40 caractères
	 */
	public String validerNom(String nom) {
		if (nom.length() > 40) {
			return nom.substring(0, 39);
		}
		return nom;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Valide la string recu et l'associe au bon objet Type
	 * 
	 * @param type
	 * @return
	 */
	public Type validerType(String type) {
		try {
			Type t = typeManager.getType(type);
			return t;
		}catch(NoResultException e) {
			return null;
		}
	}

	public String getSexe() {
		return sexe;
	}

	/**
	 * Set le sexe, fixe les erreurs d'encodage
	 * @param sexe
	 */
	public void setSexe(String sexe) {
		this.sexe = validerSexe(sexe);
	}

	/**
	 * Valide le sexe
	 * 
	 * @param sexe
	 * @return Inconnu si vide
	 */
	public String validerSexe(String sexe) {
		if (sexe == null || sexe == "") {
			return "Inconnu";
		}
		return sexe;
	}

	public float getPoids() {
		return poids;
	}

	public void setPoids(float poids) {
		this.poids = validerPoids(poids);
	}

	/**
	 * Valide que le poids est entre 0 et 500
	 * 
	 * @param poids
	 * @return 0 si poids invalide
	 */
	public float validerPoids(float poids) {
		if (poids < 0 || poids > 500) {
			return 0;
		}
		return poids;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getCouleur() {
		return couleur;
	}

	/**
	 * Set la couleur, fixe les erreurs d'encodage
	 * @param couleur
	 */
	public void setCouleur(String couleur) {
		this.couleur = validerCouleur(couleur);
	}

	/**
	 * Valide que la couleur n'est pas vide
	 * 
	 * @param couleur
	 * @return
	 */
	public String validerCouleur(String couleur) {
		if (couleur == null || couleur.isEmpty()) {
			return "Inconnu";
		} else {
			return couleur;
		}
	}

	public boolean isVaccine() {
		return vaccine;
	}

	public void setVaccine(boolean vaccine) {
		this.vaccine = vaccine;
	}

	public boolean isSterelise() {
		return sterelise;
	}

	public void setSterelise(boolean sterelise) {
		this.sterelise = sterelise;
	}

	public boolean isMicropuce() {
		return micropuce;
	}

	public void setMicropuce(boolean micropuce) {
		this.micropuce = micropuce;
	}

	public boolean isDangereux() {
		return dangereux;
	}

	public void setDangereux(boolean dangereux) {
		this.dangereux = dangereux;
	}

	public static Animal getAnimal(int id) {
		return animalManager.getAnimal(id);
	}
}
