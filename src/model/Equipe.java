package model;

public class Equipe {
	private int id;
	private String nome;
	private Setor setor;
	
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	public Setor getsetor() {
		return setor;
	}
	public void setsetor(Setor setor) {
		this.setor = setor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
