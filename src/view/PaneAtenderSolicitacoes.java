package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Solicitacao;

public class PaneAtenderSolicitacoes extends Pane implements EventHandler<ActionEvent>{
	Button btnFinalizar;
	Button btnAtender;
	Button btnEditar;
	
	private ObservableList<Solicitacao> solicitacoes = FXCollections.observableArrayList();
	private TableView<Solicitacao> table = new TableView<>();
	public PaneAtenderSolicitacoes() {
		VBox vb = new VBox();
		init(vb);
		this.getChildren().add(vb);
	}
	
	private void init(VBox vb) {
		criarTable(vb);
		criarBotoesTb(vb);
	}
	
	private void criarTable(VBox vb) {
		TableColumn<Solicitacao, String> col1 = new TableColumn<>("ID");
		col1.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Solicitacao, String> col2 = new TableColumn<>("Descricao");
		col2.setCellValueFactory(new PropertyValueFactory<>("descricao"));		
		
		table.getColumns().addAll(col1,col2);
		table.setItems(this.solicitacoes);
		table.setMinWidth(getMaxWidth());
		vb.getChildren().add(table);
	}
	
	private void criarBotoesTb(VBox vb) {
		btnAtender = new Button("Atender");		
		btnFinalizar = new Button("Finalizar");
		btnEditar = new Button("Editar");
		
		btnAtender.addEventFilter(ActionEvent.ANY, this);
		btnFinalizar.addEventFilter(ActionEvent.ANY, this);
		btnEditar.addEventFilter(ActionEvent.ANY, this);
		
		HBox hb4 = new HBox();
		hb4.setSpacing(10);
		
		
		hb4.getChildren().addAll(btnAtender,btnFinalizar,btnEditar);
		vb.getChildren().add(hb4);
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
