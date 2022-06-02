package model;

import java.sql.Date;

public class Mensagem {
	private int id;
	private int chatid;
	private int pessoaCpf;
	private String texto;
	private Date dataeHora;
	
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	public int getchatid() {
		return chatid;
	}
	public void setchatid(int chatid) {
		this.chatid = chatid;
	}
	public int getpessoaCpf() {
		return pessoaCpf;
	}
	public void setpessoaCpf(int pessoaCpf) {
		this.pessoaCpf = pessoaCpf;
	}
	public String gettexto() {
		return texto;
	}
	public void settexto(String texto) {
		this.texto = texto;
	}
	public Date getdataHora() {
		return dataeHora;
	}
	public void setdataHora(Date dataeHora) {
		this.dataeHora = dataeHora;
	}

}
