package svn.testes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;

public class ExPorAutores {

	public static void main(String[] args) throws SVNException {
		
		RepositoryConnection repositorio = new RepositoryConnection("http://argouml.tigris.org/svn/argouml/trunk", "guest", "");
		
//		Date dataInicial = DataUtil.converterStringParaDate("12/11/2001");
//		Date dataFinal = DataUtil.converterStringParaDate("21/11/2001");
//		
//		long revisaoInicial = repositorio.getDatedRevision(dataInicial);
//		long revisaoFinal = repositorio.getDatedRevision(dataFinal);
		
		Collection<?> revisao = repositorio.buscarPorData("12/11/2001", "21/11/2001");
		Map<String, Integer> contador = new HashMap<String, Integer>();
		
		for (Object objeto : revisao) {
			SVNLogEntry rev = (SVNLogEntry) objeto;
			String autor = rev.getAuthor();
			
			Integer iAutor = contador.get(autor);
			
			if (iAutor == null) {
				contador.put(autor, 1);
			} else {
				contador.put(autor, iAutor + 1);
			}
			
		}
		
		int maiorValor = 0;
		String autorMaisProdutivo = "";
		
		for (String autor : contador.keySet()) {
			Integer i = contador.get(autor);
			
			if (i > maiorValor) {
				maiorValor = i;
				autorMaisProdutivo = autor;
			}
			
		}
		
		System.out.println("Autor mais produtivo: " + autorMaisProdutivo + "\nQuantidade de modificações: " + maiorValor);
		
	}
	
}