package controller;

import java.util.ArrayList;

import dao.PessoaDAO;
import model.Pessoa;

public class PessoaController {
	PessoaDAO dao = new PessoaDAO();
	public Boolean create(Pessoa p){
		return dao.create(p);
	}
	
	public Pessoa read(String cpf){
		return dao.read(cpf);
	}
	
	public Boolean update(Pessoa p){
		return dao.update(p);
	}
	
	public Boolean delete(Pessoa p){
		return dao.delete(p);
	}
		
	public Pessoa search(Pessoa p){
		return dao.search(p);
	}

	public ArrayList<Pessoa> list(int pagina,int itensPagina){
		return dao.list(pagina,itensPagina);
	}
	
	public Pessoa login(Pessoa p){
		return dao.login(p);
	}
}
