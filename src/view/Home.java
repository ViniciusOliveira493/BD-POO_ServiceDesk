package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Pessoa;

public class Home extends Application{
	private Pessoa pessoa;
	public Home(Pessoa p) {
		this.pessoa = p;		
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Home - ["+pessoa.getNome()+"]");
		
		Pane pn =  new Pane();
		Scene scn = new Scene(pn,1000,600);
		stage.setScene(scn);
		stage.centerOnScreen();
		stage.show();
	}
}
