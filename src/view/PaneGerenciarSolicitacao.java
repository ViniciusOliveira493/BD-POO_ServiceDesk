package view;

import java.util.Date;

import controller.ProdutoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Produto;
import model.Solicitacao;
import model.TipoProblema;

public class PaneGerenciarSolicitacao extends Pane implements EventHandler<ActionEvent>{
	Button btnSolicitar;
	Button btnLimpar;
	Button btnEditar;
	Button btnCancelar;
	
	TextArea txtDescricaoProblema;
	ComboBox<TipoProblema> cbTipoProblema;
	ComboBox<String> cbProduto;
	ComboBox<String> cbSetor;
	
	private ObservableList<TipoProblema> tiposProblemas = FXCollections.observableArrayList();
	private ObservableList<Produto> produtos = FXCollections.observableArrayList();
	private ObservableList<String> produtosNome = FXCollections.observableArrayList();
	
	private ObservableList<Produto> setores = FXCollections.observableArrayList();
	private ObservableList<String> setoresNome = FXCollections.observableArrayList();
	
	private ObservableList<Solicitacao> solicitacoes = FXCollections.observableArrayList();
	private TableView<Solicitacao> table = new TableView<>();
	
	private ProdutoController ctrlProd = new ProdutoController();
	public PaneGerenciarSolicitacao() {		
		VBox vb = new VBox();
		init(vb);
		this.getChildren().add(vb);
	}
	
	private void init(VBox vb) {
		atualizar();
		criarTxtDescProblema(vb);
		criarCbTipoProblema(vb);
		criarCbProduto(vb);
		criarCbSetor(vb);
		criarBotoesSolicitacao(vb);
		criarTable(vb);
		criarBotoesTable(vb);
	}
	
	private void criarTxtDescProblema(VBox vb) {
		HBox hb3 = new HBox();
		Label lblDescProblema = new Label();
		lblDescProblema.setText("Descreva o problema que ocorreu: ");
		txtDescricaoProblema = new TextArea();
		txtDescricaoProblema.setEditable(false);
		hb3.getChildren().addAll(lblDescProblema,txtDescricaoProblema);
		vb.getChildren().add(hb3);
	}
	
	private void criarCbTipoProblema(VBox vb) {
		HBox hb4 = new HBox();
		Label lblTipoProblema = new Label();
		lblTipoProblema.setText("Selecione o tipo de problema: ");
		cbTipoProblema = new ComboBox<TipoProblema>();
		hb4.getChildren().addAll(lblTipoProblema,cbTipoProblema);
		vb.getChildren().add(hb4);
	}
	
	private void criarCbProduto(VBox vb) {
		HBox hb4 = new HBox();
		Label lblProduto = new Label();
		lblProduto.setText("Selecione o produto que apresentou o problema: ");
		cbProduto = new ComboBox<String>(produtosNome);
		hb4.getChildren().addAll(lblProduto,cbProduto);
		vb.getChildren().add(hb4);
	}
	
	private void criarBotoesSolicitacao(VBox vb) {
		btnSolicitar = new Button("Fazer Solicitação");		
		btnLimpar = new Button("Limpar Campos");
		
		btnSolicitar.addEventFilter(ActionEvent.ANY, this);
		btnLimpar.addEventFilter(ActionEvent.ANY, this);
		
		HBox hb4 = new HBox();
		hb4.setSpacing(20);
		hb4.setMinHeight(40);
		
		
		hb4.getChildren().addAll(btnSolicitar,btnLimpar);
		hb4.setAlignment(Pos.BASELINE_RIGHT);
		vb.getChildren().add(hb4);
	}
	
	private void criarBotoesTable(VBox vb) {
		btnEditar = new Button("Editar");		
		btnCancelar = new Button("Cancelar");
		
		btnEditar.addEventFilter(ActionEvent.ANY, this);
		btnCancelar.addEventFilter(ActionEvent.ANY, this);
		
		HBox hb4 = new HBox();
		hb4.setSpacing(10);
		
		
		hb4.getChildren().addAll(btnEditar,btnCancelar);
		hb4.setAlignment(Pos.BASELINE_RIGHT);
		hb4.setMinHeight(40);
		vb.getChildren().add(hb4);
	}
	
	private void criarCbSetor(VBox vb) {
		HBox hb4 = new HBox();
		Label lblSetor = new Label();
		lblSetor.setText("Selecione o setor que atende este problema: ");
		cbSetor = new ComboBox<String>(setoresNome);
		hb4.getChildren().addAll(lblSetor,cbSetor);
		vb.getChildren().add(hb4);
	}
	
	private void criarTable(VBox vb) {
		TableColumn<Solicitacao, String> col1 = new TableColumn<>("ID");
		col1.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Solicitacao, String> col2 = new TableColumn<>("Descricao");
		col2.setCellValueFactory(new PropertyValueFactory<>("descricao"));		
		
		table.getColumns().addAll(col1,col2);
		table.setItems(this.solicitacoes);
		table.setMaxHeight(200);
		vb.getChildren().add(table);
	}
	
	private void atualizar() {
		produtos.clear();
		produtos.addAll(ctrlProd.search(""));
		for(Produto p:produtos) {
			produtosNome.add(p.getNome());
		}
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

}
