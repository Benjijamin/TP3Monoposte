package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import modele.*;
import util.HibernateUtil;

class PermisTests {

	// Test que l'on peux bien obtenir un permis
	@Test
	void testGetPermis() {
		Permis.creerPermis(new String[] { "177393", "2019-07-30", "2020-07-30", "Fabreville", "Chien", "Maggy",
				"Chiens de compagnie", "Shih Tzu", "", "Femelle", "Blanc", "0000-00-00", "0", "1", "5", "0", "0" });

		Permis permis = Permis.getPermis(177393);
		Assert.assertNotNull(permis);
		Assert.assertEquals(permis.getNumero(), 177393);
	}

	// Test que la methode creer correctement le permis et que toutes les valeurs
	// sont bien rentrees
	@Test
	void testCreerPermisStringArray() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.createQuery("DELETE FROM Type").executeUpdate();
		s.createQuery("DELETE FROM Territoire").executeUpdate();
		s.createQuery("DELETE FROM Permis").executeUpdate();
		s.createQuery("DELETE FROM Animal").executeUpdate();

		Territoire.ajouterTerritoire("Sainte-Rose");
		Type.ajouterType("Chien");
		Territoire terr = Territoire.validerTerritoire("Sainte-Rose");
		Type type = Type.validerType("Chien");

		String[] array = new String[] { "164088", "2019-04-25", "2020-04-25", "Sainte-Rose", "Chien", "Kangy",
				"Lévriers et chiens courants", "Levrier anglais", "", "Mâle", "Noir", "2011-05-08", "0", "1", "38,6",
				"1", "0" };
		Permis.creerPermis(array);

		Animal animalComparaison = new Animal("Kangy", type, "Mâle", 38.6f, Date.valueOf("2011-05-08"), "Noir", false,
				true, true, false);
		// Les Ids d'animaux commencent a 1
		animalComparaison.setId(1);
		Permis permisComparaison = new Permis(164088, terr, Date.valueOf("2019-04-25"), Date.valueOf("2020-04-25"),
				animalComparaison);

