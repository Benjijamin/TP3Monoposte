package controleur;

import javafx.scene.Scene;
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

	
	
}
