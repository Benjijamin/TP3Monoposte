package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.*;
import modele.Animal;
import modele.Type;

class AnimalTests {

	Animal a = new Animal();

	// Test que le nom ne depasse pas 40 caracteres
	@Test
	void testValiderNom() {
		String nom1 = "Ce nom est tres long et est meme plus long que 40 caracteres";
		String nom2 = "Ce nom est valide";
		nom1 = a.validerNom(nom1);
		nom2 = a.validerNom(nom2);

		Assert.assertTrue(nom1.length() < 40);
		Assert.assertTrue(nom2.length() < 40);

		Assert.assertEquals(nom1, "Ce nom est tres long et est meme plus l");
		Assert.assertEquals(nom2, "Ce nom est valide");
	}

	// Test que le sexe devient incnnu si il est vide
	@Test
	void testValiderSexe() {
		String sexe1 = "Homme";
		String sexe2 = "Femme";
		String sexe3 = "";
		String sexe4 = null;

		sexe1 = a.validerSexe(sexe1);
		sexe2 = a.validerSexe(sexe2);
		sexe3 = a.validerSexe(sexe3);
		sexe4 = a.validerSexe(sexe4);

		Assert.assertNotEquals(sexe1, "Inconnu");
		Assert.assertNotEquals(sexe2, "Inconnu");
		Assert.assertEquals(sexe3, "Inconnu");
		Assert.assertEquals(sexe4, "Inconnu");

	}

	// Test que les poids invalides tombe a 0
	@Test
	void testValiderPoids() {
		float poids1 = -1;
		float poids2 = 1000;
		float poids3 = 500;
		float poids4 = 20;

		poids1 = a.validerPoids(poids1);
		poids2 = a.validerPoids(poids2);
		poids3 = a.validerPoids(poids3);
		poids4 = a.validerPoids(poids4);

		Assert.assertEquals(poids1, 0, 0);
		Assert.assertEquals(poids2, 0, 0);
		Assert.assertNotEquals(poids3, 0);
		Assert.assertNotEquals(poids4, 0);
	}

	// Test que la couleur devient inconnu si la string est vide
	@Test
	void testValiderCouleur() {
		String couleur1 = "Rouge";
		String couleur2 = "Bleu";
		String couleur3 = "";
		String couleur4 = null;

		couleur1 = a.validerCouleur(couleur1);
		couleur2 = a.validerCouleur(couleur2);
		couleur3 = a.validerCouleur(couleur3);
		couleur4 = a.validerCouleur(couleur4);

		Assert.assertNotEquals(couleur1, "Inconnu");
		Assert.assertNotEquals(couleur2, "Inconnu");
		Assert.assertEquals(couleur3, "Inconnu");
		Assert.assertEquals(couleur4, "Inconnu");

	}
}
