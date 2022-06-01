package view;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Pessoa;

public class Home extends Scene{
	private GridPane gp;
	private Pessoa pessoa;
	private TelaPrincipal tp;
	public Home(Pane root,Pessoa p,TelaPrincipal tp) {
		super(root,1000,600);
		this.pessoa = p;	
		this.tp = tp;
		gp = new GridPane();
		gp.setGridLinesVisible(true);
		init();
		root.getChildren().add(gp);
	}

	public void init() {
		tp.getStage().setTitle("Home - ["+pessoa.getNome()+"]");
		PaneSideMenu csm = new PaneSideMenu(this);
		gp.add(csm, 0, 0);
		paneMeusDados();
	}
	
	public void paneMeusDados() {
		PaneMeusDados pmd = new PaneMeusDados();
		gp.add(pmd, 1, 0);
	}
}
