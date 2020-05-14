package modele;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import modele.manager.TypeManager;

public class Type {
	private int id;
	private String nom;
	TypeManager manager = new TypeManager();

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
				byte[] bytes = type.getBytes(Charset.forName("windows-1252"));
				manager.ajouterType(new String(bytes, StandardCharsets.UTF_8));
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

}
