package vue;

import java.io.IOException;

import controleur.ICtrl;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import modele.Permis;

public class VuePermis implements IVue {
	private ICtrl ctrl;
	private Scene scene;
	private VBox root;

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
	private ListView<Permis> listViewPermis;

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
	private ChoiceBox<String> choiceBoxType;
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

		} catch (IOException e) {
			System.err.println("Erreur de chargement du fxml");
			e.printStackTrace();
		}
	}

	public Scene getScene() {
		return scene;
	}

	public Permis getFormulaire() {
		Permis permis = new Permis();
		int numero;
		try {
			numero = Integer.parseInt(fieldNumero.getText());
		} catch (NumberFormatException e) {
			numero = -1;
		}
		permis.setNumero(numero);
		permis.setDateFin(datePickerDateDebut.getValue());
		permis.setDateDebut(datePickerDateDebut.getValue());
		permis.setTerritoire(choiceBoxTerritoire.getValue());
		permis.setNom(fieldNom.getText());
		permis.setType(choiceBoxType.getValue());

		// TODO refaire ca c'est batard
		RadioButton sexe = (RadioButton) toggleGroup.getSelectedToggle();
		permis.setSexe(sexe.getText());

		float poids;
		try {
			poids = Float.parseFloat(fieldPoids.getText());
		} catch (NumberFormatException e) {
			poids = -1;
		}
		permis.setPoids(poids);
		permis.setDateNaissance(datePickerDateNaissance.getValue());
		permis.setCouleur(comboBoxCouleur.getValue());
		permis.setVaccine(checkBoxVaccine.isSelected());
		permis.setSterelise(checkBoxSterelise.isSelected());
		permis.setMicropuce(checkBoxMicropuce.isSelected());
		permis.setDangereux(checkBoxDangereux.isSelected());
		return permis;
	}

	public void quitter() {
	}

	public void nouveau() {
	}

	public void ajouter() {
		// TODO remove
		Permis p = getFormulaire();
		System.out.println(p);
	}

	public void modifier() {
	}

	public void supprimer() {
	}

	public void aide() {
	}
}
