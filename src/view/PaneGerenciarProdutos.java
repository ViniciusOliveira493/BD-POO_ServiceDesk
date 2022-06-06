package view;

import java.util.Date;

import javax.swing.JOptionPane;

import controller.ProdutoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Produto;

public class PaneGerenciarProdutos extends Pane implements EventHandler<ActionEvent>{
	private TableView<Produto> table = new TableView<>();
	private ObservableList<Produto> produtos = FXCollections.observableArrayList();
	
	Button btnAdd;
	Button btnRemove;
	Button btnEdit;
	Button btnPesquisar;
	
	TextField txtNome;
	
	ProdutoController ctrl = new ProdutoController();
	public PaneGerenciarProdutos() {
		VBox vb = new VBox();
		init(vb);
		this.getChildren().add(vb);
		atualizar("");
	}

	private void init(VBox vb) {
		criarTxtNome(vb);
		criarBotoesNome(vb);
		criarTable(vb);	
		criarBotoesTb(vb);
	}

	private void criarTable(VBox vb) {
		TableColumn<Produto, Integer> col1 = new TableColumn<>("ID");
		col1.setCellValueFactory(new PropertyValueFactory<>("id"));		
		
		TableColumn<Produto, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Produto, String> col3 = new TableColumn<>("Total de Solicitações");
		col3.setCellValueFactory(new PropertyValueFactory<>("totalSolicitacao"));		
		
		TableColumn<Produto, Date> col4 = new TableColumn<>("Solicitações na Semana");
		col4.setCellValueFactory(new PropertyValueFactory<>("totalSolicitacaoSemana"));
		
		TableColumn<Produto, String> col5 = new TableColumn<>("Status");
		col5.setCellValueFactory(new PropertyValueFactory<>("estado"));		
		
		table.getColumns().addAll(col1,col2,col3,col4,col5);
		table.setItems(this.produtos);
		table.setMinWidth(500);
		vb.getChildren().add(table);
	}
	
	private void criarTxtNome(VBox vb) {
		HBox hb2 = new HBox();
		Label lblNome = new Label();
		lblNome.setText("Nome: ");
		txtNome = new TextField();
		hb2.getChildren().addAll(lblNome,txtNome);
		vb.getChildren().add(hb2);
	}	
	
	private void criarBotoesNome(VBox vb) {
		btnAdd = new Button("Adicionar");		
		btnPesquisar = new Button("Pesquisar");
		
		btnAdd.addEventFilter(ActionEvent.ANY, this);
		btnPesquisar.addEventFilter(ActionEvent.ANY, this);
		
		HBox hb4 = new HBox();
		hb4.setSpacing(10);
		
		
		hb4.getChildren().addAll(btnAdd,btnPesquisar);
		vb.getChildren().add(hb4);
	}
	
	private void criarBotoesTb(VBox vb) {
		btnRemove = new Button("Apagar");		
		btnEdit = new Button("Editar");
		
		btnRemove.addEventFilter(ActionEvent.ANY, this);
		btnEdit.addEventFilter(ActionEvent.ANY, this);
		
		HBox hb4 = new HBox();
		hb4.setSpacing(10);
		
		
		hb4.getChildren().addAll(btnEdit,btnRemove);
		vb.getChildren().add(hb4);
	}
	
	private void adicionarProduto() {
		Produto p = new Produto();
		String nome = txtNome.getText();
		if(nome!="" && nome!=null) {
			p.setNome(nome);
			if(ctrl.create(p)) {
				Alerta a = new Alerta(AlertType.INFORMATION, "Sucesso", "O produto foi cadastrado");
				atualizar("");
				limparNome();
			}
		}
		
	}
	
	private void limparNome() {
		txtNome.setText("");		
	}

	private void atualizar(String pesq) {
		produtos.clear();
		produtos.addAll(ctrl.search(pesq));
		table.refresh();
	}

	private void editarProduto() {
		Produto p = new Produto();
		p.setId(table.getSelectionModel().getSelectedItem().getId());
		String nome = table.getSelectionModel().getSelectedItem().getNome();
		nome = JOptionPane.showInputDialog("Digite o nome nome para "+nome);
		p.setNome(nome);
		ctrl.update(p);
		atualizar("");
	}
	
	private void exluirProduto() {
		Produto p = new Produto();
		p.setId(table.getSelectionModel().getSelectedItem().getId());
		int excluir = JOptionPane.showConfirmDialog(null, "Deseja Excluir o produto ?");
		if(excluir == 0) {
			ctrl.delete(p);
			atualizar("");
		}
	}
	
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(btnAdd)) {
			adicionarProduto();
		}else if(event.getSource().equals(btnPesquisar)) {
			atualizar(txtNome.getText());
		}else if(event.getSource().equals(btnEdit)) {
			editarProduto();
		}else if(event.getSource().equals(btnRemove)) {
			exluirProduto();
		}
	}
	
}
