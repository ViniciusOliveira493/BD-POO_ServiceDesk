package controller;

import dao.EquipeDAO;
import model.Equipe;

public class EquipeController {
	EquipeDAO dao = new EquipeDAO();
	
	public Boolean create(Equipe equipe){
		return dao.create(equipe);
	}
	
	public Equipe read(Equipe equipe){
		return dao.read(equipe.getid());
	}
	
	public Equipe read(int id){
		return dao.read(id);
	}
	
	public Boolean update(Equipe equipe){
		return dao.update(equipe);
	}
	
	public Boolean delete(Equipe equipe){
		return dao.delete(equipe);
	}
}
