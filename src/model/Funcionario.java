package model;

public class Funcionario extends Pessoa{
	private Equipe equipe;//mudar para equipe inteira dps
	private int nivel;
	
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
}
