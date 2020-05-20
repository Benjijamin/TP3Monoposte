package modele;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

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

	public void importerTerritoires(Set<String> territoires) {
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

}
