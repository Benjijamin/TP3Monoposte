package vue;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controleur.ICtrl;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.*;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Vue GestionPermis
 * 
 * @author Jean-Samuel Girard & Benjamin Couillard-Dagneau
 *
 */
public class VuePermis implements IVue {
	private ICtrl ctrl;
	private Scene scene;
	private VBox root;

	private Stage gestionType = new Stage();
	private Stage gestionTerritoire = new Stage();

	/**
	 * Liste Temporaire pour retenir les territoires
	 */
	private List<String> oldListTerritoire;
	/**
	 * Liste Temporaire pour retenir les types
	 */
	private List<String> oldListType;

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

			// Listener pour la ListView permis
			listViewPermis.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					updateButtonState();
					updateFields(newValue);
				}

			});

			// Listeners pour les datePickers
			ChangeListener<LocalDate> dateListener = (observable, oldValue, newValue) -> {
				validerDates();
			};
			datePickerDateDebut.valueProperty().addListener(dateListener);
			datePickerDateFin.valueProperty().addListener(dateListener);
			datePickerDateNaissance.valueProperty().addListener(dateListener);

			// Options de base pour la couleur
			comboBoxCouleur.getItems().addAll("Inconnu","Noir", "Blanc", "Gris", "Lilac", "Caramel", "Creme");

			// Load Modals
			FXMLLoader loaderTerritoire = new FXMLLoader(getClass().getResource("/vue/modal/territoire.fxml"));
			FXMLLoader loaderType = new FXMLLoader(getClass().getResource("/vue/modal/type.fxml"));

			loaderTerritoire.setController(this);
			loaderType.setController(this);
			this.modalTerritoire = loaderTerritoire.load();
			this.modalType = loaderType.load();

			// Disable le numero de permis
			fieldNumero.setDisable(true);

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

			listeterritoire.setEditable(true);
			listetype.setEditable(true);
			listeterritoire.setCellFactory(TextFieldListCell.forListView());
			listetype.setCellFactory(TextFieldListCell.forListView());

			updateViewToDatabase();

		} catch (IOException e) {
			System.err.println("Erreur de chargement du fxml");
			e.printStackTrace();
		}
	}

	@Override
	public Scene getScene() {
		return scene;
	}

	public Map<String, Object> getFormulaire() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("numero", fieldNumero.getText());
		data.put("dateDebut", datePickerDateDebut.getValue());
		data.put("dateFin", datePickerDateFin.getValue());
		data.put("territoire", choiceBoxTerritoire.getValue());
		data.put("nom", fieldNom.getText());
		data.put("type", choiceBoxType.getValue());
		RadioButton sexe = (RadioButton) toggleGroup.getSelectedToggle();
		data.put("sexe", sexe.getText());
		data.put("poids", fieldPoids.getText());
		data.put("dateNaissance", datePickerDateNaissance.getValue());
		data.put("couleur", comboBoxCouleur.getValue());
		data.put("vaccine", checkBoxVaccine.isSelected());
		data.put("sterelise", checkBoxSterelise.isSelected());
		data.put("micropuce", checkBoxMicropuce.isSelected());
		data.put("dangereux", checkBoxDangereux.isSelected());
		return data;
	}

	/**
	 * @return true si formulaire valide
	 */
	private boolean validerFormulaire() {
		if (choiceBoxTerritoire.getValue() == null || choiceBoxTerritoire.getValue() == "") {
			error("Le territoire ne peux pas être vide.");
			return false;
		}
		if (datePickerDateDebut.getValue() == null) {
			error("La date de début de permis ne peux pas être vide");
			return false;
		}
		if (datePickerDateFin.getValue() == null) {
			error("La date de fin de permis ne peux pas être vide");
			return false;
		}
		if (fieldNom.getText() == "") {
			error("Le nom de l'animal ne peux pas être vide.");
			return false;
		}
		if (choiceBoxType.getValue() == null || choiceBoxType.getValue() == "") {
			error("Le type de l'animal ne peux pas être vide");
			return false;
		}
		if (datePickerDateNaissance.getValue() == null) {
			error("La date de naissance de l'animal ne peux pas être vide");
			return false;
		}
		try {
			Float.parseFloat(fieldPoids.getText());
		} catch (NumberFormatException e) {
			error("Le poids doit être une valeur numérique");
			return false;
		}
		if (comboBoxCouleur.getValue() == null || comboBoxCouleur.getValue() == "") {
			error("La couleur de l'animal ne peux pas être vide");
			return false;
		}
		return true;
	}

	/**
	 * Modifie les dates dans le formulaire pour qu'elles soient conformes aux
	 * exigences
	 */
	private void validerDates() {
		LocalDate debut;
		LocalDate fin;
		LocalDate naissance;

		if ((debut = datePickerDateDebut.getValue()) == null) {
			debut = LocalDate.now();
		}
		if ((naissance = datePickerDateNaissance.getValue()) == null) {
			naissance = debut;
		}
		if ((fin = datePickerDateFin.getValue()) == null) {
			fin = debut.plusYears(1);
		}

		if (fin.isBefore(debut) || fin.isBefore(debut.plusMonths(1))) {
			datePickerDateFin.setValue(debut.plusMonths(1));
		}
		if (debut.isBefore(naissance)) {
			datePickerDateNaissance.setValue(debut);
		}
	}

	@FXML
	@Override
	public void quitter() {
		Alert quit = new Alert(AlertType.CONFIRMATION);
		quit.setTitle("Quitter ?");
		quit.setContentText("Etes-vous sûr de vouloir quitter ?\nLes changements non sauvegardés seront perdus.");
		if (quit.showAndWait().get() == ButtonType.OK) {
			Platform.exit();
		}
	}

	@FXML
	@Override
	public void nouveau() {
		updateButtonNouveau();
		validerDates();
	}

	@FXML
	@Override
	public void ajouter() {
		if (validerFormulaire()) {
			ctrl.ajouter(getFormulaire());
			updateViewToDatabase();
		}
	}

	@FXML
	@Override
	public void modifier() {
		if (validerFormulaire())
			ctrl.modifier(getFormulaire());
	}

	@FXML
	@Override
	public void supprimer() {
		Alert del = new Alert(AlertType.CONFIRMATION);
		del.setTitle("Supprimmer ?");
		del.setContentText("Etes-vous sûr de vouloir Supprimer le permis " + fieldNumero.getText() + " ?");
		if (del.showAndWait().get() == ButtonType.OK) {
			try {
				ctrl.supprimer(Integer.parseInt(fieldNumero.getText()));
			} catch (Exception e) {
				error("Impossible de supprimmer ce permis, assurer vous que le numéro spécifié est contenu dans la base de données.");
			}

		}
		listViewPermis.getSelectionModel().clearSelection();
		updateViewToDatabase();
	}

	@FXML
	private void aide() {
		Alert help = new Alert(AlertType.INFORMATION);
		help.setTitle("Aide");
		help.setContentText("Cette Application permet la gestion de permis animaux.");
		help.showAndWait();
	}

	private void error(String message) {
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle("Erreur");
		error.setContentText(message);
		error.setHeaderText("Une erreur est survenue lors du traitement de votre demande.");
		error.showAndWait();
	}

	@FXML
	private void export() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("XML", "*.xml"));
		File selected = fileChooser.showSaveDialog(new Stage());

		if (selected != null) {

			Alert wait = new Alert(AlertType.NONE,
					"Veuillez Patientez pendant que l'application génère votre fichier de données XML",
					ButtonType.CLOSE);
			wait.setTitle("écriture du Fichier XML à partir de la Base de Donnée");
			wait.setHeaderText("Patientez...");
			wait.initStyle(StageStyle.UNDECORATED);
			wait.getDialogPane().lookupButton(ButtonType.CLOSE).setVisible(false);
			wait.getDialogPane()
					.setStyle("-fx-border-color:black; -fx-background-color:linear-gradient(white,lightgrey)");

			wait.show();

			Task<Void> task = new Task<Void>() {
				@Override
				public Void call() {
					ctrl.exporterXML(selected);
					return null;
				}
			};

			task.setOnSucceeded(e -> {
				updateViewToDatabase();
				wait.hide();
			});

			task.setOnFailed(e -> {
				updateViewToDatabase();
				wait.hide();
				error("Erreur d'exportation");
			});

			new Thread(task).start();
		}
	}

	@FXML
	private void importcsv() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "\\io\\Fichiers_CSV"));
		File selected = fileChooser.showOpenDialog(new Stage());

		if (selected != null) {

			Alert wait = new Alert(AlertType.NONE, "Veuillez Patientez pendant l'insertion dans la base de donnée",
					ButtonType.CLOSE);
			wait.setTitle("Lecture du Fichier CSV et Insertion dans la Base de Donnée");
			wait.setHeaderText("Patientez...");
			wait.initStyle(StageStyle.UNDECORATED);
			wait.getDialogPane().lookupButton(ButtonType.CLOSE).setVisible(false);
			wait.getDialogPane()
					.setStyle("-fx-border-color:black; -fx-background-color:linear-gradient(white,lightgrey)");

			wait.show();

			Task<long[]> task = new Task<long[]>() {
				@Override
				public long[] call() {
					try {
						return ctrl.importerCSV(selected);
					} catch (Exception e) {
						this.failed();
					}
					return null;
				}
			};

			task.setOnSucceeded(e -> {
				updateViewToDatabase();
				wait.hide();
				this.importerResult(task.getValue());
			});

			task.setOnFailed(e -> {
				updateViewToDatabase();
				wait.hide();
				error("Erreur d'importation");
			});

			new Thread(task).start();
		}

		// Event listener sur la scrollbar de ListView Permis pour loader plus de permis
		// lorsqu'on
		// atteint le bas de la scrollbar
		ScrollBar scrollbar = getListViewScrollBar();
		if (scrollbar != null) {
			ChangeListener<Number> listener = (observable, oldValue, newValue) -> {
				double position = newValue.doubleValue();
				ScrollBar scrollBar = getListViewScrollBar();
				if (position == scrollBar.getMax()) {
					if (updateViewPermis())
						scrollBar.setValue(scrollBar.getValue() - 0.1);
				}
			};
			scrollbar.valueProperty().removeListener(listener);
			scrollbar.valueProperty().addListener(listener);
		}
	}

	@FXML
	private void gestionTerritoire() {
		updateViewTerritoire();
		gestionTerritoire.show();
	}

	@FXML
	private void gestionType() {
		updateViewType();
		gestionType.show();
	}

	@FXML
	private void recherche() {
		try {
			if (fieldRecherche.getText().isEmpty()) {
				updateViewToDatabase();
			} else {
				listViewPermis.setItems(FXCollections
						.observableArrayList(ctrl.rechercher(Integer.parseInt(fieldRecherche.getText().trim()))));
				updateButtonState();
			}
		} catch (NumberFormatException e) {
			error("Le champ de recherche doit être un nombre");
		}
		fieldRecherche.setText("");
	}

	/**
	 * Disable et Enable les boutons nécessaires selon l'état actuel de
	 * l'application.
	 */
	private void updateButtonState() {
		// il y a un permis selectionné ?
		boolean permisselected = (listViewPermis.getSelectionModel().getSelectedItem() != null);
		datePickerDateDebut.setDisable(!permisselected);
		datePickerDateFin.setDisable(!permisselected);
		datePickerDateNaissance.setDisable(!permisselected);
		buttonSupprimer.setDisable(!permisselected);
		menuSupprimer.setDisable(!permisselected);
		buttonAjouter.setDisable(true);
		menuAjouter.setDisable(true);
		buttonModifier.setDisable(!permisselected);
		menuModifier.setDisable(!permisselected);
		buttonNouveau.setDisable(false);
		menuNouveau.setDisable(false);
		choiceBoxTerritoire.setDisable(!permisselected);
		choiceBoxType.setDisable(!permisselected);
		fieldNom.setDisable(!permisselected);
		fieldPoids.setDisable(!permisselected);
		choiceFemelle.setDisable(!permisselected);
		choiceInconnu.setDisable(!permisselected);
		choiceMale.setDisable(!permisselected);
		checkBoxDangereux.setDisable(!permisselected);
		checkBoxMicropuce.setDisable(!permisselected);
		checkBoxSterelise.setDisable(!permisselected);
		checkBoxVaccine.setDisable(!permisselected);
		comboBoxCouleur.setDisable(!permisselected);
		if (listViewPermis.getItems().isEmpty()) {
			menuImporterCSV.setDisable(false);
			fieldRecherche.setDisable(true);
			buttonRecherche.setStyle("-fx-background-image: url('refresh.png');");
		} else {
			menuImporterCSV.setDisable(true);
			fieldRecherche.setDisable(false);
			buttonRecherche.setStyle("-fx-background-image: url('search.png');");
		}
	}

	/**
	 * Disable et Enable les boutons nécessaires lors de la création d'un permis Et
	 * mets les champs à zéro
	 */
	private void updateButtonNouveau() {
		fieldNumero.setText(String.valueOf(ctrl.getNextNumero()));
		datePickerDateDebut.setDisable(false);
		datePickerDateFin.setDisable(false);
		datePickerDateNaissance.setDisable(false);
		buttonSupprimer.setDisable(true);
		menuSupprimer.setDisable(true);
		buttonAjouter.setDisable(false);
		menuAjouter.setDisable(false);
		buttonModifier.setDisable(true);
		menuModifier.setDisable(true);
		buttonNouveau.setDisable(true);
		menuNouveau.setDisable(true);
		choiceBoxTerritoire.setDisable(false);
		choiceBoxType.setDisable(false);
		fieldNom.setDisable(false);
		fieldPoids.setDisable(false);
		choiceFemelle.setDisable(false);
		choiceInconnu.setDisable(false);
		choiceMale.setDisable(false);
		checkBoxDangereux.setDisable(false);
		checkBoxMicropuce.setDisable(false);
		checkBoxSterelise.setDisable(false);
		checkBoxVaccine.setDisable(false);
		comboBoxCouleur.setDisable(false);

		// Fields reset
		fieldNumero.setText(ctrl.getNextNumero() + "");
		fieldNom.setText("");

		choiceBoxTerritoire.setValue("Inconnu");
		choiceBoxType.setValue("Inconnu");
		datePickerDateDebut.setValue(LocalDate.now());
		datePickerDateFin.setValue(LocalDate.now().plusYears(1));
		datePickerDateNaissance.setValue(LocalDate.now());

		fieldPoids.setText("");

		comboBoxCouleur.setValue("Inconnu");
		checkBoxVaccine.setSelected(false);
		checkBoxSterelise.setSelected(false);
		checkBoxMicropuce.setSelected(false);
		checkBoxDangereux.setSelected(false);

		choiceInconnu.setSelected(true);

		listViewPermis.getSelectionModel().clearSelection();
	}

	/**
	 * Update tous les champs pour correspondre à l'élément selectionné
	 */
	private void updateFields(String numeroPermis) {
		try {
			Map<String, Object> values = ctrl.getPermis(numeroPermis);
			fieldNumero.setText(String.valueOf((int) values.get("numero")));
			fieldNom.setText((String) values.get("nom"));

			choiceBoxTerritoire
					.setValue(values.get("territoire") == null ? "Inconnu" : values.get("territoire").toString());
			choiceBoxType.setValue(values.get("type") == null ? "Inconnu" : values.get("type").toString());

			Date dateDebut = (Date) values.get("dateDebut");
			datePickerDateDebut.setValue(dateDebut.toLocalDate());
			Date dateFin = (Date) values.get("dateFin");
			datePickerDateFin.setValue(dateFin.toLocalDate());
			Date dateNaissance = (Date) values.get("dateNaissance");
			datePickerDateNaissance.setValue(dateNaissance.toLocalDate());

			fieldPoids.setText(String.valueOf((float) values.get("poids")));

			comboBoxCouleur.setValue((String) values.get("couleur"));
			checkBoxVaccine.setSelected((boolean) values.get("vaccine"));
			checkBoxSterelise.setSelected((boolean) values.get("sterelise"));
			checkBoxMicropuce.setSelected((boolean) values.get("micropuce"));
			checkBoxDangereux.setSelected((boolean) values.get("dangereux"));
			String sexe = (String) values.get("sexe");
			if (sexe.contains("F")) {
				choiceFemelle.setSelected(true);
			} else if (sexe.contains("M")) {
				choiceMale.setSelected(true);
			} else {
				choiceInconnu.setSelected(true);
			}

		} catch (Exception e) {
			error("Impossible d'obtenir l'information sur ce permis");
			e.printStackTrace();
		}
	}

	/**
	 * Update tous les listes de la vue pour correspondre au données (remet la liste
	 * permis à 100 enregistrement visibles)
	 */
	private void updateViewToDatabase() {
		List<String> temp;

		temp = ctrl.getTerritoireListe();
		listeterritoire.setItems(FXCollections.observableArrayList(temp));
		choiceBoxTerritoire.setItems(FXCollections.observableArrayList(temp));
		choiceBoxTerritoire.getItems().add(0, "Inconnu");
		temp = ctrl.getTypeListe();
		listetype.setItems(FXCollections.observableArrayList(temp));
		choiceBoxType.setItems(FXCollections.observableArrayList(temp));
		choiceBoxType.getItems().add(0, "Inconnu");
		temp = ctrl.getPermisListe(0);
		listViewPermis.setItems(FXCollections.observableArrayList(temp));
		updateButtonState();
	}

	/**
	 * Update la liste view permis
	 * 
	 * @return true si au moin une valeur à été insérée
	 */
	private boolean updateViewPermis() {
		List<String> temp = ctrl.getPermisListe(listViewPermis.getItems().size());
		listViewPermis.getItems().addAll(temp);
		return temp.size() != 0;
	}

	/**
	 * Update la liste view territoire et la store dans oldliste
	 * 
	 * @return true si au moin une valeur à été insérée
	 */
	private boolean updateViewTerritoire() {
		List<String> temp = ctrl.getTerritoireListe();
		listeterritoire.setItems(FXCollections.observableArrayList(temp));
		oldListTerritoire = temp;
		return temp.size() != 0;
	}

	/**
	 * Update la liste view type et la store dans oldliste
	 * 
	 * @return true si au moin une valeur à été insérée
	 */
	private boolean updateViewType() {
		List<String> temp = ctrl.getTypeListe();
		listetype.setItems(FXCollections.observableArrayList(temp));
		oldListType = temp;
		return temp.size() != 0;
	}

	private ScrollBar getListViewScrollBar() {
		ScrollBar scrollbar = null;
		for (Node node : listViewPermis.lookupAll(".scroll-bar")) {
			if (node instanceof ScrollBar) {
				ScrollBar bar = (ScrollBar) node;
				if (bar.getOrientation().equals(Orientation.VERTICAL)) {
					scrollbar = bar;
				}
			}
		}
		return scrollbar;
	}

	// Modal Buttons
	@FXML
	private void ajouterTerritoire() {
		listeterritoire.getItems().add("Nouveau Territoire");
	}

	@FXML
	private void ajouterType() {
		listetype.getItems().add("Nouveau Type");
	}

	@FXML
	/**
	 * Demande au controleur : Enregistrer les territoire dans la liste view. >
	 * modifier les anciens et ajouter les nouveaux ensuite : update la choicebox
	 */
	private void enregistrerTerritoire() {
		try {
			choiceBoxTerritoire.setItems(FXCollections
					.observableArrayList(ctrl.enregistrerTerritoire(oldListTerritoire, listeterritoire.getItems())));
			choiceBoxTerritoire.getItems().add(0, "Inconnu");
		} catch (Exception e) {
			error("Impossible d'enregistrer");
		} finally {
			gestionTerritoire.hide();
		}
	}

	@FXML
	/**
	 * Demande au controleur : Enregistrer les types dans la liste view. > modifier
	 * les anciens et ajouter les nouveaux ensuite : update la choicebox
	 */
	private void enregistrerType() {
		try {
			choiceBoxType.setItems(
					FXCollections.observableArrayList(ctrl.enregistrerType(oldListType, listetype.getItems())));
			choiceBoxType.getItems().add(0, "Inconnu");
		} catch (Exception e) {
			error("Impossible d'enregistrer");
		} finally {
			gestionType.hide();
		}
	}

	@Override
	public void importerResult(long[] read) {
		Alert help = new Alert(AlertType.INFORMATION);
		help.setTitle("Fichier CSV");
		help.setContentText("Le fichier CSV à été lu,\n" + read[0] + " Enregistrements lu, " + read[1] + " retenu.\n"
				+ (read[0] - read[1]) + " ont été ignoré.\n" + "Temps pris pour l'éxécution : " + read[2] + "ms");
		help.showAndWait();
	}
}
