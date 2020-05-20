package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.*;
import modele.Type;
import modele.manager.TypeManager;

class TypeTests {
	private TypeManager manager = new TypeManager();

	// Test la validation d'une string de type et son retour de la BD
	@Test
	void testValiderType() {
		manager.ajouterType("type1");
		manager.ajouterType("type2");

		Type t1 = Type.validerType("type1");
		Type t2 = Type.validerType("typeInexistant");

		Assert.assertEquals(t1.toString(), "type1");
		Assert.assertNull(t2);
	}

	// Test que l'ajout dans la bd se fait bien et que c'est possible de retourner
	// le type
	@Test
	void testAjouterType() {
		Type.ajouterType("TYPE");

		Type t = Type.validerType("TYPE");

		Assert.assertEquals(t.toString(), "TYPE");
	}

	//Test que le type est réellement modifié quand on l'update
	@Test
	void testUpdateType() {
		Type.ajouterType("CHAT");

		Type t = Type.validerType("CHAT");
		Assert.assertEquals(t.toString(), "CHAT");

		Type.updateType("CHAT", "CHIEN");

		t = Type.validerType("CHAT");
		Assert.assertNull(t);

		t = Type.validerType("CHIEN");
		Assert.assertEquals(t.toString(), "CHIEN");
	}
}
