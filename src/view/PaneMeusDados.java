package view;

import java.util.Date;

import javax.swing.JOptionPane;

import controller.EnderecoController;
import controller.PessoaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Endereco;
import model.Pessoa;


public class PaneMeusDados extends Pane implements EventHandler<ActionEvent>{
	TextField txtCpf;
	TextField txtNome;
	PasswordField txtSenha;
	PasswordField txtConfSenha;
	PasswordField txtSenhaAnterior;
	
	Button btnSalvar;
	Button btnCancelar;
	Button btnAdd;
	Button btnRemove;
	Button btnEdit;
	
	TableView<Endereco> table = new TableView<>();
	PessoaController ctrlPess = new PessoaController();
	private ObservableList<Endereco> enderecos = FXCollections.observableArrayList();
	Pessoa pessoa = new Pessoa();
	public PaneMeusDados(Pessoa p) {
		this.pessoa = ctrlPess.read(p.getCpf());
		atualizarLista();
		
		VBox vb = new VBox();
		init(vb);
		this.getChildren().add(vb);
	}

	private void init(VBox vb) {
		criarTxtCpf(vb);
		criarTxtNome(vb);
		
		/*
		criarLblAltSenha(vb);
		criarTxtSenhaAnterior(vb);		
		criarTxtSenha(vb);
		criarTxtConfSenha(vb);
		*/
		criarTable(vb);
		criarBotoesTb(vb);
		
		criarBotoes(vb);
	}
	
	private void criarLblAltSenha(VBox vb) {
		HBox hb1 = new HBox();
		Label lblAltSenha = new Label();
		lblAltSenha.setText("Alterar Senha");
		hb1.getChildren().add(lblAltSenha);
		vb.getChildren().add(hb1);
	}
	
	private void criarTxtNome(VBox vb) {
		HBox hb2 = new HBox();
		Label lblNome = new Label();
		lblNome.setText("Nome: ");
		txtNome = new TextField();
		txtNome.setText(pessoa.getNome());
		hb2.getChildren().addAll(lblNome,txtNome);
		vb.getChildren().add(hb2);
	}	
	
	private void criarTxtCpf(VBox vb) {
		HBox hb3 = new HBox();
		Label lblCpf = new Label();
		lblCpf.setText("CPF: ");
		txtCpf = new TextField();
		txtCpf.setText(pessoa.getCpf());
		txtCpf.setEditable(false);
		hb3.getChildren().addAll(lblCpf,txtCpf);
		vb.getChildren().add(hb3);
	}
	
	private void criarTxtSenhaAnterior(VBox vb) {
		HBox hb4 = new HBox();
		Label lblSenhaAnterior = new Label();
		lblSenhaAnterior.setText("Senha Antiga: ");
		txtSenhaAnterior = new PasswordField();
		hb4.getChildren().addAll(lblSenhaAnterior,txtSenhaAnterior);
		vb.getChildren().add(hb4);
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
	
	private void criarTable(VBox vb) {
		TableColumn<Endereco, String> col1 = new TableColumn<>("CEP");
		col1.setCellValueFactory(new PropertyValueFactory<>("cep"));
		
		TableColumn<Endereco, String> col2 = new TableColumn<>("Logradouro");
		col2.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
		
		TableColumn<Endereco, Integer> col3 = new TableColumn<>("Número");
		col3.setCellValueFactory(new PropertyValueFactory<>("numero"));		
		
		TableColumn<Endereco, Date> col4 = new TableColumn<>("Descricao");
		col4.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		
		table.getColumns().addAll(col1,col2,col3,col4);
		table.setItems(this.enderecos);
		vb.getChildren().add(table);
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

	private void criarBotoesTb(VBox vb) {
		btnAdd = new Button("Add");
		btnRemove = new Button("Remove");		
		btnEdit = new Button("Editar");
		
		btnAdd.addEventFilter(ActionEvent.ANY, this);
		btnRemove.addEventFilter(ActionEvent.ANY, this);
		btnEdit.addEventFilter(ActionEvent.ANY, this);
		
		HBox hb4 = new HBox();
		hb4.setSpacing(10);
		
		
		hb4.getChildren().addAll(btnAdd,btnEdit,btnRemove);
		vb.getChildren().add(hb4);
	}
	
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(btnRemove)) {	
			int id = obterTableSelecionado();	
			if(id>=0) {
				apagarEndereco(id);
			}
		}else if(event.getSource().equals(btnAdd)){
			addEndereco();
		}else if(event.getSource().equals(btnEdit)){
			int id = obterTableSelecionado();
			if(id>=0) {
				editarEndereco(id);
			}			
		}else if(event.getSource().equals(btnSalvar)){
			pessoa.setNome(txtNome.getText());
			boolean res = ctrlPess.update(pessoa);
			if(res) {
				Alerta a = new Alerta(AlertType.INFORMATION,"Sucesso","Os dados foram atualizados");
			}else {
				Alerta a = new Alerta(AlertType.ERROR,"Erro","Algo deu errado");
			}
		}
		
	}
	
