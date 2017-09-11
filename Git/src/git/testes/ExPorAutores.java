package git.testes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;

public class ExPorAutores {
	
	public static void main(String[] args) throws IOException, NoHeadException, GitAPIException {
		
		RepositoryConnection c = new RepositoryConnection("C:/Users/jancy/git/wicket");
		
		Iterable<RevCommit> revisoes = c.buscarPorData("01/03/2017", "20/03/2017");

		Map<String, Integer> contagem = new HashMap<String, Integer>();
		
		for (RevCommit rev : revisoes) {
			String autor = rev.getAuthorIdent().getName();
			
			Integer i = contagem.get(autor);
			
			if (i == null) {
				contagem.put(autor, 1);
			} else {
				contagem.put(autor, i + 1);
			}
			
		}
		
		Integer numero_modificacoes = 0;
		String autor_mais_modificacoes = null;
		
		for (String autor : contagem.keySet()) {
			Integer i = contagem.get(autor);
			
			if (i > numero_modificacoes) {
				numero_modificacoes = i;
				autor_mais_modificacoes = autor;
			}
		}
		
		System.out.println("Autor mais produtivo no período é: " + autor_mais_modificacoes + 
				" com " + numero_modificacoes + " revisões.");
		
		
		
	}
	
}