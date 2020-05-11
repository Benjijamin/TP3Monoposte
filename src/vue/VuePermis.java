package vue;

import java.io.IOException;
import java.sql.Date;

import controleur.ICtrl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import modele.Animal;
import modele.Permis;
import modele.Territoire;
import modele.Type;

public class VuePermis implements IVue {
	private ICtrl ctrl;
	private Scene scene;
	private VBox root;

	private Stage gestionType = new Stage();
	private Stage gestionTerritoire = new Stage();

	// CONTROLS

	// menu
	@FXML
	private MenuItem menuExporterXML;
	@FXML
	private MenuItem menuImporterCSV;
	@FXML
	private MenuItem menuQuitter;
	@FXML
	private MenuItem menuNouveau;
	@FXML
	private MenuItem menuAjouter;
	@FXML
	private MenuItem menuModifier;
	@FXML
	private MenuItem menuSupprimer;
	@FXML
	private MenuItem menuGererTerritoires;
	@FXML
	private MenuItem menuGererTypes;
	@FXML
	private MenuItem menuAide;

	// Liste des permis
	@FXML
	private TextField fieldRecherche;
	@FXML
	private Button buttonRecherche;
	@FXML
	private ListView<String> listViewPermis;

	// AnchorPane du dessus
	@FXML
	private TextField fieldNumero;
	@FXML
	private ChoiceBox<String> choiceBoxTerritoire;
	@FXML
	private Button buttonTerritoire;
	@FXML
	private DatePicker datePickerDateDebut;
	@FXML
	private DatePicker datePickerDateFin;
	@FXML
	private Button buttonSupprimer;

	// AnchorPane du bas
	@FXML
	private TextField fieldNom;
	@FXML
	private DatePicker datePickerDateNaissance;
	@FXML
	private CheckBox checkBoxVaccine;
	@FXML
	private CheckBox checkBoxSterelise;
	@FXML
	private CheckBox checkBoxMicropuce;
	@FXML
	private CheckBox checkBoxDangereux;
	@FXML
	private ChoiceBox<Type> choiceBoxType;
	@FXML
	private TextField fieldPoids;
	@FXML
	private Button buttonType;
	@FXML
	private ComboBox<String> comboBoxCouleur;

	// Radiobuttons
	@FXML
	private ToggleGroup toggleGroup;
	@FXML
	private RadioButton choiceMale;
	@FXML
	private RadioButton choiceFemelle;
	@FXML
	private RadioButton choiceInconnu;

	// Boutons
	@FXML
	private Button buttonNouveau;
	@FXML
	private Button buttonAjouter;
	@FXML
	private Button buttonModifier;
	@FXML
	private Button buttonQuitter;
	@FXML
	private Button buttonAide;

	// Gestion Type
	@FXML
	private BorderPane modalType;

	@FXML
	private ListView<String> listetype;
	@FXML
	private Button ajoutertype;

	// Gestion Territoire
	@FXML
	private BorderPane modalTerritoire;

	@FXML
	private ListView<String> listeterritoire;

