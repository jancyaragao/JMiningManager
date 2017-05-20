package git.testes;

import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;

public class ExPorRevisao {
	
	public static void main(String[] args) throws IOException, NoHeadException, GitAPIException {

		RepositoryConnection c = new RepositoryConnection("C:/Users/jancy/git/wicket");
		
		Iterable<RevCommit> revisoes = c.buscarPorRevisao
				("81684f93dde752bed5208353a035a524430d08be", "1421ea2dc9207143cdadb735f3c79421674d924d");
		
		for (RevCommit rev : revisoes) {
			PersonIdent autoria = rev.getAuthorIdent();
			
			System.out.println("----------------------------------------------------");
			System.out.println("Hash: " + rev.getName());
			System.out.println("Autor: " + autoria.getName());
			System.out.println("E-mail: " + autoria.getEmailAddress());
			System.out.println("Data: " + DataUtil.converterDateParaString(autoria.getWhen()));
		}
		
	}
	
}