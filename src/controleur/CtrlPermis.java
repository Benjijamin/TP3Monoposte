package controleur;

import java.io.File;
import java.util.*;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import modele.*;
import util.CSVioUtil;
import util.XMLioUtil;
import vue.*;

public class CtrlPermis implements ICtrl {

	private IVue vue;

	public CtrlPermis() {
		vue = new VuePermis(this);
	}

	@Override
	public Scene getScene() {
		return vue.getScene();
	}

	/**
	 * Retourne la liste des territoires sous formes de strings pour l'affichage
	 */
	@Override
	public List<String> getTerritoireListe() {
		List<Territoire> temp = Territoire.getTerritoires();
		ArrayList<String> retour = new ArrayList<String>();
		for (Territoire t : temp) {
			retour.add(t.toString());
		}
		return retour;
	}

	/**
	 * Retourne la liste des types sous formes de strings pour l'affichage
	 */
	@Override
	public List<String> getTypeListe() {
		List<Type> temp = Type.getTypes();
		ArrayList<String> retour = new ArrayList<String>();
		for (Type t : temp) {
			retour.add(t.toString());
		}
		return retour;
	}

	/**
	 * Retourne la liste des permis sous formes de strings pour l'affichage
	 */
	@Override
	public List<String> getPermisListe(int start) {
		List<Permis> temp = Permis.getListPermis(start);
		ArrayList<String> retour = new ArrayList<String>();
		for (Permis t : temp) {
			retour.add(t.toString());
		}
		return retour;
	}

	/**
	 *Obtient le permis dans la BD et retourne une map de toutes ses valeurs
	 */
	public Map<String, Object> getPermis(String numeroPermis) {
		Permis p = Permis.getPermis(Integer.parseInt(numeroPermis));
		Animal a = p.getAnimal();
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("numero", p.getNumero());
		values.put("territoire", p.getTerritoire());
		values.put("dateDebut", p.getDateDebut());
		values.put("dateFin", p.getDateFin());
		values.put("nom", a.getNom());
		values.put("type", a.getType());
		values.put("sexe", a.getSexe());
		values.put("poids", a.getPoids());
		values.put("dateNaissance", a.getDateNaissance());
		values.put("couleur", a.getCouleur());
		values.put("vaccine", a.isVaccine());
		values.put("sterelise", a.isSterelise());
		values.put("micropuce", a.isMicropuce());
		values.put("dangereux", a.isDangereux());
		return values;
	}

	public void modifier(Map<String,Object> data) {
		Permis.modifierPermis(data);
	}
	
	public void ajouter(Map<String,Object> data) {
		Permis.creerPermis(data);
	}
	
	@Override
	public void supprimer(int permis) {
		Permis.supprimerPermis(permis);

	}

	public void importerCSV(File file) {
		CSVioUtil util = new CSVioUtil();
		util.read(file);
	}

	@Override
	public void exporterXML(File selected) {
		XMLioUtil.write(Permis.getListPermisFull(), selected.getAbsolutePath());
		
	}

	@Override
	public int getNextNumero() {
		return Permis.getnext();
	}

	@Override
	public List<String> enregistrerTerritoire(List<String> oldListTerritoire, ObservableList<String> items) {
		for(int i = 0; i<oldListTerritoire.size();i++) {
			Territoire.updateTerritoire(oldListTerritoire.get(i),items.get(i));
		}
		for(int i = oldListTerritoire.size();i<items.size();i++) {
			Territoire.ajouterTerritoire(items.get(i));
		}
		return getTerritoireListe();
	}

	@Override
	public List<String> enregistrerType(List<String> oldListType, ObservableList<String> items) {
		for(int i = 0; i<oldListType.size();i++) {
			Type.updateType(oldListType.get(i),items.get(i));
		}
		for(int i = oldListType.size();i<items.size();i++) {
			Type.ajouterType(items.get(i));
		}
		return getTypeListe();
	}

	/**
	 * Retourne la liste des permis qui commence avec le nombre reçu en paramètre
	 */
	@Override
	public List<String> rechercher(int i) {
		List<Permis> temp = Permis.search(i);
		ArrayList<String> retour = new ArrayList<String>();
		for (Permis t : temp) {
			retour.add(t.toString());
		}
		return retour;
	}

}
