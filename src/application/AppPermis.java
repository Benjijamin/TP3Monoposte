package application;

import controleur.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Cette Aplication Requière une base de données MySQL nommée "GestionPermis"
 * 
 * Après la création de la base de données (Première Execution du programme)
 * Changer "create" par "update" dans hibernate.cfg.xml
 * 
 * @author Jean-Samuel Girard & Benjamin Couillard-Dagneau
 *
 */
public class AppPermis extends Application {

	private ICtrl ctrl;

	@Override
	public void start(Stage stage) throws Exception {
		ctrl = new CtrlPermis();
		stage.setScene(ctrl.getScene());
		stage.setTitle("Gestion Permis");
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
