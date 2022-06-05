package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PaneSideMenu extends Pane implements EventHandler<ActionEvent>{
	Button btnMeusDados;
	Button btnCadastrarFuncionario;
	Button btnAltSenha;
	Button btnProdutos;
	
	Home hm;
	public PaneSideMenu(Home hm) {
		this.hm = hm;
		VBox vb = new VBox();
		init(vb);
		this.getChildren().add(vb);
	}
	
	private void init(VBox vb) {
		criarBtnMeusDados(vb);
		criarBtnAltSenha(vb);
		criarBtnCadFuncionario(vb);
		criarBtnProdutos(vb);
	}
	
	private void criarBtnMeusDados(VBox vb) {
		HBox hb1 = new HBox();
		btnMeusDados = new Button("Meus Dados");
		btnMeusDados.addEventHandler(ActionEvent.ANY, this);
		hb1.getChildren().add(btnMeusDados);
		vb.getChildren().add(hb1);
	}

	private void criarBtnAltSenha(VBox vb) {
		HBox hb1 = new HBox();
		btnAltSenha = new Button("Alterar Senha");
		btnAltSenha.addEventHandler(ActionEvent.ANY, this);
		hb1.getChildren().add(btnAltSenha);
		vb.getChildren().add(hb1);
	}
	
	private void criarBtnCadFuncionario(VBox vb) {
		HBox hb1 = new HBox();
		btnCadastrarFuncionario = new Button("Gerenciar Funcionarios");
		btnCadastrarFuncionario.addEventHandler(ActionEvent.ANY, this);
		hb1.getChildren().add(btnCadastrarFuncionario);
		vb.getChildren().add(hb1);
	}
	
	private void criarBtnProdutos(VBox vb) {
		HBox hb1 = new HBox();
		btnProdutos = new Button("Gerenciar Produtos");
		btnProdutos.addEventHandler(ActionEvent.ANY, this);
		hb1.getChildren().add(btnProdutos);
		vb.getChildren().add(hb1);
	}
	
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == btnMeusDados){
			hm.paneMeusDados();
		}else if(event.getSource() == btnCadastrarFuncionario){
			hm.paneGerenciarFuncionarios();
		}else if(event.getSource() == btnAltSenha){
			hm.alterarSenha();
		}else if(event.getSource() == btnProdutos){
			hm.gerenciarProdutos();
		}
		
	}
}
