package view;

import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Funcionario;

public class PaneGerenciarFuncionarios extends Pane implements EventHandler<ActionEvent>{
	private TableView<Funcionario> table = new TableView<>();
	private ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList();
	public PaneGerenciarFuncionarios() {		
		VBox vb = new VBox();
		init(vb);
		this.getChildren().add(vb);
	}
	
	private void init(VBox vb){
		criarTable(vb);
	}
	
	private void criarTable(VBox vb) {
		TableColumn<Funcionario, String> col1 = new TableColumn<>("CPF");
		col1.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		
		TableColumn<Funcionario, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Funcionario, Integer> col3 = new TableColumn<>("Equipe");
		col3.setCellValueFactory(new PropertyValueFactory<>("equipe"));		
		
		TableColumn<Funcionario, Date> col4 = new TableColumn<>("Nivel");
		col4.setCellValueFactory(new PropertyValueFactory<>("nivel"));
		
		table.getColumns().addAll(col1,col2,col3,col4);
		table.setItems(this.funcionarios);
		vb.getChildren().add(table);
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
