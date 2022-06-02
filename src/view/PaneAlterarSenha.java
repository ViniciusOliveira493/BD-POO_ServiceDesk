package view;

import controller.PessoaController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Pessoa;

public class PaneAlterarSenha extends Pane implements EventHandler<ActionEvent>{
	PasswordField txtSenha;
	PasswordField txtConfSenha;
	PasswordField txtSenhaAnterior;
	
	Button btnSalvar;
	Button btnCancelar;
	
	PessoaController ctrlPess = new PessoaController();

	Pessoa pessoa = new Pessoa();
	public PaneAlterarSenha(Pessoa p) {
		this.pessoa = ctrlPess.read(p.getCpf());
		
		VBox vb = new VBox();
		init(vb);
		this.getChildren().add(vb);
	}

	private void init(VBox vb) {
		criarLblAltSenha(vb);		
		criarTxtSenha(vb);
		criarTxtConfSenha(vb);
		
		criarBotoes(vb);
	}
	
	private void criarLblAltSenha(VBox vb) {
		HBox hb1 = new HBox();
		Label lblAltSenha = new Label();
		lblAltSenha.setText("Alterar Senha");
		hb1.getChildren().add(lblAltSenha);
		vb.getChildren().add(hb1);
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
		btnSalvar = new Button("Salvar");
		btnCancelar = new Button("Cancelar");
		
		btnSalvar.addEventFilter(ActionEvent.ANY, this);
		btnCancelar.addEventFilter(ActionEvent.ANY, this);
		
		HBox hb4 = new HBox();
		hb4.setSpacing(10);
		
		
		hb4.getChildren().addAll(btnCancelar,btnSalvar);
		vb.getChildren().add(hb4);
	}
	
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(btnSalvar)){
			if(txtSenha.getText().equals(txtConfSenha.getText())) {
				pessoa.setSenha(txtSenha.getText());
				boolean res = ctrlPess.updateSenha(pessoa);
				if(res) {
					Alerta a = new Alerta(AlertType.INFORMATION,"Sucesso","A senha foi atualizada");
				}else {
					Alerta a = new Alerta(AlertType.ERROR,"Erro","Algo deu errado");
				}
			}else {
				Alerta a = new Alerta(AlertType.INFORMATION,"A confirmação falhou","verifique se a senha e confirmar senha são iguais");
			}
		}
		
	}
}
