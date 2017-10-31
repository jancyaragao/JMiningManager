package jmm.gui;
import java.util.List;

import jmm.common.ChangeType;
import jmm.git.JMMGit;

public class Main {

	public static void main(String[] args) throws Exception {

		JMMGit git = new JMMGit("C:/Users/jancy/git/wicket");

		ChangeType[] types = {ChangeType.ADDED, ChangeType.DELETED};
		
		List<String> paths = git.listFiles("7e032d211feecf00b93f72fd0ee49c42abf08c61", types);

		System.out.println("Total: " + paths.size());
		for (String p : paths)
			System.out.println(p);

	}

}
