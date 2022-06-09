package ServiceDesk;

import javax.swing.JOptionPane;

import controller.TipoProblemaController;
import javafx.event.ActionEvent;
import javafx.collection.FXCollection;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PaneGerenciarTipoProblema extends Pane implements EventHandler<ActionEvent>{
	private TableView<TipoProblema> table = new TableView<>();
	private ObservableList<TipoProblema> tipoProblema = FXCollection.observableArrayList();
	 Button btnAdd;
	 Button btnRemove;
	 Button btnEdit;
	 Button btnPesquisar;
	
	TextField txtNome;
	TipoProblemaCOntroller ctr = new TipoProblemaController();
	public PaneGerenciarTipoProblema() {
		VBox vb = new Vbox();
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
		TableColumn<TipoProblema, Integer> col1 = new TableColumn<>("ID");
		col1.setCellValueFactory(new PropertyValueFactory<>("id"));		
		
		TableColumn<TipoProblema, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<TipoProblema, Integer> col3 = new TableColumn<>("Tempo de Resolucao");
		col3.setCellValueFactory(new PropertyValueFactory<>("tempoderesolucao"));		
		
			
		
		table.getColumns().addAll(col1,col2,col3);
		table.setItems(this.tipoProblema);
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
	private void adicionarTipoProblema() {
		TipoProblema p = new TipoProblema();
		String nome = txtNome.getText();
		if(nome!="" && nome!=null) {
			p.setNome(nome);
			if(ctrl.create(p)) {
				Alerta a = new Alerta(AlertType.INFORMATION, "Sucesso", "O tipo do problema foi registrado");
				atualizar("");
				limparNome();
			}
			private void limparNome() {
				txtNome.setText("");		
			}
			private void atualizar(String pesq) {
				tipoProblema.clear();
				tipoProblema.addAll(ctrl.search(pesq));
				table.refresh();
			}
			private void editarTipoProblema() {
				TipoProblema p1 = new TipoProblema();
				p1.setid(table.getSelectionModel().getSelectedItem().getid());
				String Nome = table.getSelectionModel().getSelectedItem().getNome();
				Nome = JOptionPane.showInputDialog("Digite o nome nome para "+Nome);
				p1.setNome(Nome);
				ctrl.update(p1);
				atualizar("");
			}
			private void exluirTipoProblema() {
				TipoProblema p = new TipoProblema();
				p.setid(table.getSelectionModel().getSelectedItem().getid());
				int excluir = JOptionPane.showConfirmDialog(null, "Deseja Excluir o problema?");
				if(excluir == 0) {
					ctrl.delete(p);
					atualizar("");
				}
			

		}
			@Override
			public void handle(ActionEvent event) {
				if(event.getSource().equals(btnAdd)) {
					adicionarTipoProblema();
				}else if(event.getSource().equals(btnPesquisar)) {
					atualizar(txtNome.getText());
				}else if(event.getSource().equals(btnEdit)) {
					editarTipoProblema();
				}else if(event.getSource().equals(btnRemove)) {
					exluirTipoProblema();
				}
			}
		
	}

}
