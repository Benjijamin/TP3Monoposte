package controleur;

import java.io.File;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import modele.Permis;
import modele.Territoire;
import modele.Type;

/**
 * Interface pour le controleur GestionPermis
 * 
 * @author Jean-Samuel Girard & Benjamin Couillard-Dagneau
 *
 */
public interface ICtrl {
	/**
	 * 
	 * @return La scene associée à ce controleur
	 */
	public Scene getScene();

	/**
	 * Retourne la liste des territoires dans la BD sous formes de strings pour
	 * l'affichage
	 */
	public List<String> getTerritoireListe();

	/**
	 * Retourne la liste des types dans la BD sous formes de strings pour
	 * l'affichage
	 */
	public List<String> getTypeListe();

	/**
	 * Retourne la liste des numero de permis dans la BD sous formes de strings pour
	 * l'affichage
	 * 
	 * @param start le point de départ de la requête (pour passer un certain nombre
	 *              de permis)
	 */
	public List<String> getPermisListe(int start);

	/**
	 * Obtient le permis correspondant au numero reçu en paramètre dans la BD et
	 * retourne une map de toutes ses valeurs
	 * 
	 * @return une Map avec toutes les Clés : "numero" : int, "territoire" :
	 *         Territoire, "dateDebut" : java.sql.Date, "dateFin" : java.sql.Date,
	 *         "nom" : String, "type" Type, "sexe" : String, "poids" : float,
	 *         "dateNaissance" : java.sql.Date, "couleur" : String, "vaccine" :
	 *         boolean, "sterelise" : boolean, "micropuce" : boolean, "dangereux" :
	 *         boolean
	 */
	public Map<String, Object> getPermis(String numeroPermis);

	/**
	 * Supprime le permis et son animal de la BD
	 * 
	 * @param id le numero du permis à supprimer
	 */
	public void supprimer(int permis);

	/**
	 * Lit un Fichier CSV et insére sont contenu dans la BD (ne devrait pas être
	 * appelé plusieurs fois à moins d'être certain que la BD ne contient pas les
	 * valeurs qui sont dans le fichiers CSV)
	 * 
	 * @param file Le fichier à lire
	 * @return une Array Long dont les index correspondent à : [0 : Total de Permis
	 *         lu, 1: Total de Permis Retenu]
	 */
	public long[] importerCSV(File file);

	/**
	 * Écrit le contenu de la BD dans un fichier XML
	 * 
	 * @param selected Le Fichier dans lequel écrire
	 */
	public void exporterXML(File selected);

	/**
	 * Demande au modèle de Modifier dans la BD le permis dont le numero est le même
	 * que celui dans la Map pour correspondre à celle-ci
	 * 
	 * @param data une Map avec toutes les Clés : "numero" : String, "territoire" :
	 *             String, "dateDebut" : LocalDate, "dateFin" : LocalDate, "nom" :
	 *             String, "type" String, "sexe" : String, "poids" : String,
	 *             "dateNaissance" : LocalDate, "couleur" : String, "vaccine" :
	 *             boolean, "sterelise" : boolean, "micropuce" : boolean,
	 *             "dangereux" : boolean
	 */
	public void modifier(Map<String, Object> data);

	/**
	 * Demande au modèle d'ajouter dans la BD le permis qui correspond à la Map
	 * 
	 * @param data une Map avec toutes les Clés : "numero" : String, "territoire" :
	 *             String, "dateDebut" : LocalDate, "dateFin" : LocalDate, "nom" :
	 *             String, "type" String, "sexe" : String, "poids" : String,
	 *             "dateNaissance" : LocalDate, "couleur" : String, "vaccine" :
	 *             boolean, "sterelise" : boolean, "micropuce" : boolean,
	 *             "dangereux" : boolean
	 */
	public void ajouter(Map<String, Object> data);

	/**
	 * 
	 * @return Le Prochain numéro de permis libre dans la BD
	 */
	public int getNextNumero();

	/**
	 * Demande au modèle d'appliquer les modification dans la BD en comparant les
	 * deux listes reçu en paramètres.
	 * 
	 * @param oldListTerritoire La liste des territoire avant les modification
	 * @param items             La liste des territoire après les modification
	 *                          (l'objectif à avoir dans la BD après l'appel de
	 *                          cette méthode)
	 */
	public List<String> enregistrerTerritoire(List<String> oldListTerritoire, ObservableList<String> items);

	/**
	 * Demande au modèle d'appliquer les modification dans la BD en comparant les
	 * deux listes reçu en paramètres.
	 * 
	 * @param oldListTerritoire La liste des types avant les modification
	 * @param items             La liste des types après les modification
	 *                          (l'objectif à avoir dans la BD après l'appel de
	 *                          cette méthode)
	 */
	public List<String> enregistrerType(List<String> oldListType, ObservableList<String> items);

	/**
	 * Retourne la liste des permis (sous formes de String pour l'affichage) qui
	 * commencent avec le nombre reçu en paramètre
	 */
	public List<String> rechercher(int i);
}
