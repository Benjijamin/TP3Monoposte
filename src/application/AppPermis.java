package application;

import controleur.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Cette Aplication Requi�re une base de donn�es MySQL nomm�e "GestionPermis"
 * 
 * Apr�s la cr�ation de la base de donn�es (Premi�re Execution du programme)
 * Changer "create" par "update" dans hibernate.cfg.xml
 * 
 * @author Jean-Samuel Girard & Benjamin Couillard-Dagneau
 *
 */
public class AppPermis extends Application {

	private ICtrl ctrl;
	@Override
	public void start(Stage stage) throws Exception {
		stage.setMinHeight(460);
		stage.setMinWidth(900);
		stage.setHeight(460);
		stage.setWidth(900);
		ctrl = new CtrlPermis();
		stage.setScene(ctrl.getScene());
		stage.setTitle("Gestion Permis");
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