	private int obterTableSelecionado() {
		try {
			int id = table.getSelectionModel().getSelectedItem().getId();
			return id;
		} catch (Exception e) {
			Alerta a = new Alerta(AlertType.WARNING,"Selecione um item", "é necessario selecionar um item");
		}	
		return -1;
	}
	
	private void apagarEndereco(int id) {
		Alerta a = new Alerta(AlertType.CONFIRMATION, "Excluir Endereço", "Deseja excluir este endereco ?");
		ButtonType res = a.getResult();
		if(res.getText().equals("OK")) {
			int pos = getTablePos(id);
			pessoa.getEnderecos().remove(pos);
		}
		atualizarLista();
	}
	
	private int getTablePos(int id) {		
		for(int i=0;i<pessoa.getEnderecos().size();i++) {
			if(pessoa.getEnderecos().get(i).getId()==id) {			
				return i;
			}
		}		
		return -1;
	}

	private void addEndereco() {
		String nCep = JOptionPane.showInputDialog("Digite o cep");			
		String logradouro = JOptionPane.showInputDialog("Digite o logradouro");
		boolean numInvalido = true;
		int num = 0;
		while(numInvalido) {
			try {
				String nNum = JOptionPane.showInputDialog("Digite o numero");
				num = Integer.parseInt(nNum);
				numInvalido = false;
			} catch (Exception e) {
				Alerta a = new Alerta(AlertType.WARNING,"Erro","Você digitou um número inválido");
			}
			
		}
		String desc = JOptionPane.showInputDialog("Digite a descricao");
		
		Endereco end = new Endereco();
		end.setCep(nCep);
		end.setNumero(num);
		end.setLogradouro(logradouro);
		end.setDescricao(desc);
		end.setPessoaCPF(pessoa.getCpf());
		pessoa.getEnderecos().add(end);
		atualizarLista();
	}
	
	private void editarEndereco(int id) {
		String nCep = JOptionPane.showInputDialog("Digite o novo cep");			
		String logradouro = JOptionPane.showInputDialog("Digite o novo logradouro");
		boolean numInvalido = true;
		int num = 0;
		while(numInvalido) {
			try {
				String nNum = JOptionPane.showInputDialog("Digite o novo numero");
				num = Integer.parseInt(nNum);
				numInvalido = false;
			} catch (Exception e) {
				Alerta a = new Alerta(AlertType.WARNING,"Erro","Você digitou um número inválido");
			}
			
		}
		String desc = JOptionPane.showInputDialog("Digite a nova descricao");
		
		Endereco end = new Endereco();
		end.setCep(nCep);
		end.setNumero(num);
		end.setLogradouro(logradouro);
		end.setDescricao(desc);
		end.setPessoaCPF(pessoa.getCpf());
		end.setId(id);
		
		int pos = getTablePos(id);
		pessoa.getEnderecos().set(pos, end);
		atualizarLista();
		
	}
	
	private void atualizarLista() {
		this.enderecos.clear();
		this.enderecos.addAll(pessoa.getEnderecos());
		this.table.refresh();
	}
}
