package view;

import javafx.scene.control.Alert;

public class Alerta extends Alert{
	public Alerta(AlertType a,String title,String text) {
		super(a);
		this.setTitle(title);
		this.setContentText(text);
		this.showAndWait();
	}
}
