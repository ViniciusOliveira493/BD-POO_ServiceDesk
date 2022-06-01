package view;

import controller.PessoaController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Pessoa;

public class CenaCadastro extends Scene implements EventHandler<ActionEvent>{
	TextField txtCpf;
	TextField txtNome;
	PasswordField txtSenha;
	PasswordField txtConfSenha;
	
	Button btnCadastrar;
	Button btnVoltar;
	PessoaController ctrl = new PessoaController();
	Pane root;
	TelaPrincipal tp;
	VBox vb;
	public CenaCadastro(Pane root,TelaPrincipal tp) {
		super(root,300,400);
		this.root = root;
		this.tp = tp;
		
		vb = new VBox();
		cadastrar();
		root.getChildren().add(vb);
		
	}
	
	private void cadastrar() {
		vb = new VBox();
		vb.setSpacing(10);	
		
		criarLblCadastrar(vb);
		criarTxtCpf(vb);
		criarTxtNome(vb);
		criarTxtSenha(vb);
		criarTxtConfSenha(vb);
		criarBotoes(vb);
		
		vb.setVisible(true);
		
	}
	
	private void criarLblCadastrar(VBox vb) {
		HBox hb1 = new HBox();
		Label lblCadastrar = new Label();
		lblCadastrar.setText("Cadastrar");
		hb1.getChildren().add(lblCadastrar);
		vb.getChildren().add(hb1);
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
	
	
	private void criarBotoes(VBox vb) {
		btnCadastrar = new Button("Cadastrar");
		btnVoltar = new Button("Voltar");
		
		btnCadastrar.addEventFilter(ActionEvent.ANY, this);
		btnVoltar.addEventFilter(ActionEvent.ANY, this);
		
		VBox vb4 = new VBox();
		vb4.setSpacing(10);
		
		
		vb4.getChildren().addAll(btnCadastrar,btnVoltar);
		vb.getChildren().add(vb4);
	}
	
	private Pessoa boundaryToEntity() {
		Pessoa p = new Pessoa();
		p.setCpf(txtCpf.getText());
		p.setNome(txtNome.getText());
		p.setSenha(txtSenha.getText());
		return p;
	}
	
	private void limparCampos() {
		txtCpf.setText(null); 
		txtNome.setText(null);
		txtSenha.setText(null);
		txtConfSenha.setText(null);
	}
	
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == btnCadastrar) {
			if(txtSenha.getText().equals(txtConfSenha.getText())) {
				Pessoa p = boundaryToEntity();
				boolean a = ctrl.create(p);
				if(a) {
					Alert al = new Alert(AlertType.INFORMATION);
					al.setTitle("Cadastrado Com Sucesso");
					al.setContentText("Você está cadastrado no sistema");
					al.show();
					limparCampos();
				}else {
					Alert al = new Alert(AlertType.ERROR);
					al.setTitle("ERRO");
					al.setContentText("Algo deu errado!");
					al.show();
				}
			}else {
				Alert a = new Alert(AlertType.INFORMATION);
				a.setTitle("A confirmação falhou");
				a.setContentText("verifique se a senha e confirmar senha são iguais");
				a.show();
			}
		}else {
			tp.login();
		}
	}
}
