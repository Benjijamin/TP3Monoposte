package modele;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Queue;

import manager.PermisManager;

public class Permis {
	private int numero;
	private Territoire territoire;
	private Date dateDebut;
	private Date dateFin;
	private Animal animal;

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
	public static void creerPermisDB(List<String> data) {
		PermisManager manager = new PermisManager();

		System.out.println(data);
		int numero = Integer.parseInt(data.get(0));

		// TODO
		data.get(1);
		Territoire territoire = null;

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

		// TODO
		data.get(5);
		Type type = null;

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

		Animal a = new Animal(nom,type,sexe,poids,dateNaissance,couleur,vaccine,sterelise,micropuce,dangereux);
		Permis p = new Permis(numero,territoire,dateDebut,dateFin,a);
        System.out.println(p);
		//manager.ajouterPermis(p);
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
		// TODO
		return null;
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
