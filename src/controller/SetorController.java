package controller;

import dao.SetorDAO;
import model.Setor;

public class SetorController {
	SetorDAO dao = new SetorDAO();
	
	public Boolean create(Setor setor){
		return dao.create(setor);
	}
	
	public Setor read(Setor setor){
		return dao.read(setor.getid());
	}
	
	public Setor read(int id){
		return dao.read(id);
	}
	
	public Boolean update(Setor setor){
		return dao.update(setor);
	}
	
	public Boolean delete(Setor setor){
		return dao.delete(setor);
	}
}
