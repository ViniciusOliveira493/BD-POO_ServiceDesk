package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Setor;

public class SetorDAO extends DAO{
	
	public Boolean create(Setor setor){
		String query = "INSERT INTO tbSetor(nome) VALUES(?)";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, setor.getNome());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	
	public Setor read(int id){
		Setor p = new Setor();
		String query = "SELECT id,nome from tbSetor Where id = ?";
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close(cn);
		}
		return p;
	}
	
	public Boolean update(Setor setor){
		String query="UPDATE tbSetor"
				+ " SET "
				+ "	nome = ?"
				+ " WHERE id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, setor.getNome());
			pstm.setInt(2, setor.getid());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	
	public Boolean delete(Setor setor){
		String query="Delete from tbSetor WHERE id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, setor.getid());
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
