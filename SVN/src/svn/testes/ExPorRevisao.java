package svn.testes;

import java.util.Collection;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.io.SVNRepository;

public class ExPorRevisao {

	public static void main(String[] args) throws SVNException {
		
		SVNRepository repositorio = ConnectionUtil.conexao();
		
		long revisaoInicial = 1000;
		long revisaoFinal = 1050;
		
		Collection<?> revisoes = repositorio.log(new String[] { "" }, null, revisaoInicial, revisaoFinal, true, true);
		
		for (Object elem : revisoes) {
			SVNLogEntry rev = (SVNLogEntry) elem;
			
			System.out.println("---------------------------------------------");
			System.out.println("Número: " + rev.getRevision());
			System.out.println("Autor: " + rev.getAuthor());
			System.out.println("Data: " + rev.getDate());
		}
		
		repositorio.closeSession();
		
	}
	
}