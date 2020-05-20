package modele;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.hibernate.ObjectNotFoundException;

import javafx.scene.control.RadioButton;
import modele.manager.AnimalManager;
import modele.manager.PermisManager;
import modele.manager.TerritoireManager;
import modele.manager.TypeManager;

/**
 * Modèle Permis
 * 
 * @author Jean-Samuel Girard & Benjamin Couillard-Dagneau
 *
 */
public class Permis {
	private int numero;
	private Territoire territoire;
	private Date dateDebut;
	private Date dateFin;
	private Animal animal;
	private static PermisManager permisManager = new PermisManager();
	private static AnimalManager animalManager = new AnimalManager();
	private static TerritoireManager territoireManager = new TerritoireManager();
	private static TypeManager typeManager = new TypeManager();

	public Permis() {
	}

	public Permis(int numero, Territoire territoire, Date dateDebut, Date dateFin, Animal animal) {
		setNumero(numero);
		setTerritoire(territoire);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setAnimal(animal);
	}

	/**
	 * Ajoute un permis dans la DB à partir d'une liste de string
	 */
	public void creerPermis(String[] data) {
		Animal a = new Animal();
		Permis p = this;

		int numero = Integer.parseInt(data[0]);

		Territoire territoire;
		try {
			territoire = validerTerritoire(data[3]);
		} catch (NoResultException e) {
			territoire = null;
		}

		Date dateDebut;
		try {
			dateDebut = Date.valueOf(LocalDate.parse(data[1]));
		} catch (DateTimeException e) {
			dateDebut = new Date(System.currentTimeMillis());
		}

		Date dateFin;
		try {
			dateFin = Date.valueOf(LocalDate.parse(data[2]));
			if (dateFin.getTime() < dateDebut.getTime()) {
				dateFin = new Date(dateDebut.getTime() + 31536000000l);
			}
		} catch (DateTimeException e) {
			dateFin = new Date(dateDebut.getTime() + 31536000000l);
		}

		String nom = data[5];

		Type type;
		try {
			type = a.validerType(data[4]);
		} catch (NoResultException e) {
			type = null;
		}

		String sexe = data[9];

		float poids = Float.parseFloat(data[14].replace(',', '.'));

		Date dateNaissance;
		try {
			dateNaissance = Date.valueOf(LocalDate.parse(data[11]));
		} catch (DateTimeException e) {
			dateNaissance = dateDebut;
		}

		String couleur = data[10];

		boolean vaccine;
		try {
			vaccine = Integer.parseInt(data[12]) == 1;
		} catch (NumberFormatException e) {
			vaccine = false;
		}
		boolean sterelise;
		try {
			sterelise = Integer.parseInt(data[13]) == 1;
		} catch (NumberFormatException e) {
			sterelise = false;
		}
		boolean micropuce;
		try {
			micropuce = Integer.parseInt(data[15]) == 1;
		} catch (NumberFormatException e) {
			micropuce = false;
		}
		boolean dangereux;
		try {
			dangereux = Integer.parseInt(data[16]) == 1;
		} catch (NumberFormatException e) {
			dangereux = false;
		}

		a = new Animal(nom, type, sexe, poids, dateNaissance, couleur, vaccine, sterelise, micropuce, dangereux);
		p = new Permis(numero, territoire, dateDebut, dateFin, a);
		animalManager.ajouterAnimal(a);
		permisManager.ajouterPermis(p);
	}

	/**
	 * Ajoute un permis dans la DB a partir d'une map provenant de la vue
	 */
	public static void creerPermis(Map<String, Object> data) {
		Permis permis = new Permis();
		Animal animal = new Animal();
		permis.setNumero(Integer.valueOf((String) data.get("numero")));
		permis.setDateDebut(Date.valueOf((LocalDate) data.get("dateDebut")));
		permis.setDateFin(Date.valueOf((LocalDate) data.get("dateFin")));
		permis.setTerritoire(permis.validerTerritoire((String) data.get("territoire")));
		permis.setAnimal(animal);
		animal.setNom((String) data.get("nom"));
		animal.setSexe((String) data.get("sexe"));
		animal.setType(animal.validerType((String) data.get("type")));
		animal.setCouleur((String) data.get("couleur"));
		animal.setDateNaissance(Date.valueOf((LocalDate) data.get("dateNaissance")));
		animal.setPoids(Float.valueOf((String) data.get("poids")));
		animal.setVaccine((boolean) data.get("vaccine"));
		animal.setSterelise((boolean) data.get("sterelise"));
		animal.setMicropuce((boolean) data.get("micropuce"));
		animal.setDangereux((boolean) data.get("dangereux"));
		animalManager.ajouterAnimal(animal);
		permisManager.ajouterPermis(permis);
		System.out.println("succes creation de permis");
	}

	/**
	 * Modifie un formulaire existant avec les donnees
	 * 
	 * @param data : donnees de la vue
	 */
	public static void modifierPermis(Map<String, Object> data) {
		Permis permis = permisManager.getPermis(Integer.parseInt((String) data.get("numero")));
		Animal animal = permis.getAnimal();
		permis.setDateDebut(Date.valueOf((LocalDate) data.get("dateDebut")));
		permis.setDateFin(Date.valueOf((LocalDate) data.get("dateFin")));
		permis.setTerritoire(permis.validerTerritoire((String) data.get("territoire")));
		animal.setNom((String) data.get("nom"));
		animal.setSexe((String) data.get("sexe"));
		animal.setType(animal.validerType((String) data.get("type")));
		animal.setCouleur((String) data.get("couleur"));
		animal.setDateNaissance(Date.valueOf((LocalDate) data.get("dateNaissance")));
		animal.setPoids(Float.valueOf((String) data.get("poids")));
		animal.setVaccine((boolean) data.get("vaccine"));
		animal.setSterelise((boolean) data.get("sterelise"));
		animal.setMicropuce((boolean) data.get("micropuce"));
		animal.setDangereux((boolean) data.get("dangereux"));
		animalManager.modifierAnimal(animal);
		permisManager.modifierPermis(permis);
		System.out.println("succes modification");
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Territoire getTerritoire() {
		return territoire;
	}

	public void setTerritoire(Territoire territoire) {
		this.territoire = territoire;
	}

	/**
	 * Valide la string recu et l'associe au bon objet Territoire
	 * 
	 * @param terr
	 * @return
	 */
	public Territoire validerTerritoire(String terr) {
		try {
			Territoire t = territoireManager.getTerritoire(terr);
			return t;
		} catch (NoResultException e) {
			System.err.println("territoire non existant");
			return null;
		}
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date localDate) {
		this.dateFin = localDate;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	@Override
	public String toString() {
		return numero + "";
	}

	public static List<Permis> getListPermis(int start) {
		return permisManager.getListPermis(start);
	}

	public static Permis getPermis(int id) {
		return permisManager.getPermis(id);
	}

	public static void supprimerPermis(int permis) {
		permisManager.supprimerPermis(permis);
	}

	public static List<Permis> getListPermisFull() {
		return permisManager.getListPermisFull();
	}

	public static int getnext() {
		return permisManager.getnext();

	}

	public static List<Permis> search(int i) {
		return permisManager.search(i);
	}

}
