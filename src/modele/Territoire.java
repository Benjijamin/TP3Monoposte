package modele;

import java.util.List;
import java.util.Set;
import javax.persistence.NoResultException;
import modele.manager.TerritoireManager;
/**
 * Modèle Territoire
 * 
 * @author Jean-Samuel Girard & Benjamin Couillard-Dagneau
 *
 */
public class Territoire {
	private int id;
	private String nom;
	static TerritoireManager manager = new TerritoireManager();

	public Territoire() {
	}

	public Territoire(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public Territoire(String nom) {
		this.nom = nom;
	}

	public static void importerTerritoires(Set<String> territoires) {
		for (String territoire : territoires) {
			if (!territoire.isEmpty()) {
				manager.ajouterTerritoire(territoire);
			}
		}
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

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}

	public static List<Territoire> getTerritoires() {
		return manager.getTerritoires();
	}

	public static void updateTerritoire(String territoirebefore, String territoireafter) {
		manager.modifierTerritoire(territoirebefore, territoireafter);
	}

	public static void ajouterTerritoire(String territoire) {
		manager.ajouterTerritoire(territoire);
	}
	
	/**
	 * Valide la string recu et l'associe au bon objet Territoire
	 * 
	 * @param terr
	 * @return
	 */
	public static Territoire validerTerritoire(String terr) {
		try {
			Territoire t = manager.getTerritoire(terr);
			return t;
		} catch (NoResultException e) {
			System.err.println("territoire non existant");
			return null;
		}
	}

}
