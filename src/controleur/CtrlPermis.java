package controleur;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import modele.Permis;
import modele.Territoire;
import modele.Type;
import vue.IVue;
import vue.VuePermis;

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
		return new ArrayList<String>();
	}

	/**
	 * Retourne la liste des types sous formes de strings pour l'affichage
	 */
	@Override
	public List<String> getTypeListe() {
		return new ArrayList<String>();
	}

	/**
	 * Retourne la liste des permis sous formes de strings pour l'affichage
	 */
	@Override
	public List<String> getPermisListe() {
		return new ArrayList<String>();
	}

	
	
}
