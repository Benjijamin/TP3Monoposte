package controleur;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import modele.Permis;
import modele.Territoire;
import modele.Type;
import modele.manager.PermisManager;
import modele.manager.TerritoireManager;
import modele.manager.TypeManager;
import vue.IVue;
import vue.VuePermis;

public class CtrlPermis implements ICtrl {

	private TypeManager typem = new TypeManager();
	private PermisManager permism = new PermisManager();
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
		List<Territoire> temp = territoirem.getTerritoire();
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
		List<Type> temp = typem.getTypes();
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
	public List<String> getPermisListe() {
		return new ArrayList<String>();
	}

}
