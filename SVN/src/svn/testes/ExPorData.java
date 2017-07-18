package svn.testes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;

public class ExPorData {

	public static void main(String[] args) throws SVNException {
		
		RepositoryConnection repositorio = new RepositoryConnection("http://argouml.tigris.org/svn/argouml/trunk", "guest", "");
		
//		Date dataInicial = DataUtil.converterStringParaDate("12/11/2001");
//		Date dataFinal = DataUtil.converterStringParaDate("21/11/2001");
//		
//		long revisaoInicial = repositorio.getDatedRevision(dataInicial);
//		long revisaoFinal = repositorio.getDatedRevision(dataFinal);
		
		Collection<?> revisoes = repositorio.buscarPorData("12/11/2001", "21/11/2001");
		Map<String, Integer> contagem = new HashMap<String, Integer>();
		
		for (Object elem : revisoes) {
			SVNLogEntry rev = (SVNLogEntry) elem;
			String autor = rev.getAuthor();
			
			Integer i = contagem.get(autor);
			
			if (i == null) {
				contagem.put(autor, 1);
			} else {
				contagem.put(autor, i + 1);
			}
			
		}
		
		for (String autor : contagem.keySet()) {
			System.out.println("Autor: " + autor + " | Commits: " + contagem.get(autor));
		}
		
	}
	
}
