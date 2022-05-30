package model;

import java.util.ArrayList;

public class Pessoa {
	private String cpf;
	private String nome;
	private String senha;
	private boolean funcionario = false; 
	private ArrayList<String> enderecos = new ArrayList<String>();
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public ArrayList<String> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(ArrayList<String> enderecos) {
		this.enderecos = enderecos;
	}
	public boolean isFuncionario() {
		return funcionario;
	}
	public void setFuncionario(boolean funcionario) {
		this.funcionario = funcionario;
	}
	@Override
	public String toString() {
		return "{"+cpf+" | "+nome+"|"+funcionario+"}";
	}
}
