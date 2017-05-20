package svn.testes;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.io.SVNRepository;

public class ExPorData {

	public static void main(String[] args) throws SVNException {
		
		SVNRepository repositorio = ConnectionUtil.conexao();
		
		Date dataInicial = DataUtil.converterStringParaDate("12/11/2001");
		Date dataFinal = DataUtil.converterStringParaDate("21/11/2001");
		
		long revisaoInicial = repositorio.getDatedRevision(dataInicial);
		long revisaoFinal = repositorio.getDatedRevision(dataFinal);
		
		Collection<?> revisoes = repositorio.log(new String[] { "" }, null, revisaoInicial, revisaoFinal, true, true);
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
			System.out.println(autor + " = " + contagem.get(autor));
		}
		
		repositorio.closeSession();
		
	}
	
}
