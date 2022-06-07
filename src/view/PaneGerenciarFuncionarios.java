package view;

import javax.swing.JOptionPane;

import controller.FuncionarioController;
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
import model.Equipe;
import model.Funcionario;

public class PaneGerenciarFuncionarios extends Pane implements EventHandler<ActionEvent>{
	private TableView<Funcionario> table = new TableView<>();
	private ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList();
	private FuncionarioController ctrl = new FuncionarioController();
	
	private TextField txtNome;
	private Button btnAdd;
	private Button btnPesquisar;
	private Button btnRemove;
	private Button btnEdit;
	private Button btnObterFuncSenhapadrao;
	
	private int nivelFunc = 50;
	public PaneGerenciarFuncionarios(int nivelFuncionario) {
		nivelFunc = nivelFuncionario;
		VBox vb = new VBox();
		init(vb);
		this.getChildren().add(vb);
		atualizar("");
	}
	
	private void init(VBox vb){
		criarTxtNome(vb);
		criarBotoesNome(vb);
		criarTable(vb);
		criarBotoesTb(vb);
		criarBtnFuncSenhaPadrao(vb);
		alertaSenhaPadrao();
	}
	
	private void criarTable(VBox vb) {
		TableColumn<Funcionario, String> col1 = new TableColumn<>("CPF");
		col1.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		
		TableColumn<Funcionario, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Funcionario, Integer> col3 = new TableColumn<>("Equipe");
		col3.setCellValueFactory(new PropertyValueFactory<>("equipe"));		
		
		TableColumn<Funcionario, String> col4 = new TableColumn<>("Cargo");
		col4.setCellValueFactory(new PropertyValueFactory<>("cargo"));
		
		table.getColumns().addAll(col1,col2,col3,col4);
		table.setItems(this.funcionarios);
		vb.getChildren().add(table);
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
	private void criarBtnFuncSenhaPadrao(VBox vb) {
		btnObterFuncSenhapadrao = new Button("Funcionarios com senha padrao");	
		btnObterFuncSenhapadrao.addEventFilter(ActionEvent.ANY, this);
		
		HBox hb4 = new HBox();
		hb4.setSpacing(10);
		hb4.getChildren().add(btnObterFuncSenhapadrao);
		vb.getChildren().add(hb4);
	}
	private void criarTxtNome(VBox vb) {
		HBox hb2 = new HBox();
		Label lblNome = new Label();
		lblNome.setText("Nome: ");
		txtNome = new TextField();
		hb2.getChildren().addAll(lblNome,txtNome);
		vb.getChildren().add(hb2);
	}
	
	private void atualizar(String nome){
		funcionarios.clear();
		funcionarios.addAll(ctrl.listar(nome));
		table.refresh();
	}
	
	private void addFunc() {
		Funcionario f = obterEquipeECargo();
		f.setCpf(JOptionPane.showInputDialog("Digite o cpf do funcionario"));
		ctrl.create(f);
		atualizar("");
	}
	private void excluirFunc() {
		Funcionario f = getSelecionado();
		int excluir = JOptionPane.showConfirmDialog(null, "Deseja Excluir o funcionário ?");
		if(excluir == 0) {
			ctrl.delete(f);
			atualizar("");
		}
	}
	private void editarFunc() {
		Funcionario f = getSelecionado();
		Funcionario f2 = obterEquipeECargo();
		f.setEquipe(f2.getEquipe());
		f.setNivel(f2.getNivel());
		ctrl.update(f);
	}
	private Funcionario getSelecionado() {
		Funcionario f = new Funcionario();
		try {
			f = table.getSelectionModel().getSelectedItem();
		} catch (Exception e) {
			Alerta a = new Alerta(AlertType.ERROR, "ERRO", "Você não selecionaou um funcionário na tabela");
		}
		return f;
	}
	private Funcionario obterEquipeECargo() {
		Funcionario f = new Funcionario();
		try {
			int nivel = Integer.parseInt(
					JOptionPane.showInputDialog("Digite o cargo do funcionario\n"
							+ "0 = Admin\n"
							+ "1 = Técnico\n"
							+ "2 = Atendente\n"));
			f.setNivel(nivel);
			int equipe = Integer.parseInt(
					JOptionPane.showInputDialog("Digite o codigo da equipe"
							+ " que o funcionario será alocado"));
			Equipe e = new Equipe();
			e.setid(equipe);
			f.setEquipe(e);
		} catch (Exception e) {
			Alerta a = new Alerta(AlertType.ERROR,"ERRO","Você digitou um valor inválido");
		}
		
		return f;
	}
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(btnPesquisar)) {
			String nome = txtNome.getText();
			atualizar(nome);
		}else if(event.getSource().equals(btnAdd)) {
			addFunc();
			atualizar("");
		}else if(event.getSource().equals(btnRemove)) {
			excluirFunc();
			atualizar("");
		}else if(event.getSource().equals(btnEdit)) {
			editarFunc();
			atualizar("");
		}else if(event.getSource().equals(btnObterFuncSenhapadrao)) {
			Alerta a = new Alerta(AlertType.INFORMATION,"Funcionarios",ctrl.funcionariosSenhaPadrão());
		}
	}
	private void alertaSenhaPadrao() {
		if(nivelFunc == 0) {
			int qtd = ctrl.contarFuncSenhaPadrao();
			if(qtd>0) {
				Alerta a = new Alerta(AlertType.WARNING,"Alerta de Segurança","existem "+qtd+" Funcionários utilizando a senha padrão");
			}
		}		
	}
}
