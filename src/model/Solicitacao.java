package model;

import java.sql.Date;

public class Solicitacao {
	private int id;
	private Pessoa solicitante;
	private Date data;
	private Setor setor;
	private Produto produto;
	private TipoProblema tipoProblema;
	private String descricaoProblema;
	private Funcionario atendentes;
	private Date dataResolucao;
	private String modoResolucao;
	
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	public Pessoa getsolicitante() {
		return solicitante;
	}
	public void setsolicitante(Pessoa soli) {
		this.solicitante = soli;
	}
	public Date getdata() {
		return data;
	}
	public void setdata(Date data) {
		this.data = data;
	}
	public Setor getsetor() {
		return setor;
	}
	public void setsetor(Setor setor) {
		this.setor = setor;
	}
	public Produto getproduto() {
		return produto;
	}
	public void setproduto(Produto produto) {
		this.produto = produto;
	}
	public TipoProblema gettipoProblema() {
		return tipoProblema;
	}
	public void settipoProblema(TipoProblema tipoProblema) {
		this.tipoProblema = tipoProblema;
	}
	public String getdescricaoProblema() {
		return descricaoProblema;
	}
	public void setdescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}
	public Funcionario getatendentes() {
		return atendentes;
	}
	public void setatendentes(Funcionario atendentes) {
		this.atendentes = atendentes;
	}
	public Date getdataResolucao() {
		return dataResolucao;
	}
	public void setdataResolucao(Date dataResolucao) {
		this.dataResolucao = dataResolucao;
	}
	public String getmodoResolucao() {
		return modoResolucao;
	}
	public void setmodoResolucao(String modoResolucao) {
		this.modoResolucao = modoResolucao;
	}
	

}
