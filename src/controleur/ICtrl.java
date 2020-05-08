package controleur;

import java.util.List;

import javafx.scene.Scene;
import modele.Territoire;
import modele.Type;

public interface ICtrl {
	public Scene getScene();

	public List<Territoire> getTerritoireListe();

	public List<Type> getTypeListe();
}
