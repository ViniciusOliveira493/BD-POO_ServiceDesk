package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Funcionario;
import model.Solicitacao;

public class SolicitacaoDAO extends DAO{
	private ProdutoDAO pdao = new ProdutoDAO();
	public Boolean create(Solicitacao s){
		String query = "INSERT INTO tbsolicitacao(descricaoProblema,dataSolicitacao,"
				+ "produtoId,tipoProblema,setorId,"
				+ "solicitanteCpf) VALUES(?,?,?,?,?,?)";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, s.getdescricaoProblema());
			pstm.setDate(2, getNow());
			pstm.setInt(3, s.getproduto().getId());
			pstm.setInt(4, s.gettipoProblema().getid());
			pstm.setInt(5, s.getsetor().getid());
			pstm.setString(6, s.getsolicitante().getCpf());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	
	public Solicitacao read(Solicitacao s){
		String query = "SELECT * from tbproduto Where id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, s.getid());
			ResultSet res = pstm.executeQuery();
			while(res.next()) {			
				s.setsolicitante(null);				
				s.setmodoResolucao(query);
				s.setid(0);
				s.setdescricaoProblema(query);
				s.setdataResolucao(null);
				s.setdata(null);
				
				s.setproduto(pdao.read(res.getInt("produtoId")));
				s.setatendentes(null);//funcionarios
				s.settipoProblema(null);
				s.setsetor(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close(cn);
		}
		return s;
		
	}
	
	public Boolean update(Solicitacao s,Funcionario func){
		cadastrarAtendente(s, func);
		String query="UPDATE tbsolicitacao"
				+ " SET "
				+ "	descricaoProblema = ?"
				+ "	produtoId = ?"
				+ "	tipoProblema = ?"
				+ "	setorId = ?"
				+ " WHERE id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, s.getdescricaoProblema());
			pstm.setInt(2, s.getproduto().getId());
			pstm.setInt(3, s.gettipoProblema().getid());
			pstm.setInt(4, s.getsetor().getid());
			pstm.setInt(5, s.getid());
			
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	
	public Boolean encerrar(Solicitacao s,Funcionario func){
		cadastrarAtendente(s, func);
		String query="UPDATE tbsolicitacao"
				+ " SET "
				+ "	dataResolucao = ?"
				+ "	descricaoResulucao = ?"
				+ " WHERE id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setDate(1, getNow());
			pstm.setString(2, s.getmodoResolucao());
			pstm.setInt(3, s.getid());
			
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
		
	public List<Solicitacao> listar(Funcionario func){
		List<Solicitacao> lista = new ArrayList<Solicitacao>();
		
		String query = "SELECT * from tbproduto Where setorId = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, func.getEquipe().getsetor().getid());
			ResultSet res = pstm.executeQuery();
			while(res.next()) {		
				Solicitacao s = new Solicitacao();
				s.setsolicitante(null);				
				s.setmodoResolucao(query);
				s.setid(0);
				s.setdescricaoProblema(query);
				s.setdataResolucao(null);
				s.setdata(null);
				
				s.setproduto(pdao.read(res.getInt("produtoId")));
				s.setatendentes(null);//funcionarios
				s.settipoProblema(null);
				s.setsetor(null);
				lista.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close(cn);
		}
		return lista;		
	}
	
	private Boolean cadastrarAtendente(Solicitacao s,Funcionario func) {
		String query = "INSERT INTO tbatendente(solicitacaoId,pessoaCpf,dateEHora) "
				+ "VALUES(?,?,?)";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, s.getid());
			pstm.setString(2, func.getCpf());
			pstm.setDate(3, getNow());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
}
