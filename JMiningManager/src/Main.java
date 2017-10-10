import java.util.List;

import git.testes.ChangeType;
import git.testes.JMMGit;

public class Main {

	public static void main(String[] args) throws Exception {

		JMMGit git = new JMMGit("C:/Users/felipe/git/wicket");

		ChangeType[] types = {ChangeType.ADDED, ChangeType.DELETED};
		
		List<String> paths = git.listarArquivos("7e032d211feecf00b93f72fd0ee49c42abf08c61", types);

		System.out.println("Total: " + paths.size());
		for (String p : paths)
			System.out.println(p);

	}

}
