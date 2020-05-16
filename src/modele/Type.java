package modele;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import modele.manager.TypeManager;
import util.HibernateUtil;

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

	public void importerTypes(Set<String> types) {
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

}
