package controller;

import java.util.List;

import dao.SolicitacaoDAO;
import model.Funcionario;
import model.Solicitacao;

public class SolicitacaoController {
	SolicitacaoDAO dao = new SolicitacaoDAO();
	public Boolean create(Solicitacao s){
		return dao.create(s);
	}
	
	public Solicitacao read(Solicitacao s){
		return dao.read(s);
	}
	
	public Boolean update(Solicitacao s,Funcionario func){
		return dao.update(s,func);
	}
	
	public Boolean encerrar(Solicitacao s,Funcionario func){
		return dao.encerrar(s,func);
	}
		
	public List<Solicitacao> listar(Solicitacao s,Funcionario func){
		return dao.listar(func);
	}
}
