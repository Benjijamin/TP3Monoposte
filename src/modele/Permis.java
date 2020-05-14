package modele;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Queue;

import javax.persistence.NoResultException;

import org.hibernate.ObjectNotFoundException;

import modele.manager.PermisManager;
import modele.manager.TerritoireManager;

public class Permis {
	private int numero;
	private Territoire territoire;
	private Date dateDebut;
	private Date dateFin;
	private Animal animal;
	private PermisManager permisManager = new PermisManager();
	private TerritoireManager territoireManager = new TerritoireManager();

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
	 * Constructeur pour la lecture de CSV, donc quand le territoire et le type sont
	 * recus en string
	 * 
	 * @param numero
	 * @param territoire
	 * @param dateDebut
	 * @param dateFin
	 */
	public Permis(int numero, String territoire, Date dateDebut, Date dateFin, Animal animal) {
		setNumero(numero);
		setTerritoire(validerTerritoire(territoire));
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setAnimal(animal);
	}

	/**
	 * Ajoute un permis dans la DB à partir d'une liste de string
	 */
	public void creerPermisDB(List<String> data) {
		Animal a = new Animal();
		Permis p = new Permis();

		int numero = Integer.parseInt(data.get(0));

		Territoire territoire;
		try {
			territoire = validerTerritoire(data.get(1));
		} catch (NoResultException e) {
			territoire = null;
		}

		Date dateDebut;
		try {
			dateDebut = Date.valueOf(LocalDate.parse(data.get(2)));
		} catch (DateTimeException e) {
			dateDebut = new Date(System.currentTimeMillis());
		}

		Date dateFin;
		try {
			dateFin = Date.valueOf(LocalDate.parse(data.get(3)));
			if (dateFin.getTime() < dateDebut.getTime()) {
				dateFin = new Date(dateDebut.getTime() + 31536000000l);
			}
		} catch (DateTimeException e) {
			dateFin = new Date(dateDebut.getTime() + 31536000000l);
		}

		String nom = data.get(4);

		Type type;
		try {
			type = a.validerType(data.get(5));
		} catch (NoResultException e) {
			type = null;
		}
		
		String sexe = data.get(6);

		float poids = Float.parseFloat(data.get(7).replace(',', '.'));

		Date dateNaissance;
		try {
			dateNaissance = Date.valueOf(LocalDate.parse(data.get(8)));
		} catch (DateTimeException e) {
			dateNaissance = dateDebut;
		}

		String couleur = data.get(9);

		boolean vaccine;
		try {
			vaccine = Integer.parseInt(data.get(10)) == 1;
		} catch (NumberFormatException e) {
			vaccine = false;
		}
		boolean sterelise;
		try {
			sterelise = Integer.parseInt(data.get(11)) == 1;
		} catch (NumberFormatException e) {
			sterelise = false;
		}
		boolean micropuce;
		try {
			micropuce = Integer.parseInt(data.get(12)) == 1;
		} catch (NumberFormatException e) {
			micropuce = false;
		}
		boolean dangereux;
		try {
			dangereux = Integer.parseInt(data.get(13)) == 1;
		} catch (NumberFormatException e) {
			dangereux = false;
		}

		a = new Animal(nom, type, sexe, poids, dateNaissance, couleur, vaccine, sterelise, micropuce, dangereux);
		p = new Permis(numero, territoire, dateDebut, dateFin, a);
		permisManager.ajouterPermis(p);
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
			byte[] bytes = terr.getBytes(Charset.forName("windows-1252"));
			Territoire t = territoireManager.getTerritoire(new String(bytes,StandardCharsets.UTF_8));
			return t;
		} catch (ObjectNotFoundException e) {
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

}
