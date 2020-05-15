package controleur;

import java.io.File;
import java.util.List;
import java.util.Map;

import javafx.scene.Scene;
import modele.Permis;
import modele.Territoire;
import modele.Type;

public interface ICtrl {
	public Scene getScene();

	public List<String> getTerritoireListe();

	public List<String> getTypeListe();

	public List<String> getPermisListe(int start);
	
	public Map<String,Object> getPermis(String numeroPermis);

	public void supprimer(int permis);
	
	public void importerCSV(File file);
}
