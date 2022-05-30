package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Pessoa;

public class TelaPrincipal extends Application{
	Stage stage;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Service Desk - Login");
		Pane pn =  new Pane();
		
		CenaLogin login = new CenaLogin(pn,stage,this);
		stage.setScene(login);
		stage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
