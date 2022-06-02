package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Funcionario;
import model.Pessoa;

public class FuncionarioDAO extends PessoaDAO{
	public Boolean create(Funcionario f){
		Pessoa p = f;
		create(p);
		String query = "INSERT INTO tbfuncionario(pessoaCpf,equipeId,nivel) VALUES(?,?,?)";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setString(1, f.getCpf());
			pstm.setInt(2, f.getEquipeId());
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
					f.setEquipeId(res.getInt("equipeId"));
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
		
	}
	
	public Boolean delete(Funcionario f){
		
	}
		
	public Pessoa search(Funcionario f){
		
	}

	public ArrayList<Funcionario> list(int pagina,int itensPagina){
		
	}
}
