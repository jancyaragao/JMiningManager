package git.testes;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.filter.CommitTimeRevFilter;
import org.eclipse.jgit.revwalk.filter.RevFilter;

public class RepositoryConnection {

	private Git git;
	private Repository repositorio;

	public RepositoryConnection(String path) {
		try {
			git = Git.open(new File(path));
			repositorio = git.getRepository();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Iterable<RevCommit> buscarPorData(String data_inicial, String data_final)
			throws NoHeadException, GitAPIException {
		Date data1 = DataUtil.converterStringParaDate(data_inicial);
		Date data2 = DataUtil.converterStringParaDate(data_final);

		RevFilter filtro = CommitTimeRevFilter.between(data1, data2);

		Iterable<RevCommit> revisoes = git.log().setRevFilter(filtro).call();

		return revisoes;
	}

	public Iterable<RevCommit> buscarPorRevisao(String r1, String r2) throws RevisionSyntaxException, AmbiguousObjectException,
			IncorrectObjectTypeException, IOException, NoHeadException, GitAPIException {
		ObjectId ref_inicial = repositorio.resolve(r1);
		ObjectId ref_final = repositorio.resolve(r2);

		Iterable<RevCommit> revisoes = git.log().addRange(ref_inicial, ref_final).call();

		return revisoes;
	}

	public void close() {
		git.close();
	}
	
	public Repository getRepository() {
		return repositorio;
	}

}
