package controleur;

import java.io.File;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import modele.Permis;
import modele.Territoire;
import modele.Type;

public interface ICtrl {
	public Scene getScene();

	public List<String> getTerritoireListe();

	public List<String> getTypeListe();

	public List<String> getPermisListe(int start);

	public Map<String, Object> getPermis(String numeroPermis);

	public void supprimer(int permis);

	public long[] importerCSV(File file);

	public void exporterXML(File selected);

	public void modifier(Map<String, Object> data);

	public void ajouter(Map<String, Object> data);

	public int getNextNumero();

	public List<String> enregistrerTerritoire(List<String> oldListTerritoire, ObservableList<String> items);

	public List<String> enregistrerType(List<String> oldListType, ObservableList<String> items);

	public List<String> rechercher(int parseInt);
}
