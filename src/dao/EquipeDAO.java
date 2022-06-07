package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Equipe;

public class EquipeDAO extends DAO{
	public Boolean create(Equipe equipe){
		String query = "INSERT INTO tbEquipe(nome,setorId) VALUES(?,?)";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, equipe.getid());
			pstm.setString(2, equipe.getNome());
			pstm.setInt(3, equipe.getsetor().getid());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	
	public Equipe read(int id){
		Equipe p = new Equipe();
		String query = "SELECT id,nome,setorId from tbEquipe Where id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, id);
			ResultSet res = pstm.executeQuery();
			while(res.next()) {
				p.setNome(res.getString("nome"));
				p.setid(res.getInt("id"));
				//p.setsetor(res.getInt("setorId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close(cn);
		}
		return p;
	}
	
	public Boolean update(Equipe equipe){
		String query="UPDATE tbSetor"
				+ " SET "
				+ "	nome = ?"
				+ " setorId= ?"
				+ " WHERE id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, equipe.getid());
			pstm.setString(2, equipe.getNome());
			pstm.setInt(3, equipe.getsetor().getid());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	
	public Boolean delete(Equipe equipe){
		String query="Delete from tbEquipe WHERE id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, equipe.getid());
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
