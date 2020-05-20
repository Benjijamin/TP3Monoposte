package vue;

import javafx.scene.Scene;
/**
 * Interface pour la vue GestionPermis
 * 
 * @author Jean-Samuel Girard & Benjamin Couillard-Dagneau
 *
 */
public interface IVue {
	public Scene getScene();
	
	public void quitter();

	public void nouveau();

	public void ajouter();

	public void modifier();

	public void supprimer();

	public void importerResult(long[] read);
}
