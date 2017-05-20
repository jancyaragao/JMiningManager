package svn.testes;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.io.SVNRepository;

public class ExPorAutores {

	public static void main(String[] args) throws SVNException {
		
		SVNRepository repositorio = ConnectionUtil.conexao();
		
		Date dataInicial = DataUtil.converterStringParaDate("12/11/2001");
		Date dataFinal = DataUtil.converterStringParaDate("21/11/2001");
		
		long revisaoInicial = repositorio.getDatedRevision(dataInicial);
		long revisaoFinal = repositorio.getDatedRevision(dataFinal);
		
		Collection<?> revisao = repositorio.log(new String[] { "" }, null, revisaoInicial, revisaoFinal, true, true);
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
		
		System.out.println("----------------------------------------");
		System.out.println("Autor mais produtivo: " + autorMaisProdutivo + "\nQuantidade de modificações: " + maiorValor);
		System.out.println("----------------------------------------");
		
		repositorio.closeSession();
		
	}
	
}