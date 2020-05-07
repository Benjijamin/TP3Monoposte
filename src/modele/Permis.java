package modele;

import java.util.Date;

public class Permis {
	private int numero;
	private String territoire;
	private Date dateDebut;
	private Date dateFin;
	private String nom;
	private String type;
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

	public Permis(int numero, String territoire, Date dateDebut, Date dateFin, String nom, String type, String sexe,
			float poids, Date dateNaissance, String couleur, boolean vaccine, boolean sterelise, boolean micropuce,
			boolean dangereux) {
		this.numero = numero;
		this.territoire = territoire;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nom = nom;
		this.type = type;
		this.sexe = sexe;
		this.poids = poids;
		this.dateNaissance = dateNaissance;
		this.couleur = couleur;
		this.vaccine = vaccine;
		this.sterelise = sterelise;
		this.micropuce = micropuce;
		this.dangereux = dangereux;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTerritoire() {
		return territoire;
	}

	public void setTerritoire(String territoire) {
		this.territoire = territoire;
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

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public float getPoids() {
		return poids;
	}

	public void setPoids(float poids) {
		this.poids = poids;
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
		this.couleur = couleur;
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
	
	
	}
