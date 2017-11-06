package git.testes;

import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;

public class ExPorData {
	
	public static void main(String[] args) throws IOException, NoHeadException, GitAPIException {
		
		RepositoryConnection c = new RepositoryConnection("C:/Users/felipe/git/wicket");
		
		Iterable<RevCommit> revisoes = c.buscarPorData("01/03/2017", "20/03/2017");
		
		for (RevCommit rev : revisoes) {
			PersonIdent autoria = rev.getAuthorIdent();
			
			System.out.println("Hash: " + rev.getName());
			System.out.println("Autor: " + autoria.getName());
			System.out.println("E-mail: " + autoria.getEmailAddress());
			System.out.println("Data: " + DataUtil.converterDateParaString(autoria.getWhen()));
			System.out.println("--------------------------------------------------");
		}
		
		c.close();
		
	}
	
}