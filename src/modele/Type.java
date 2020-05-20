package modele;

import java.util.List;
import java.util.Set;
import javax.persistence.NoResultException;
import modele.manager.TypeManager;

public class Type {
	private int id;
	private String nom;
	static TypeManager manager = new TypeManager();

	public Type() {
	}

	public Type(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public Type(String nom) {
		this.nom = nom;
	}

	public static void importerTypes(Set<String> types) {
		for (String type : types) {
			if (!type.isEmpty()) {
				manager.ajouterType(type);
			}
		}
	}

	public static List<Type> getTypes() {
		return manager.getTypes();
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

	public static void updateType(String typebefore, String typeafter) {
		manager.modifierType(typebefore, typeafter);
	}

	public static void ajouterType(String type) {
		manager.ajouterType(type);
	}

	/**
	 * Valide la string recu et l'associe au bon objet Type
	 * 
	 * @param type
	 * @return
	 */
	public static Type validerType(String type) {
		try {
			Type t = manager.getType(type);
			return t;
		} catch (NoResultException e) {
			return null;
		}
	}

}