	/**
	 * @param ctrl : controleur de l'application.
	 * 
	 *             <p>
	 *             Construit la vue et load le fichier fxml.
	 *             </p>
	 */
	public VuePermis(ICtrl ctrl) {
		this.ctrl = ctrl;
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/VuePermis.fxml"));
			loader.setController(this);

			this.root = loader.load();
			this.scene = new Scene(root);
			scene.getStylesheets().setAll(this.getClass().getResource("/style.css").toString());

			// Load Modals
			FXMLLoader loaderTerritoire = new FXMLLoader(getClass().getResource("/vue/modal/territoire.fxml"));
			FXMLLoader loaderType = new FXMLLoader(getClass().getResource("/vue/modal/type.fxml"));

			loaderTerritoire.setController(this);
			loaderType.setController(this);
			this.modalTerritoire = loaderTerritoire.load();
			this.modalType = loaderType.load();

			gestionTerritoire.initStyle(StageStyle.UTILITY);
			gestionType.initStyle(StageStyle.UTILITY);
			gestionTerritoire.setResizable(false);
			gestionType.setResizable(false);
			gestionTerritoire.setScene(new Scene(modalTerritoire));
			gestionType.setScene(new Scene(modalType));
			gestionTerritoire.setTitle("Gestion Territoire");
			gestionType.setTitle("Gestion Type");
			gestionTerritoire.initOwner(this.getScene().getWindow());
			gestionType.initOwner(this.getScene().getWindow());
			gestionTerritoire.initModality(Modality.APPLICATION_MODAL);
			gestionType.initModality(Modality.APPLICATION_MODAL);

			updateViewToDatabase();
		} catch (IOException e) {
			System.err.println("Erreur de chargement du fxml");
			e.printStackTrace();
		}
	}

	public Scene getScene() {
		return scene;
	}

	// TODO D�placer vers le contr�leur (Seulement envoyer la valeur des champs dans
	// un dictionnaire ou une array)
	public Permis getFormulaire() {
		Permis permis = new Permis();
		Animal animal = new Animal();
		int numero;
		try {
			numero = Integer.parseInt(fieldNumero.getText());
		} catch (NumberFormatException e) {
			numero = -1;
		}
		permis.setNumero(numero);
		permis.setDateFin(Date.valueOf(datePickerDateDebut.getValue()));
		permis.setDateDebut(Date.valueOf(datePickerDateDebut.getValue()));

		// permis.setTerritoire(choiceBoxTerritoire.getValue());
		animal.setNom(fieldNom.getText());
		animal.setType(choiceBoxType.getValue());

		RadioButton sexe = (RadioButton) toggleGroup.getSelectedToggle();
		animal.setSexe(sexe.getText());

		float poids;
		try {
			poids = Float.parseFloat(fieldPoids.getText());
		} catch (NumberFormatException e) {
			poids = -1;
		}
		animal.setPoids(poids);
		animal.setDateNaissance(Date.valueOf(datePickerDateNaissance.getValue()));
		animal.setCouleur(comboBoxCouleur.getValue());
		animal.setVaccine(checkBoxVaccine.isSelected());
		animal.setSterelise(checkBoxSterelise.isSelected());
		animal.setMicropuce(checkBoxMicropuce.isSelected());
		animal.setDangereux(checkBoxDangereux.isSelected());
		permis.setAnimal(animal);
		return permis;
	}

	@FXML
	public void quitter() {
		Alert quit = new Alert(AlertType.CONFIRMATION);
		quit.setTitle("Quitter ?");
		quit.setContentText("Etes-vous s�r de vouloir quitter ?\nLes changements non sauvegard� seront perdus.");
		if (quit.showAndWait().get() == ButtonType.OK) {
			Platform.exit();
		}
	}

	@FXML
	public void nouveau() {
	}

	@FXML
	public void ajouter() {
		if (listViewPermis == null) {
			System.out.println("Permis null");
		}
		if (listetype == null) {
			System.out.println("type null");
		}
		if (listeterritoire == null) {
			System.out.println("territoire null");
		}
		if (ajoutertype == null) {
			System.out.println("ajouter null");
		}
	}

	@FXML
	public void modifier() {
	}

	@FXML
	public void supprimer() {
	}

	@FXML
	public void aide() {
		Alert help = new Alert(AlertType.INFORMATION);
		help.setTitle("Aide");
		help.setContentText("Cette Application permet la gestion de permis animaux.");
		help.showAndWait();
	}

	@FXML
	public void export() {

	}

	@FXML
	public void importcsv() {

	}

	@FXML
	public void gestionTerritoire() {
		gestionTerritoire.show();
	}

	@FXML
	public void gestionType() {
		gestionType.show();
	}

	@FXML
	public void recherche() {

	}

	/**
	 * Disable et Enable les boutons n�cessaires selon l'�tat actuel de
	 * l'application
	 */
	public void updateButtonState() {
		fieldNumero.setDisable(true);
	}

	/**
	 * Update tous les �l�ments de la vue pour correspondre au donn�es
	 */
	public void updateViewToDatabase() {
		listeterritoire.setItems(FXCollections.observableArrayList(ctrl.getTerritoireListe()));
		listetype.setItems(FXCollections.observableArrayList(ctrl.getTypeListe()));
		listViewPermis.setItems(FXCollections.observableArrayList(ctrl.getPermisListe()));
	}

}