		Permis permis = Permis.getPermis(164088);
		Assert.assertEquals(permis.getNumero(), permisComparaison.getNumero());
		Assert.assertEquals(permis.getTerritoire().toString(), permisComparaison.getTerritoire().toString());
		Assert.assertEquals(permis.getDateDebut(), permisComparaison.getDateDebut());
		Assert.assertEquals(permis.getDateFin(), permisComparaison.getDateFin());
		Assert.assertEquals(permis.getAnimal().getCouleur(), animalComparaison.getCouleur());
		Assert.assertEquals(permis.getAnimal().getNom(), animalComparaison.getNom());
		Assert.assertEquals(permis.getAnimal().getPoids(), animalComparaison.getPoids(), 0);
		Assert.assertEquals(permis.getAnimal().getSexe(), animalComparaison.getSexe());
		Assert.assertEquals(permis.getAnimal().getDateNaissance(), animalComparaison.getDateNaissance());
		Assert.assertEquals(permis.getAnimal().getType().toString(), animalComparaison.getType().toString());
		Assert.assertEquals(permis.getAnimal().isVaccine(), animalComparaison.isVaccine());
		Assert.assertEquals(permis.getAnimal().isSterelise(), animalComparaison.isSterelise());
		Assert.assertEquals(permis.getAnimal().isMicropuce(), animalComparaison.isMicropuce());
		Assert.assertEquals(permis.getAnimal().isDangereux(), animalComparaison.isDangereux());
		s.close();
	}

	// Test que la creation a partir de map fonctionne aussi
	@Test
	void testCreerPermisMapOfStringObject() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.createQuery("DELETE FROM Type").executeUpdate();
		s.createQuery("DELETE FROM Territoire").executeUpdate();
		s.createQuery("DELETE FROM Permis").executeUpdate();
		s.createQuery("DELETE FROM Animal").executeUpdate();

		Territoire.ajouterTerritoire("Duvernay");
		Type.ajouterType("Chat");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("numero", "66930");
		map.put("dateDebut", LocalDate.parse("2019-08-12"));
		map.put("dateFin", LocalDate.parse("2020-08-12"));
		map.put("territoire", "Duvernay");
		map.put("nom", "Robertino");
		map.put("type", "Chat");
		map.put("Sexe", "Inconnu");
		map.put("couleur", "");
		map.put("dateNaissance", LocalDate.parse("2010-01-01"));
		map.put("poids", "7");
		map.put("vaccine", false);
		map.put("sterelise", true);
		map.put("micropuce", false);
		map.put("dangereux", false);

		Permis.creerPermis(map);

		Permis permis = Permis.getPermis(66930);

		Assert.assertEquals(permis.getNumero(), 66930);
		Assert.assertEquals(permis.getTerritoire().toString(), "Duvernay");
		Assert.assertEquals(permis.getDateDebut(), Date.valueOf("2019-08-12"));
		Assert.assertEquals(permis.getDateFin(), Date.valueOf("2020-08-12"));
		Assert.assertEquals(permis.getAnimal().getCouleur(), "Inconnu");
		Assert.assertEquals(permis.getAnimal().getNom(), "Robertino");
		Assert.assertEquals(permis.getAnimal().getPoids(), 7, 0);
		Assert.assertEquals(permis.getAnimal().getSexe(), "Inconnu");
		Assert.assertEquals(permis.getAnimal().getDateNaissance(), Date.valueOf("2010-01-01"));
		Assert.assertEquals(permis.getAnimal().getType().toString(), "Chat");
		Assert.assertEquals(permis.getAnimal().isVaccine(), false);
		Assert.assertEquals(permis.getAnimal().isSterelise(), true);
		Assert.assertEquals(permis.getAnimal().isMicropuce(), false);
		Assert.assertEquals(permis.getAnimal().isDangereux(), false);

		s.close();
	}

	// Test que GetListPermis retourne bien la liste complete des permis
	@Test
	void testGetListPermis() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.createQuery("DELETE FROM Type").executeUpdate();
		s.createQuery("DELETE FROM Territoire").executeUpdate();
		s.createQuery("DELETE FROM Permis").executeUpdate();
		s.createQuery("DELETE FROM Animal").executeUpdate();

		Permis.creerPermis(new String[] { "176302", "2019-03-20", "2020-03-20", "Sainte-Dorothée", "Chien", "Angus",
				"Chiens de travail", "Bouvier bernois", "Caniche moyen", "Mâle", "Beige", "2016-02-08", "0", "1", "35",
				"1", "0" });
		Permis.creerPermis(new String[] { "175874", "2019-01-15", "2020-01-14", "Fabreville", "Chien", "Chika",
				"Chiens de compagnie", "Shih Tzu", "", "Femelle", "Noir et blanc", "2018-10-01", "0", "1", "2", "1",
				"0" });
		Permis.creerPermis(new String[] { "68252", "2019-05-07", "2020-05-07", "Saint-Vincent-de-Paul", "Chat", "Toby",
				"", "", "", "Mâle", "Roux", "2007-06-01", "0", "1", "6,5", "1", "0" });

		List<Permis> liste = Permis.getListPermisFull();

		Assert.assertEquals(liste.size(), 3);

		s.close();
	}

	// Test que le permis est enleve de la BD
	@Test
	void testSupprimerPermis() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.createQuery("DELETE FROM Type").executeUpdate();
		s.createQuery("DELETE FROM Territoire").executeUpdate();
		s.createQuery("DELETE FROM Permis").executeUpdate();
		s.createQuery("DELETE FROM Animal").executeUpdate();

		Permis.creerPermis(new String[] { "176302", "2019-03-20", "2020-03-20", "Sainte-Dorothée", "Chien", "Angus",
				"Chiens de travail", "Bouvier bernois", "Caniche moyen", "Mâle", "Beige", "2016-02-08", "0", "1", "35",
				"1", "0" });

		Permis p = Permis.getPermis(176302);
		Assert.assertNotNull(p);

		Permis.supprimerPermis(176302);
		p = Permis.getPermis(176302);
		try {
			p.toString();
			Assert.fail("Il devrait y avoir une exception");
		}catch(Exception e) {
		}
		s.close();
	}

	// Test que la taile de la liste de permis retournés correspond bien
	@Test
	void testSearch() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.createQuery("DELETE FROM Type").executeUpdate();
		s.createQuery("DELETE FROM Territoire").executeUpdate();
		s.createQuery("DELETE FROM Permis").executeUpdate();
		s.createQuery("DELETE FROM Animal").executeUpdate();

		Permis.creerPermis(new String[] { "176302", "2019-03-20", "2020-03-20", "Sainte-Dorothée", "Chien", "Angus",
				"Chiens de travail", "Bouvier bernois", "Caniche moyen", "Mâle", "Beige", "2016-02-08", "0", "1", "35",
				"1", "0" });
		Permis.creerPermis(new String[] { "175874", "2019-01-15", "2020-01-14", "Fabreville", "Chien", "Chika",
				"Chiens de compagnie", "Shih Tzu", "", "Femelle", "Noir et blanc", "2018-10-01", "0", "1", "2", "1",
				"0" });
		Permis.creerPermis(new String[] { "68252", "2019-05-07", "2020-05-07", "Saint-Vincent-de-Paul", "Chat", "Toby",
				"", "", "", "Mâle", "Roux", "2007-06-01", "0", "1", "6,5", "1", "0" });

		List<Permis> liste = Permis.search(17);

		Assert.assertEquals(liste.size(), 2);

		s.close();
	}

}
