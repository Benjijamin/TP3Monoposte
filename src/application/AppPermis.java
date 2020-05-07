package application;

import controleur.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Cette Aplication Requi�re une base de donn�es MySQL nomm�e "GestionPermis" 
 * @author Jean-Samuel Girard & Benjamin Couillard-Dagneau
 *
 */
public class AppPermis extends Application{

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
