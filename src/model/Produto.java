package model;

public class Produto {
	private int id;
	private String nome;
	private int totalSolicitacao;
	private int solicitacaoSemana;
	
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
	public int getSolicitacaoSemana() {
		return solicitacaoSemana;
	}
	public void setSolicitacaoSemana(int solicitacaoSemana) {
		this.solicitacaoSemana = solicitacaoSemana;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
