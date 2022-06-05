package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Pessoa;

public class PessoaDAO {
	public Boolean create(Pessoa p){
		String query = "INSERT INTO tbPessoa(cpf,nome,senha) VALUES(?,?,?)";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, p.getCpf());
			pstm.setString(2, p.getNome());
			pstm.setString(3, p.getSenha());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	
	public Pessoa read(String cpf){
		Pessoa p = new Pessoa();
		if(cpf!=null || cpf!="") {
			String query="SELECT cpf,nome FROM tbPessoa WHERE cpf=?";
			Conexao conn = new Conexao();
			Connection cn = null;
			try {
				cn = conn.getConexao();			
				PreparedStatement pstm = cn.prepareStatement(query);
				pstm = cn.prepareStatement(query);
				pstm.setString(1, cpf);
				
				ResultSet res= pstm.executeQuery();
				while(res.next()) {
					p.setCpf(res.getString("cpf"));
					p.setNome(res.getString("nome"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				conn.close(cn);
			}	
		}
	    return p;
	}
	
	public Boolean updateName(Pessoa p){
		String query="UPDATE tbpessoa"
				+ " SET "
				+ "	nome=?"
				+ " WHERE cpf=?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, p.getNome());
			pstm.setString(2, p.getCpf());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	
	public Boolean updateSenha(Pessoa p){
		String query="UPDATE tbpessoa"
				+ " SET "
				+ "	senha = ?"
				+ " WHERE cpf = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, p.getSenha());
			pstm.setString(2, p.getCpf());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	
	public Boolean delete(Pessoa p){
		String query="Delete from tbpessoa WHERE cpf = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, p.getCpf());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
		
	public Pessoa search(Pessoa p){
		//caso os parametros estejam vazios ou nulos, a pesquisa não será realizada
		if(p.getCpf()!=null || p.getNome()!=null || p.getCpf()!="" || p.getNome()!="") {
			String query="SELECT cpf,nome FROM tbPessoa WHERE ";
			Conexao conn = new Conexao();
			Connection cn = null;
			try {
				cn = conn.getConexao();			
				PreparedStatement pstm = cn.prepareStatement(query);
						
				if(p.getCpf()!=null && p.getCpf()!="") {
					query+="cpf=?";
					pstm = cn.prepareStatement(query);
					pstm.setString(1, p.getCpf());
				}else if(p.getNome()==null && p.getNome()!="") {
					query+="nome LIKE ?";
					pstm = cn.prepareStatement(query);
					pstm.setString(1, p.getNome());
				}		
				
				ResultSet res= pstm.executeQuery();
				while(res.next()) {
					p.setCpf(res.getString("cpf"));
					p.setNome(res.getString("nome"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				conn.close(cn);
			}	
		}
	    return p;
	}

	public ArrayList<Pessoa> list(){
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		String query="SELECT cpf,nome FROM tbPessoa";
		Conexao conn = new Conexao();
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			ResultSet res= pstm.executeQuery();
			while(res.next()) {
				Pessoa p = new Pessoa();
				p.setCpf(res.getString("cpf"));
				p.setNome(res.getString("nome"));
				pessoas.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}	
	    return pessoas;
	}
	
	public Pessoa login(Pessoa p){
		String query="SELECT cpf,nome FROM tbPessoa WHERE cpf=? AND senha=?";
		Conexao conn = new Conexao();
		Connection cn = null;
		try {
			cn = conn.getConexao();			
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, p.getCpf());
			pstm.setString(2, p.getSenha());
			p = new Pessoa();
			
			ResultSet res = pstm.executeQuery();			
			while(res.next()) {
				p.setCpf(res.getString("cpf"));
				p.setNome(res.getString("nome"));
				
				query="SELECT count(pessoaCpf) as qtd FROM tbFuncionario WHERE pessoaCpf=?";
				pstm = cn.prepareStatement(query);
				pstm.setString(1, p.getCpf());
				ResultSet res2 = pstm.executeQuery();
				while(res2.next()) {
					if(res2.getInt("qtd")>0) {
						p.setFuncionario(true);
					}
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}	
		return p;
	}
}
