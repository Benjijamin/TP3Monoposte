package modele;

import java.sql.Date;

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
		return numero +"";
	}

}
