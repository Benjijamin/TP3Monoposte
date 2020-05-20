package tests;


import org.junit.*;
import org.junit.jupiter.api.Test;
import modele.Territoire;
import modele.manager.TerritoireManager;

class TerritoireTests {
	private TerritoireManager manager = new TerritoireManager();

	private Territoire territoire1 = new Territoire("terr1");


	//Test la validation d'une string de territoire et son retour de la BD
	@Test
	void testValiderTerritoire() {
		manager.ajouterTerritoire("test1");
		manager.ajouterTerritoire("test2");
		manager.ajouterTerritoire("test3");
		
		Territoire t1 = Territoire.validerTerritoire("test2");
		Territoire t2 = Territoire.validerTerritoire("test4");
		
		Assert.assertEquals(t1.toString(), "test2");
		Assert.assertNull(t2);
	}
	
	
	//Test que l'ajout dans la bd d'un territoire se fait bien et qu'il est possible de retourner le terroire aussi
	@Test
	void testAjouterTerritoire() {
		Territoire.ajouterTerritoire(territoire1.toString());

		Territoire t = Territoire.validerTerritoire(territoire1.toString());

		Assert.assertEquals(territoire1.toString(), t.toString());
	}

	//Test que le territoire est réellement modifié lorsqu'on l'update
	@Test
	void testUpdateTerritoire() {
		Territoire.ajouterTerritoire("CANADA");
		
		Territoire t = Territoire.validerTerritoire("CANADA");
		Assert.assertEquals(t.toString(), "CANADA");
		
		Territoire.updateTerritoire("CANADA", "USA");
		
		t = Territoire.validerTerritoire("CANADA");
		Assert.assertNull(t);
		
		t = Territoire.validerTerritoire("USA");
		Assert.assertEquals(t.toString(), "USA");
	}
}
