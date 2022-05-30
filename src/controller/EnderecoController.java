package controller;

import java.util.ArrayList;

import dao.EnderecoDAO;
import model.Endereco;
import model.Pessoa;

public class EnderecoController {
	EnderecoDAO dao = new EnderecoDAO();
	public boolean create(Endereco ende){
		return dao.create(ende);
	}
	
	public Endereco read(int id){
		return dao.read(id);
	}
	
	public boolean update(Endereco[] endereco){
		return dao.update(endereco);
	}
	
	public ArrayList<Endereco> list(Pessoa p){
		return dao.list(p);
	}
}
