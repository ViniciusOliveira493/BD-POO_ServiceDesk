package model;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {
	private String cpf;
	private String nome;
	private String senha;
	private String telefone;
	
	private boolean funcionario = false; 
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
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
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
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
		return "{"+cpf+" | "+nome+" | "+telefone+"}";
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
