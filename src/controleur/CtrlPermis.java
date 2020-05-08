package controleur;

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

	
	@Override
	public List<Territoire> getTerritoireListe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Type> getTypeListe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permis> getPermisListe() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
