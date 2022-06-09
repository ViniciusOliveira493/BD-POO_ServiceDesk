package ServiceDesk;
import java.util.List;

 	
public class TipoProblemaController {
	TipoProblemaDAO dao = new TipoProblemaDAO();
	public Boolean create(TipoProblema p){
		return dao.create(p);
	}
	
	public Produto read(TipoProblema p){
		return dao.read(p);
	}
	
	public Boolean update(TipoProblema p){
		return dao.update(p);
	}
	
	public Boolean delete(TipoProblema p){
		return dao.delete(p);
	}
		
	public List<TipoProblema> search(String nome){
		TipoProblema p = new TipoProblema();
		p.setNome(nome);
		return dao.search(p);
	}

}
