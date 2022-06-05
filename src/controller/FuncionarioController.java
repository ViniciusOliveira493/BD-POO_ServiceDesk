package controller;

import java.util.List;

import dao.FuncionarioDAO;
import model.Funcionario;

public class FuncionarioController {
	private FuncionarioDAO dao = new FuncionarioDAO();
	public Boolean create(Funcionario f){
		return dao.create(f);
	}
	
	public Funcionario read(Funcionario f){
		return dao.read(f);
	}
	
	public Boolean update(Funcionario f){
		return dao.update(f);
	}
	
	public Boolean delete(Funcionario f){
		return dao.delete(f);
	}
		
	public Funcionario search(Funcionario f){
		return dao.search(f);
	}

	public List<Funcionario> listar(){
		return dao.listar();
	}
}
