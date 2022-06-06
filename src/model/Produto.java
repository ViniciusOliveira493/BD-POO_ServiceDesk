package model;

public class Produto {
	private int id;
	private String nome;
	private int totalSolicitacao;
	private int totalSolicitacaoSemana;
	private String estado;
	
	public int getTotalSolicitacaoSemana() {
		return totalSolicitacaoSemana;
	}
	public void setTotalSolicitacaoSemana(int totalSolicitacaoSemana) {
		this.totalSolicitacaoSemana = totalSolicitacaoSemana;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTotalSolicitacao() {
		return totalSolicitacao;
	}
	public void setTotalSolicitacao(int totalSolicitacao) {
		this.totalSolicitacao = totalSolicitacao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
