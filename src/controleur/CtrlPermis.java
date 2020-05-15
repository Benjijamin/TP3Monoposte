package controleur;

import java.io.File;
import java.util.*;

import javafx.scene.Scene;
import modele.*;
import modele.manager.*;
import util.CSVioUtil;
import util.XMLioUtil;
import vue.*;

public class CtrlPermis implements ICtrl {

	private TerritoireManager territoirem = new TerritoireManager();
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
		List<Territoire> temp = territoirem.getTerritoires();
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

	public Map<String, Object> getPermis(String numeroPermis) {
		Permis p = Permis.getPermis(Integer.parseInt(numeroPermis));
		Animal a = Animal.getAnimal(p.getAnimal().getId());
		Map<String, Object> values = new HashMap<String, Object>();
		System.out.println(p);
		values.put("numero", p.getNumero());
		values.put("territoire", p.getTerritoire());
		values.put("dateDebut", p.getDateDebut());
		values.put("dateFin", p.getDateFin());
		values.put("nom", a.getNom());
		values.put("type", a.getType().toString());
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

}
