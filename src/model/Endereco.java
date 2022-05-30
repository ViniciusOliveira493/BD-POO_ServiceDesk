package model;

public class Endereco {
	private int id;
	private String pessoaCPF;
	private String cep;
	private int numero;
	private String logradouro;
	private String descricao;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPessoaCPF() {
		return pessoaCPF;
	}
	public void setPessoaCPF(String pessoaCPF) {
		this.pessoaCPF = pessoaCPF;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return pessoaCPF + " | " + logradouro + " | " + numero;
	}
}
