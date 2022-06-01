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
	private Stage stage;
	
	private CenaLogin login;
	private CenaCadastro cad;
	private Home hm;
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Service Desk - Login");
		this.stage = stage;
		Pane pn =  new Pane();
		Pane pn2 =  new Pane();
		login = new CenaLogin(pn,this);
		cad = new CenaCadastro(pn2, this);
		stage.setScene(login);
		stage.show();
	}
	
	public void cadastrar(){
		stage.setScene(cad);
	}
	
	public void login() {
		stage.setScene(login);
	}
	
	public void home(Pessoa p) {
		Pane pn3 =  new Pane();
		hm = new Home(pn3,p,this);
		stage.setScene(hm);
		stage.centerOnScreen();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
	public Stage getStage() {
		return this.stage;
	}
}
