package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class ProdutoDAO extends DAO{
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
	
	public Produto read(int id){
		Produto p = new Produto();
		String query = "SELECT id,nome from tbproduto Where id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, id);
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
					Produto p2 = obterNumeroSolicitacoes(p.getId());
					p.setEstado(p2.getEstado());
					p.setTotalSolicitacao(p2.getTotalSolicitacao());
					p.setTotalSolicitacaoSemana(p2.getTotalSolicitacaoSemana());
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
	
	private Produto obterNumeroSolicitacoes(int id) {
		int qtd = 0;
		String query = "SELECT COUNT(so.id) as total_solicitacoes "
				+ " FROM tbproduto AS prod "
				+ "	INNER JOIN tbsolicitacao AS so "
				+ "		ON so.produtoId = prod.id "
				+ " WHERE prod.id = ?"
				+ " GROUP BY prod.id";
		Conexao conn = new Conexao();
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, id);
			ResultSet res = pstm.executeQuery();
			while(res.next()) {
				qtd = res.getInt("total_solicitacoes");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close(cn);
		}
		Produto p = obterSolicitacoesSemanaEStatus(id);
		p.setTotalSolicitacao(qtd);
		return p;
	}
	
	private Produto obterSolicitacoesSemanaEStatus(int id) {
		Produto p = new Produto();
		int qtd = 0;
		String query = "SELECT \r\n"
				+ "	COUNT(so.id) as total_solicitacoes\r\n"
				+ "	, CASE WHEN COUNT(so.id)>30\r\n"
				+ "		THEN\r\n"
				+ "			'Alerta'\r\n"
				+ "		WHEN COUNT(so.id)>10\r\n"
				+ "			THEN\r\n"
				+ "			'Atenção'\r\n"
				+ "		ELSE\r\n"
				+ "			'OK'\r\n"
				+ "	  END AS estado\r\n"
				+ "FROM tbproduto AS prod\r\n"
				+ "	INNER JOIN tbsolicitacao AS so\r\n"
				+ "		ON so.produtoId = prod.id\r\n"
				+ "WHERE prod.id = ? AND so.dataSolicitacao > DATEADD(DAY,-7,GETDATE())\r\n"
				+ "GROUP BY prod.id";
		Conexao conn = new Conexao();
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, id);
			ResultSet res = pstm.executeQuery();
			while(res.next()) {
				p.setTotalSolicitacaoSemana(res.getInt("total_solicitacoes"));
				p.setEstado(res.getString("estado"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close(cn);
		}
		return p;
	}
}
