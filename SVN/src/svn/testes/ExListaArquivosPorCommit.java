package svn.testes;

import java.util.ArrayList;
import java.util.Collection;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;

public class ExListaArquivosPorCommit {

	public static void main(String[] args) throws SVNException {
		
		RepositoryConnection repositorio = new RepositoryConnection("http://argouml.tigris.org/svn/argouml/trunk", "guest", "");
		
//		Date dataInicial = DataUtil.converterStringParaDate("12/11/2001");
//		Date dataFinal = DataUtil.converterStringParaDate("12/01/2002");
//		
//		long revisaoInicial = repositorio.getDatedRevision(dataInicial);
//		long revisaoFinal = repositorio.getDatedRevision(dataFinal);
		
		Collection<?> revisao = repositorio.buscarPorRevisao(1000, 1050);
		
		ArrayList<String> modificados = new ArrayList<>();
		ArrayList<String> adicionados = new ArrayList<>();
		ArrayList<String> removidos = new ArrayList<>();
		ArrayList<String> substituidos = new ArrayList<>();
		int total = 0;
		
		for (Object objeto : revisao) {
			SVNLogEntry rev = (SVNLogEntry) objeto;
			Collection<SVNLogEntryPath> arquivos = rev.getChangedPaths().values();
			
			for (SVNLogEntryPath arq : arquivos) {
				
				char aux = arq.getType();
				
				System.out.println("Tipo de alteração: " + arq.getType() + "\nPath: " + arq.getPath());
				System.out.println("----------------------------------------------------------------------------------------------------------------");
				
				if (aux == 'M' && !modificados.contains(arq.getPath())) {
					modificados.add(arq.getPath());
					total++;
				} else if (aux == 'A' && !adicionados.contains(arq.getPath())) {
					adicionados.add(arq.getPath());
					total++;
				} else if (aux == 'D' && !removidos.contains(arq.getPath())) {
					removidos.add(arq.getPath());
					total++;
				} else if (aux == 'R' && !substituidos.contains(arq.getPath())) {
					substituidos.add(arq.getPath());
					total++; 
				}
				
			}
			
		}
		
		if (modificados.size() > 1) {
			System.out.println(modificados.size() + " arquivos foram modificados: " + modificados);
		} else {
			System.out.println(modificados.size() + " arquivo foi modificado: " + modificados);
		}
		
		if (adicionados.size() > 1) {
			System.out.println(adicionados.size() + " arquivos foram adicionados: " + adicionados);
		} else {
			System.out.println(adicionados.size() + " arquivo foi adicionado: " + adicionados);
		}
		
		if (removidos.size() > 1) {
			System.out.println(removidos.size() + " arquivos foram removidos: " + removidos);
		} else {
			System.out.println(removidos.size() + " arquivo foi removido: " + removidos);
		}
		
		if (substituidos.size() > 1) {
			System.out.println(substituidos.size() + " arquivos foram removidos: " + substituidos);
		} else {
			System.out.println(substituidos.size() + " arquivo foi removido: " + substituidos);
		}
		
		System.out.println("\nTotal: " + total + " arquivos");
		
	}

}
