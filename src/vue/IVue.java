package vue;

import javafx.scene.Scene;

/**
 * Interface pour la vue GestionPermis
 * 
 * @author Jean-Samuel Girard & Benjamin Couillard-Dagneau
 *
 */
public interface IVue {
	/**
	 * @return la Scene Principale
	 */
	public Scene getScene();

	/**
	 * Répond à un bouton quitter / Quitte l'application
	 */
	public void quitter();

	/**
	 * Répond à un bouton nouveaux / crée un nouveau enregistrement
	 */
	public void nouveau();

	/**
	 * Répond à un bouton ajouter / ajoute un enregistrement
	 */
	public void ajouter();

	/**
	 * Répond à un bouton modifier
	 */
	public void modifier();

	/**
	 * Répond à un bouton supprimer
	 */
	public void supprimer();

	/**
	 * Affiche une fenêtre après l'importation d'un fichier
	 * 
	 * @return une Array Long dont les index correspondent à : [0 : Total de Permis
	 *         lu, 1: Total de Permis Retenu, 2: temps pris pour la lecture en
	 *         millisecondes]
	 */
	public void importerResult(long[] read);
}
