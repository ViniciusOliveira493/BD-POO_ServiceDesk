package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class ProdutoDAO {
	public Boolean create(Produto p){
		String query = "INSERT INTO tbproduto(nome) VALUES(?)";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, p.getNome());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	
	public Produto read(Produto p){
		String query = "SELECT id,nome from tbproduto Where id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, p.getId());
			ResultSet res = pstm.executeQuery();
			while(res.next()) {
				p.setNome(res.getString("nome"));
				p.setId(res.getInt("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close(cn);
		}
		return p;
	}
	
	public Boolean update(Produto p){
		String query="UPDATE tbproduto"
				+ " SET "
				+ "	nome = ?"
				+ " WHERE id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, p.getNome());
			pstm.setInt(2, p.getId());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	
	public Boolean delete(Produto p){
		String query="Delete from tbproduto WHERE id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, p.getId());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
		
	public List<Produto> search(Produto p){
		List<Produto> lista = new ArrayList<Produto>();
		if(p.getNome()!=null) {
			String query="SELECT id,nome FROM tbproduto WHERE nome LIKE ?";
			Conexao conn = new Conexao();
			Connection cn = null;
			try {
				cn = conn.getConexao();			
				PreparedStatement pstm = cn.prepareStatement(query);
				pstm = cn.prepareStatement(query);
				String nome = p.getNome()+"%";
				pstm.setString(1, nome);
					
				
				ResultSet res= pstm.executeQuery();
				while(res.next()) {
					p = new Produto();
					p.setId(res.getInt("id"));
					p.setNome(res.getString("nome"));
					lista.add(p);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				conn.close(cn);
			}				
		}
	    return lista;
	}
}
