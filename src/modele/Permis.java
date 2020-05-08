package modele;

import java.sql.Date;

public class Permis {
	private int numero;
	private Territoire territoire;
	private Date dateDebut;
	private Date dateFin;
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

	public Permis() {

	}

	public Permis(int numero, Territoire territoire, Date dateDebut, Date dateFin, String nom, Type type, String sexe,
			float poids, Date dateNaissance, String couleur, boolean vaccine, boolean sterelise, boolean micropuce,
			boolean dangereux) {
		setNumero(numero);
		setTerritoire(territoire);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
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

	/**
	 * Constructeur pour la lecture de CSV, donc quand le territoire et le type sont
	 * recus en string
	 * 
	 * @param numero
	 * @param territoire
	 * @param dateDebut
	 * @param dateFin
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
	public Permis(int numero, String territoire, Date dateDebut, Date dateFin, String nom, String type, String sexe,
			float poids, Date dateNaissance, String couleur, boolean vaccine, boolean sterelise, boolean micropuce,
			boolean dangereux) {
		setNumero(numero);
		setTerritoire(validerTerritoire(territoire));
		setDateDebut(dateDebut);
		setDateFin(dateFin);
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Valide si le nom n'est pas plus long que 40 caractères
	 * @param nom
	 * @return nom coupé après 40 caractères
	 */
	public String validerNom(String nom) {
		if(nom.length() > 40) {
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
		// TODO
		return null;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = validerSexe(sexe);
	}

	/**
	 * Valide le sexe
	 * @param sexe
	 * @return Male si les accents sont brisés, Inconnu si vide
	 */
	public String validerSexe(String sexe) {
		if (sexe == "MÃ¢le") {
			return "Male";
		}
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

	public void setCouleur(String couleur) {
		this.couleur = validerCouleur(couleur);
	}
	
	/**
	 * Valide que la couleur n'est pas vide
	 * @param couleur
	 * @return
	 */
	public String validerCouleur(String couleur) {
		if(couleur == null || couleur.isEmpty()) {
			return "Inconnu";
		}
		else {
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

	@Override
	public String toString() {
		return numero +"";
	}

}
