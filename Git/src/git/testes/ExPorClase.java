package git.testes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;

public class ExPorClase {

	public static void main(String[] args) throws NoHeadException, IOException, GitAPIException {
		
		Iterable<RevCommit> revisoes = ConnectionUtil.conexaoPorData();
		
		Map<String, Integer> contador = new HashMap<String, Integer>();
		
		for (Object objeto : revisoes) {
			RevCommit rev = (RevCommit) objeto;
			
			
		}
		
	}
	
}
