package controller;

import java.util.List;

import dao.ProdutoDAO;
import model.Produto;

public class ProdutoController {
	ProdutoDAO dao = new ProdutoDAO();
	
	public Boolean create(Produto p){
		return dao.create(p);
	}
	
	public Produto read(Produto p){
		return dao.read(p);
	}
	
	public Boolean update(Produto p){
		return dao.update(p);
	}
	
	public Boolean delete(Produto p){
		return dao.delete(p);
	}
		
	public List<Produto> search(String nome){
		Produto p = new Produto();
		p.setNome(nome);
		return dao.search(p);
	}
}
