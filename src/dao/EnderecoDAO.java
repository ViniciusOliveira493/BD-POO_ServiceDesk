package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Endereco;
import model.Pessoa;

public class EnderecoDAO {
	public boolean create(Endereco ende){
		String query="INSERT INTO tbEndereco(pessoaCPF,cep,numero,logradouro,descricao) VALUES(?,?,?,?,?)";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, ende.getPessoaCPF());
			pstm.setString(2, ende.getCep());
			pstm.setInt(3, ende.getNumero());
			pstm.setString(4, ende.getLogradouro());
			pstm.setString(5, ende.getDescricao());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	
	public Endereco read(int id){
		Endereco ende = new Endereco();
		String query = "SELECT id,pessoaCPF,cep,numero,logradouro,descricao from tbendereco Where id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, id);
			ResultSet res = pstm.executeQuery();
			while(res.next()) {
				ende.setId(res.getInt(1));
				ende.setPessoaCPF(res.getString(2));
				ende.setCep(res.getString(3));
				ende.setNumero(res.getInt(4));
				ende.setLogradouro(res.getString(5));
				ende.setDescricao(res.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close(cn);
		}
		return ende;
	}
	
	public boolean update(List<Endereco> endereco){
		String query="DELETE FROM tbEndereco WHERE pessoaCPF = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, endereco.get(0).getPessoaCPF());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		
		for (Endereco e : endereco) {
			if(!create(e)) {
				r=false;
			}
		}
		return r;
	}
	
	public ArrayList<Endereco> list(Pessoa p){
		ArrayList<Endereco> lista = new ArrayList<Endereco>();
		String query = "SELECT id,pessoaCPF,cep,numero,logradouro,descricao from tbendereco Where pessoaCPF = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, p.getCpf());
			ResultSet res = pstm.executeQuery();
			while(res.next()) {
				Endereco ende = new Endereco();
				ende.setId(res.getInt(1));
				ende.setPessoaCPF(res.getString(2));
				ende.setCep(res.getString(3));
				ende.setNumero(res.getInt(4));
				ende.setLogradouro(res.getString(5));
				ende.setDescricao(res.getString(6));
				lista.add(ende);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close(cn);
		}
		return lista;
	}
}
