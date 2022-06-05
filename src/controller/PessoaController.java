package controller;

import java.util.ArrayList;

import dao.PessoaDAO;
import model.Endereco;
import model.Pessoa;

public class PessoaController {
	PessoaDAO dao = new PessoaDAO();
	EnderecoController ctrlEnd = new EnderecoController();
	public Boolean create(Pessoa p){
		return dao.create(p);
	}
	
	public Pessoa read(String cpf){
		Pessoa p = dao.read(cpf);
		p.setEnderecos(ctrlEnd.list(p));		
		return p;
	}
	
	public Boolean update(Pessoa p){
		boolean b = ctrlEnd.update(p.getEnderecos());
		if(b) {
			b = dao.updateName(p);
			System.out.println("update pessoa" + p.getCpf());
		}
		return b;
	}
	
	public Boolean updateSenha(Pessoa p) {
		return dao.updateSenha(p);
	}
	
	public Boolean delete(Pessoa p){
		return dao.delete(p);
	}
		
	public Pessoa search(Pessoa p){
		return dao.search(p);
	}

	public ArrayList<Pessoa> list(){
		return dao.list();
	}
	
	public Pessoa login(Pessoa p){
		return dao.login(p);
	}
}
