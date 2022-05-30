package view;

import controller.PessoaController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Pessoa;

public class CenaLogin extends Scene implements EventHandler<ActionEvent>{
	
	TextField txtCpf;
	PasswordField txtSenha;
	
	Button btnEntrar;
	Label lblCadastrar;
	PessoaController ctrl = new PessoaController();
	Stage stg;
	Pane root;
	TelaPrincipal tp;
	public CenaLogin(Pane root,Stage stg,TelaPrincipal tp) {
		super(root,300,400);
		root.getChildren().add(iniciar());
		this.root = root;
		this.stg = stg;
		this.tp = tp;
	}
	private VBox iniciar() {
		VBox vb = new VBox();
		vb.setSpacing(10);
		
		criarLblLogin(vb);
		criarTxtCpf(vb);
		criarTxtSenha(vb);
		criarBotoes(vb);
		
		return vb;
	}
	
	private void criarLblLogin(VBox vb) {
		HBox hb1 = new HBox();
		Label lblLogin = new Label();
		lblLogin.setText("Login");
		hb1.getChildren().add(lblLogin);
		vb.getChildren().add(hb1);
	}
	
	private void criarTxtCpf(VBox vb) {
		HBox hb2 = new HBox();
		Label lblCpf = new Label();
		lblCpf.setText("CPF: ");
		txtCpf = new TextField();
		hb2.getChildren().addAll(lblCpf,txtCpf);
		vb.getChildren().add(hb2);
	}
	
	private void criarTxtSenha(VBox vb) {
		HBox hb3 = new HBox();
		Label lblSenha = new Label();
		lblSenha.setText("Senha: ");
		txtSenha = new PasswordField();
		hb3.getChildren().addAll(lblSenha,txtSenha);
		vb.getChildren().add(hb3);
	}
	
	private void criarBotoes(VBox vb) {
		btnEntrar = new Button("Entrar");
		lblCadastrar = new Label();
		lblCadastrar.setText("Cadastre-se");
		
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
			
		}
	}
}
