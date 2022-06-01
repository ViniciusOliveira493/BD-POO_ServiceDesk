package view;

import controller.PessoaController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Pessoa;

public class CenaLogin extends Scene implements EventHandler<ActionEvent>{
	
	TextField txtCpf;
	TextField txtNome;
	PasswordField txtSenha;
	PasswordField txtConfSenha;
	
	Button btnEntrar;
	Button lblCadastrar;
	PessoaController ctrl = new PessoaController();
	Stage stg;
	Pane root;
	TelaPrincipal tp;
	VBox vb;
	public CenaLogin(Pane root,Stage stg,TelaPrincipal tp) {
		super(root,300,400);
		this.root = root;
		this.stg = stg;
		this.tp = tp;
		
		vb = new VBox();
		login();
		root.getChildren().add(vb);
		
	}
	
	private void login() {
		vb = new VBox();
		vb.setSpacing(10);
		
		criarLblLogin(vb);
		criarTxtCpf(vb);
		criarTxtSenha(vb);
		criarBotoes(vb);
	}
	
	private void criarLblLogin(VBox vb) {
		HBox hb1 = new HBox();
		Label lblLogin = new Label();
		lblLogin.setText("Login");
		hb1.getChildren().add(lblLogin);
		vb.getChildren().add(hb1);
	}
	
	private void criarTxtCpf(VBox vb) {
		GridPane hb2 = new GridPane();
		Label lblCpf = new Label();
		lblCpf.setText("CPF: ");
		txtCpf = new TextField();
		hb2.add(lblCpf,0,0);
		hb2.add(txtCpf,1,0);
		vb.getChildren().add(hb2);
	}
	
	private void criarTxtSenha(VBox vb) {
		GridPane hb3 = new GridPane();
		Label lblSenha = new Label();
		lblSenha.setText("Senha: ");
		txtSenha = new PasswordField();
		hb3.add(lblSenha,0,0);
		hb3.add(txtSenha,1,0);
		vb.getChildren().add(hb3);
	}
	
	private void criarBotoes(VBox vb) {
		btnEntrar = new Button("Entrar");
		lblCadastrar = new Button("Cadastre-se");
		
		btnEntrar.addEventFilter(ActionEvent.ANY, this);
		lblCadastrar.addEventFilter(ActionEvent.ANY, this);
		
		VBox vb4 = new VBox();
		vb4.setSpacing(10);
		
		
		vb4.getChildren().addAll(btnEntrar,lblCadastrar);
		vb.getChildren().add(vb4);
	}
	
	private Pessoa boundaryToEntity() {
		Pessoa p = new Pessoa();
		p.setCpf(txtCpf.getText());
		p.setSenha(txtSenha.getText());
		return p;
	}
	
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == btnEntrar) {
			Pessoa p = ctrl.login(boundaryToEntity());
			Home hm = new Home(p);
			if(p.getNome() != null && p.getNome()!="") {
				try {
					hm.start(stg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				Alert a = new Alert(AlertType.INFORMATION);
				a.setTitle("Usuário inexistente");
				a.setContentText("CPF ou Senha estão incorretos");
				a.show();
			}
		}else {
			tp.cadastrar();
		}
	}
}
