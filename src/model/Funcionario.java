package model;

public class Funcionario extends Pessoa{
	private int equipeId;//mudar para equipe inteira dps
	private int nivel;
	
	public int getEquipeId() {
		return equipeId;
	}
	public void setEquipeId(int equipeId) {
		this.equipeId = equipeId;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
}
