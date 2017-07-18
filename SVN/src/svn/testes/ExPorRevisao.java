package svn.testes;

import java.util.Collection;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;

public class ExPorRevisao {

	public static void main(String[] args) throws SVNException {
		
		RepositoryConnection repositorio = new RepositoryConnection("http://argouml.tigris.org/svn/argouml/trunk", "guest", "");
		
		Collection<?> revisoes = repositorio.buscarPorRevisao(1000, 1050);
		
		for (Object elem : revisoes) {
			SVNLogEntry rev = (SVNLogEntry) elem;
			
			System.out.println("Revisão: " + rev.getRevision());
			System.out.println("Autor: " + rev.getAuthor());
			System.out.println("Data: " + rev.getDate());
			System.out.println("------------------------------------");
		}
		
	}
	
}