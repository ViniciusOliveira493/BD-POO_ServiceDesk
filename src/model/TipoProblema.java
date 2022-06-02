package model;

public class TipoProblema {
	private int id;
	private String nome;
	private int tempoResolucao;
	
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getResolucao() {
		return tempoResolucao;
	}
	public void setResolucao(int tempoResolucao) {
		this.tempoResolucao = tempoResolucao;
	}

}
