package modele;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class Permis {
	private int numero;
	private Territoire territoire;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private String nom;
	private Type type;
	private String sexe;
	private float poids;
	private LocalDate dateNaissance;
	private String couleur;
	private boolean vaccine;
	private boolean sterelise;
	private boolean micropuce;
	private boolean dangereux;
	
	public Permis() {
		
	}

	public Permis(int numero, Territoire territoire, LocalDate dateDebut, LocalDate dateFin, String nom, Type type, String sexe,
			float poids, LocalDate dateNaissance, String couleur, boolean vaccine, boolean sterelise, boolean micropuce,
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

	public Territoire getTerritoire() {
		return territoire;
	}

	public void setTerritoire(Territoire territoire) {
		this.territoire = territoire;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate localDate) {
		this.dateFin = localDate;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
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

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
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

	@Override
	public String toString() {
		return "Permis [numero=" + numero + ", territoire=" + territoire + ", dateDebut=" + dateDebut + ", dateFin="
				+ dateFin + ", nom=" + nom + ", type=" + type + ", sexe=" + sexe + ", poids=" + poids
				+ ", dateNaissance=" + dateNaissance + ", couleur=" + couleur + ", vaccine=" + vaccine + ", sterelise="
				+ sterelise + ", micropuce=" + micropuce + ", dangereux=" + dangereux + "]";
	}
	
	
	}
