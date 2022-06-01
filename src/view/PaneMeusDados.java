package view;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PaneMeusDados extends Pane{
	TextField txtCpf;
	TextField txtNome;
	PasswordField txtSenha;
	PasswordField txtConfSenha;
	
	public PaneMeusDados() {
		VBox vb = new VBox();
		init(vb);
		this.getChildren().add(vb);
	}

	private void init(VBox vb) {
		criarTxtNome(vb);
		criarTxtCpf(vb);
		criarTxtSenha(vb);
		criarTxtConfSenha(vb);
	}
	
	private void criarTxtNome(VBox vb) {
		HBox hb2 = new HBox();
		Label lblNome = new Label();
		lblNome.setText("Nome: ");
		txtNome = new TextField();
		hb2.getChildren().addAll(lblNome,txtNome);
		vb.getChildren().add(hb2);
	}	
	
	private void criarTxtCpf(VBox vb) {
		HBox hb3 = new HBox();
		Label lblCpf = new Label();
		lblCpf.setText("CPF: ");
		txtCpf = new TextField();
		hb3.getChildren().addAll(lblCpf,txtCpf);
		vb.getChildren().add(hb3);
	}
	
	private void criarTxtSenha(VBox vb) {
		HBox hb4 = new HBox();
		Label lblSenha = new Label();
		lblSenha.setText("Senha: ");
		txtSenha = new PasswordField();
		hb4.getChildren().addAll(lblSenha,txtSenha);
		vb.getChildren().add(hb4);
	}
	
	private void criarTxtConfSenha(VBox vb) {
		HBox hb5 = new HBox();
		Label lblSenha = new Label();
		lblSenha.setText("Confirmar senha: ");
		txtConfSenha = new PasswordField();
		hb5.getChildren().addAll(lblSenha,txtConfSenha);
		vb.getChildren().add(hb5);
	}
}
