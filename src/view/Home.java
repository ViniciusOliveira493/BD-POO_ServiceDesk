package view;

import controller.PessoaController;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Pessoa;

public class Home extends Scene{
	private GridPane gp;
	private Pessoa pessoa;
	private TelaPrincipal tp;
	private PaneMeusDados pmd;
	private PaneAlterarSenha pas;
	private PaneGerenciarFuncionarios pgf;
	private PaneGerenciarProdutos pgp;
	private PaneGerenciarSolicitacao pgs;
	private PaneAtenderSolicitacoes pasc;
	
	private PessoaController ctrlp = new PessoaController();
	public Home(Pane root,Pessoa p,TelaPrincipal tp) {
		super(root,1000,600);
		this.pessoa = p;
		this.tp = tp;
		atualizar();		
		startGridPane();		
		init();
		root.getChildren().add(gp);
	}
	private void startGridPane() {
		gp = new GridPane();
		gp.setHgap(30);
		
		pmd = new PaneMeusDados(pessoa);
		gp.add(pmd, 1, 0);
		
		pas = new PaneAlterarSenha(pessoa);
		gp.add(pas, 1, 0);
		
		pgf = new PaneGerenciarFuncionarios();
		gp.add(pgf, 1, 0);
		
		pgp = new PaneGerenciarProdutos();
		gp.add(pgp, 1, 0);
		
		pgs = new PaneGerenciarSolicitacao();
		gp.add(pgs, 1, 0);	
		
		pasc = new PaneAtenderSolicitacoes();
		gp.add(pasc, 1, 0);	
	}
	
	public void init() {
		tp.getStage().setTitle("Home - ["+pessoa.getNome()+"]");
		PaneSideMenu csm = new PaneSideMenu(this);
		gp.add(csm, 0, 0);
		paneMeusDados();
	}
	
	public void paneMeusDados() {
		hideAll();
		pmd.setVisible(true);
		
	}
	
	public void paneGerenciarFuncionarios() {
		hideAll();
		pgf.setVisible(true);
		
	}

	public void alterarSenha() {
		hideAll();		
		pas.setVisible(true);	
	}
	
	public void gerenciarProdutos() {
		hideAll();		
		pgp.setVisible(true);		
	}
	
	public void atenderSolicitacoes() {
		hideAll();		
		pasc.setVisible(true);		
	}
	
	public void atualizar() {
		pessoa = ctrlp.read(pessoa.getCpf());
		tp.getStage().setTitle("Home - ["+pessoa.getNome()+"]");
	}
	
	public void gerenciarSolicitacao() {
		hideAll();		
		pgs.setVisible(true);		
	}
	
	private void hideAll() {
		pmd.setVisible(false);
		pas.setVisible(false);
		pgf.setVisible(false);
		pgp.setVisible(false);
		pgs.setVisible(false);
		pasc.setVisible(false);
	}
	
	
}
