package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Funcionario;
import model.Pessoa;

public class FuncionarioDAO extends PessoaDAO{
	public Boolean create(Funcionario f){
		Pessoa p = f;
		String query = "INSERT INTO tbfuncionario(pessoaCpf,equipeId,nivel) VALUES(?,?,?)";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, f.getCpf());
			pstm.setInt(2, f.getEquipe().getid());
			pstm.setInt(3, f.getNivel());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	
	public Funcionario read(Funcionario f){
		Pessoa p= f;
		p = read(p.getCpf());
		f.setCpf(p.getCpf());
		f.setNome(p.getNome());
		f.setEnderecos(p.getEnderecos());
		f.setFuncionario(p.isFuncionario());
		if(f.getCpf()!=null || f.getCpf()!="") {
			String query="SELECT equipeId,nivel FROM tbfuncionario WHERE cpf=?";
			Conexao conn = new Conexao();
			Connection cn = null;
			try {
				cn = conn.getConexao();			
				PreparedStatement pstm = cn.prepareStatement(query);
				pstm = cn.prepareStatement(query);
				pstm.setString(1, f.getCpf());
				
				ResultSet res= pstm.executeQuery();
				while(res.next()) {
					f.setNivel(res.getInt("nivel"));
					//f.setEquipe(res.getInt("equipeId"));//precisa do crud equipe
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				conn.close(cn);
			}	
		}
	    return f;
	}
	
	public Boolean update(Funcionario f){
		Pessoa p = f;
		String query="UPDATE tbfuncionario"
				+ " SET "
				+ "	equipeId=?"
				+ " ,nivel=?"
				+ " WHERE pessoaCpf=?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, f.getEquipe().getid());
			pstm.setInt(2, f.getNivel());
			pstm.setString(3, f.getCpf());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	
	public Boolean delete(Funcionario f){
		String query="Delete from tbfuncionario WHERE pessoacpf = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, f.getCpf());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}	
		return r;
	}
		
	public Funcionario search(Funcionario f){
		//caso os parametros estejam vazios ou nulos, a pesquisa não será realizada
		if(f.getCpf()!=null || f.getNome()!=null || f.getCpf()!="" || f.getNome()!="") {
			String query="SELECT equipeId , nivel FROM tbfuncionario WHERE pessoaCpf=?";
			Conexao conn = new Conexao();
			Connection cn = null;
			try {
				cn = conn.getConexao();			
				PreparedStatement pstm = cn.prepareStatement(query);
				pstm = cn.prepareStatement(query);
				pstm.setString(1, f.getCpf());
					
				
				ResultSet res= pstm.executeQuery();
				while(res.next()) {
					//f.setEquipeId(res.getInt("equipeId"));//precisa do crud equipe
					f.setNivel(res.getInt("nivel"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				conn.close(cn);
			}	
			Pessoa p = f;
			p = search(p);
			
			f.setCpf(p.getCpf());
			f.setEnderecos(p.getEnderecos());
			f.setNome(p.getNome());					
		}
	    return f;
	}

	public List<Funcionario> listar(String nome){
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		String query="SELECT	\r\n"
				+ "	p.cpf as cpf \r\n"
				+ "	, f.equipeId as equipe\r\n"
				+ "	, CASE WHEN f.nivel = 0 THEN		\r\n"
				+ "			'ADMIN'\r\n"
				+ "		WHEN f.nivel = 1 THEN\r\n"
				+ "			'Técnico'\r\n"
				+ "		WHEN f.nivel = 2 THEN\r\n"
				+ "			'Atendente'\r\n"
				+ "		END AS cargo\r\n"
				+ "FROM tbFuncionario AS f\r\n"
				+ "	INNER JOIN tbPessoa AS p\r\n"
				+ "		ON p.cpf = f.pessoaCpf "
				+ "	WHERE p.nome LIKE ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			nome = nome+"%";
			pstm.setString(1, nome);
			ResultSet res= pstm.executeQuery();
			while(res.next()) {
				Funcionario f = new Funcionario();
				f.setCpf(res.getString("cpf"));
				f.setCargo(res.getString("cargo"));
				f = search(f);
				funcionarios.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}	
	    return funcionarios;
	}
}
