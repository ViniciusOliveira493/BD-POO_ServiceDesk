package ServiceDesk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class TipoProblemaDAO extends DAO{
	public Boolean create(TipoProblema p){
		String query = "INSERT INTO tbtipoproblema(nome) VALUES(?)";
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
	public TipoProblema read(TipoProblema p){
		String query = "SELECT id,nome from tbtipoproblema Where id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, p.getid());
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
	public TipoProblema read(int id){
		TipoProblema p = new TipoProblema();
		String query = "SELECT id,nome from tbtipoproblema Where id = ?";
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
	public Boolean update(TipoProblema p){
		String query="UPDATE tbtipoproblema"
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
			pstm.setInt(2, p.getid());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	public Boolean delete(TipoProblema p){
		String query="Delete from tbtipoproblema WHERE id = ?";
		Conexao conn = new Conexao();
		Connection cn = null;
		boolean r = false;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, p.getid());
			pstm.execute();
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close(cn);
		}
		return r;
	}
	public List<TipoProblema> search(TipoProblema p){
		List<TipoProblema> lista = new ArrayList<TipoProblema>();
		if(p.getNome()!=null) {
			String query="SELECT id,nome FROM tbtipoproblema WHERE nome LIKE ?";
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
					p = new TipoProblema();
					p.setid(res.getInt("id"));
					p.setNome(res.getString("nome"));
					p.setResolucao(res.getInt("tempoResolucao")); 
					
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
