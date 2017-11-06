package jmm.git;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import jmm.common.DataUtil;
import jmm.model.FileChangeType;

/*
 * TODO: Dar mais opções de parâmetros nas buscas por data.
 */

public class JMMGit {

	private Git git;
	private Repository idRepository;

	public JMMGit(String path) {
		try {
			git = Git.open(new File(path));
			idRepository = git.getRepository();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Iterable<RevCommit> searchByDate(String initial_date, String final_date)
			throws NoHeadException, GitAPIException {
		Date date1 = DataUtil.converterStringParaDate(initial_date);
		Date date2 = DataUtil.converterStringParaDate(final_date);

		return searchByDate(date1, date2);
	}
	
	public Iterable<RevCommit> searchByDate(Date initial_date, Date final_date)
			throws NoHeadException, GitAPIException {
		RevFilter filtro = CommitTimeRevFilter.between(initial_date, final_date);

		Iterable<RevCommit> revisions = git.log().setRevFilter(filtro).call();

		return revisions;
	}

	public Iterable<RevCommit> searchByRevision(String r1, String r2) throws RevisionSyntaxException,
			AmbiguousObjectException, IncorrectObjectTypeException, IOException, NoHeadException, GitAPIException {
		ObjectId initial_rev = idRepository.resolve(r1);
		ObjectId final_rev = idRepository.resolve(r2);

		Iterable<RevCommit> revisions = git.log().addRange(initial_rev, final_rev).call();

		return revisions;
	}

	public List<String> listFiles(String id, FileChangeType type)
			throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException {
		return listFiles(id, new FileChangeType[] { type });
	}

	// TODO: Adaptar para usar apenas uma lista (economizar memória).
	public List<String> listFiles(String id, FileChangeType[] types)
			throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException {
		ObjectId objectId = idRepository.resolve(id);

		RevWalk rw = new RevWalk(idRepository);
		RevCommit commit = rw.parseCommit(objectId);
		RevCommit parent = rw.parseCommit(commit.getParent(0).getId());

		DiffFormatter df = new DiffFormatter(DisabledOutputStream.INSTANCE);
		df.setRepository(idRepository);
		df.setDiffComparator(RawTextComparator.DEFAULT);
		df.setDetectRenames(true);

		List<DiffEntry> diffs = df.scan(parent.getTree(), commit.getTree());
		ArrayList<String> added = new ArrayList<>();
		ArrayList<String> removed = new ArrayList<>();
		ArrayList<String> modified = new ArrayList<>();
		ArrayList<String> renamed = new ArrayList<>();
		ArrayList<String> copied = new ArrayList<>();

		for (DiffEntry diff : diffs) {
			if (diff.getChangeType().name().equals("MODIFY") && !modified.contains(diff.getNewPath())) {
				modified.add(diff.getNewPath());
			} else if (diff.getChangeType().name().equals("ADD") && !added.contains(diff.getNewPath())) {
				added.add(diff.getNewPath());
			} else if (diff.getChangeType().name().equals("DELETE") && !removed.contains(diff.getOldPath())) {
				removed.add(diff.getOldPath());
			} else if (diff.getChangeType().name().equals("RENAME") && !renamed.contains(diff.getNewPath())) {
				renamed.add(diff.getNewPath());
			} else if (diff.getChangeType().name().equals("COPY") && !copied.contains(diff.getNewPath())) {
				copied.add(diff.getNewPath());
			}
		}

		df.close();
		rw.close();

		List<String> result = new ArrayList<>();
		List<FileChangeType> tipos = Arrays.asList(types);

		if (tipos.contains(FileChangeType.ADDED)) {
			result.addAll(added);
		}

		if (tipos.contains(FileChangeType.DELETED)) {
			result.addAll(removed);
		}

		if (tipos.contains(FileChangeType.MODIFIED)) {
			result.addAll(modified);
			result.addAll(copied);
			result.addAll(renamed);
		}

		return result;
	}
	
	public String listAuthorByCommit(String d1, String d2) throws NoHeadException, GitAPIException {
		
		Iterable<RevCommit> revisions = searchByDate(d1, d2);
		
		Map<String, Integer> count = new HashMap<String, Integer>();
		
		for (RevCommit rev : revisions) {
			String author = rev.getAuthorIdent().getName();
			
			Integer i = count.get(author);
			
			if (i == null) {
				count.put(author, 1);
			} else {
				count.put(author, i + 1);
			}
			
		}
		
		Integer number_changes = 0;
		String author_more_modifications = null;
		
		for (String author : count.keySet()) {
			Integer i = count.get(author);
			
			if (i > number_changes) {
				number_changes = i;
				author_more_modifications = author;
			}
		}
		
		return author_more_modifications + number_changes;
		
	}
	
	public void close() {
		git.close();
	}

}
