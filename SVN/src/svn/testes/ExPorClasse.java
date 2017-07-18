package svn.testes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;

public class ExPorClasse {
	
	public static void main(String[] args) throws SVNException {
		
		RepositoryConnection repositorio = new RepositoryConnection("http://argouml.tigris.org/svn/argouml/trunk", "guest", "");
		
//		Date dataInicial = DataUtil.converterStringParaDate("12/11/2001");
//		Date dataFinal = DataUtil.converterStringParaDate("12/01/2002");
//		
//		long revisaoInicial = repositorio.getDatedRevision(dataInicial);
//		long revisaoFinal = repositorio.getDatedRevision(dataFinal);
		
		Collection<?> revisao = repositorio.buscarPorData("12/11/2001", "12/01/2002");
		Map<String, Integer> contador = new HashMap<String, Integer>();
		
		for (Object objeto : revisao) {
			SVNLogEntry rev = (SVNLogEntry) objeto;
			Collection<SVNLogEntryPath> arquivos = rev.getChangedPaths().values();
			
			for (SVNLogEntryPath arq : arquivos) {
				String path = arq.getPath();
				Integer iPath = contador.get(path);
				
				System.out.println(arq.getType());
				
				if (iPath == null) {
					contador.put(path, 1);
				} else {
					contador.put(path, iPath + 1);
				}
			}
		}
		
		for (String path : contador.keySet()) {
			if (path.endsWith(".java"))
				System.out.println(path + " = " + contador.get(path));
		}
		
		int maiorValor = 0;
		String classeMaisAlterada = "";
		
		for (String classe : contador.keySet()) {
			Integer i = contador.get(classe);
			
			if (i > maiorValor && classe.endsWith(".java")) {
				maiorValor = i;
				classeMaisAlterada = classe;
			}
			
		}
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Classe mais alterada: \n\t" + classeMaisAlterada + "\nQuantidade de modificações: " + maiorValor);
		System.out.println("---------------------------------------------------------------------------------------");
		
	}
	
}
