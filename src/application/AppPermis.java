package application;

import controleur.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppPermis extends Application{

	private ICtrl ctrl;
	
	@Override
	public void start(Stage stage) throws Exception {
		ctrl = new CtrlPermis();
		stage.setScene(ctrl.getScene());
		stage.setTitle("app");
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
