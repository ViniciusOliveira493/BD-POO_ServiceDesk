package model;

import java.sql.Date;

public class Chamado {
	private int id;
	private Solicitacao solicitacao;
	private Equipe equipe;
	private Date data;
	private Endereco endereco;
	
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	public Solicitacao getsolicitacao() {
		return solicitacao;
	}
	public void setsolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}
	public Equipe getequipe() {
		return equipe;
	}
	public void setequipe(Equipe equipe) {
		this.equipe = equipe;
	}
	public Date getdata() {
		return data;
	}
	public void setdata(Date data) {
		this.data = data;
	}
	public Endereco getendereco() {
		return endereco;
	}
	public void setendereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
