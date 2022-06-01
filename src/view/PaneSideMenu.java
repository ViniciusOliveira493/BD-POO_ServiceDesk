package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PaneSideMenu extends Pane implements EventHandler<ActionEvent>{
	Button btnMeusDados;
	
	Home hm;
	public PaneSideMenu(Home hm) {
		this.hm = hm;
		VBox vb = new VBox();
		init(vb);
		this.getChildren().add(vb);
	}
	
	private void init(VBox vb) {
		criarBtnMeusDados(vb);
	}
	
	private void criarBtnMeusDados(VBox vb) {
		HBox hb1 = new HBox();
		btnMeusDados = new Button("Meus Dados");
		btnMeusDados.addEventHandler(ActionEvent.ANY, this);
		hb1.getChildren().add(btnMeusDados);
		vb.getChildren().add(hb1);
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == btnMeusDados){
			hm.paneMeusDados();
		}
		
	}
}
