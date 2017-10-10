package git.testes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.CommitTimeRevFilter;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.eclipse.jgit.util.io.DisabledOutputStream;

public class JMMGit {

	private Git git;
	private Repository repositorio;

	public JMMGit(String path) {
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

	public Iterable<RevCommit> buscarPorRevisao(String r1, String r2) throws RevisionSyntaxException,
			AmbiguousObjectException, IncorrectObjectTypeException, IOException, NoHeadException, GitAPIException {
		ObjectId ref_inicial = repositorio.resolve(r1);
		ObjectId ref_final = repositorio.resolve(r2);

		Iterable<RevCommit> revisoes = git.log().addRange(ref_inicial, ref_final).call();

		return revisoes;
	}

	public List<String> listarArquivos(String id, ChangeType type)
			throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException {
		return listarArquivos(id, new ChangeType[] { type });
	}

	public List<String> listarArquivos(String id, ChangeType[] types)
			throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException {
		ObjectId objectId = repositorio.resolve(id);

		RevWalk rw = new RevWalk(repositorio);
		RevCommit commit = rw.parseCommit(objectId);
		RevCommit parent = rw.parseCommit(commit.getParent(0).getId());

		DiffFormatter df = new DiffFormatter(DisabledOutputStream.INSTANCE);
		df.setRepository(repositorio);
		df.setDiffComparator(RawTextComparator.DEFAULT);
		df.setDetectRenames(true);

		List<DiffEntry> diffs = df.scan(parent.getTree(), commit.getTree());
		ArrayList<String> adicionados = new ArrayList<>();
		ArrayList<String> removidos = new ArrayList<>();
		ArrayList<String> modificados = new ArrayList<>();
		ArrayList<String> renomeados = new ArrayList<>();
		ArrayList<String> copiados = new ArrayList<>();

		for (DiffEntry diff : diffs) {
			if (diff.getChangeType().name().equals("MODIFY") && !modificados.contains(diff.getNewPath())) {
				modificados.add(diff.getNewPath());
			} else if (diff.getChangeType().name().equals("ADD") && !adicionados.contains(diff.getNewPath())) {
				adicionados.add(diff.getNewPath());
			} else if (diff.getChangeType().name().equals("DELETE") && !removidos.contains(diff.getOldPath())) {
				removidos.add(diff.getOldPath());
			} else if (diff.getChangeType().name().equals("RENAME") && !renomeados.contains(diff.getNewPath())) {
				renomeados.add(diff.getNewPath());
			} else if (diff.getChangeType().name().equals("COPY") && !copiados.contains(diff.getNewPath())) {
				copiados.add(diff.getNewPath());
			}
		}

		df.close();
		rw.close();

		List<String> resultado = new ArrayList<>();
		List<ChangeType> tipos = Arrays.asList(types);

		if (tipos.contains(ChangeType.ADDED)) {
			resultado.addAll(adicionados);
		}

		if (tipos.contains(ChangeType.DELETED)) {
			resultado.addAll(removidos);
		}

		if (tipos.contains(ChangeType.MODIFIED)) {
			resultado.addAll(modificados);
			resultado.addAll(copiados);
			resultado.addAll(renomeados);
		}

		return resultado;
	}

	public void close() {
		git.close();
	}

}
