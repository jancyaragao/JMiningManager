package jmm.git;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.util.io.DisabledOutputStream;

import jmm.model.Author;
import jmm.model.Change;
import jmm.model.ChangedFile;
import jmm.model.FileChangeType;

public abstract class GitUtil {

<<<<<<< HEAD
	protected static RevCommit fromCommitCodeToRevCommit(Repository rep, String commit_code) {
		RevCommit revision = null;

		try {
			RevWalk rw = new RevWalk(rep);
			revision = rw.parseCommit(rep.resolve(commit_code));
			rw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return revision;
	}
	
	protected static Change fromRevCommitToChange(RevCommit revision) {
		Change c = new Change();
		c.setAuthor(fromRevCommitToAuthor(revision));
		c.setCommit(revision.getName());
		c.setDate(revision.getAuthorIdent().getWhen());
		c.setFiles(null); // TODO: Vale a pena implementar agora?

		return c;
	}

	protected static List<Change> fromRevCommitToChange(Iterable<RevCommit> revisions) {
		List<Change> changes = new ArrayList<Change>();

		for (RevCommit rev : revisions)
			changes.add(fromRevCommitToChange(rev));

		return changes;
	}

	protected static List<ChangedFile> filesBetweenRevCommits(Repository rep, RevCommit initial_revision,
			RevCommit final_revision) {
		DiffFormatter df = new DiffFormatter(DisabledOutputStream.INSTANCE);
		df.setRepository(rep);
		df.setDiffComparator(RawTextComparator.DEFAULT);
		df.setDetectRenames(true);

		List<DiffEntry> diffs = null;
		try {
			diffs = df.scan(initial_revision.getTree(), final_revision.getTree());
		} catch (IOException e) {
			e.printStackTrace();
		}

		df.close();

		List<ChangedFile> changed_files = new ArrayList<ChangedFile>();

		for (DiffEntry diff : diffs) {
			String change_type = diff.getChangeType().name();
			ChangedFile cf = new ChangedFile();

			cf.setChanges(-1); // TODO: Vale a pena implementar isso agora?

			if (change_type.equals("MODIFY") || change_type.equals("RENAME") || change_type.equals("COPY")) {
				cf.setPath(diff.getNewPath());
				cf.setType(FileChangeType.MODIFIED);
			} else if (change_type.equals("ADD")) {
				cf.setPath(diff.getNewPath());
				cf.setType(FileChangeType.ADDED);
			} else if (change_type.equals("DELETE")) {
				cf.setPath(diff.getOldPath());
				cf.setType(FileChangeType.DELETED);
			}

			changed_files.add(cf);
		}

		return changed_files;
	}

	protected static Author fromRevCommitToAuthor(RevCommit revision) {
		Author a = new Author();
		a.setChanges(null); // TODO: Vale a pena implementar isso agora?
		a.setEmail(revision.getAuthorIdent().getEmailAddress());
		a.setName(revision.getAuthorIdent().getName());

		return a;
	}
	
	protected static List<Author> fromRevCommitToAuthor(Iterable<RevCommit> revisions) {
		List<Author> authors = new ArrayList<Author>();
		
		for (RevCommit rev : revisions) {
			authors.add(fromRevCommitToAuthor(rev));
		}
		
		return authors;
		
	}
	
	
=======
	protected static Change fromRevCommitToChange(RevCommit revision) {
		Change c = new Change();
		c.setAuthor(fromRevCommitToAuthor(revision));
		c.setCommit(revision.getName());
		c.setDate(revision.getAuthorIdent().getWhen());
		c.setFiles(null); // TODO: Vale a pena implementar agora?

		return c;
	}

	protected static List<Change> fromRevCommitToChange(Iterable<RevCommit> revisions) {
		List<Change> changes = new ArrayList<Change>();

		for (RevCommit rev : revisions)
			changes.add(fromRevCommitToChange(rev));

		return changes;
	}

	protected static List<ChangedFile> filesBetweenRevCommits(Repository rep, RevCommit initial_revision,
			RevCommit final_revision) {
		DiffFormatter df = new DiffFormatter(DisabledOutputStream.INSTANCE);
		df.setRepository(rep);
		df.setDiffComparator(RawTextComparator.DEFAULT);
		df.setDetectRenames(true);

		List<DiffEntry> diffs = null;
		try {
			diffs = df.scan(initial_revision.getTree(), final_revision.getTree());
		} catch (IOException e) {
			e.printStackTrace();
		}

		df.close();

		List<ChangedFile> changed_files = new ArrayList<ChangedFile>();

		for (DiffEntry diff : diffs) {
			String change_type = diff.getChangeType().name();
			ChangedFile cf = new ChangedFile();

			cf.setChanges(-1); // TODO: Vale a pena implementar isso agora?

			if (change_type.equals("MODIFY") || change_type.equals("RENAME") || change_type.equals("COPY")) {
				cf.setPath(diff.getNewPath());
				cf.setType(FileChangeType.MODIFIED);
			} else if (change_type.equals("ADD")) {
				cf.setPath(diff.getNewPath());
				cf.setType(FileChangeType.ADDED);
			} else if (change_type.equals("DELETE")) {
				cf.setPath(diff.getOldPath());
				cf.setType(FileChangeType.DELETED);
			}

			changed_files.add(cf);
		}

		return changed_files;
	}

	protected static RevCommit fromCommitCodeToRevCommit(Repository rep, String commit_code) {
		RevCommit revision = null;

		try {
			RevWalk rw = new RevWalk(rep);
			revision = rw.parseCommit(rep.resolve(commit_code));
			rw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return revision;
	}

	protected static Author fromRevCommitToAuthor(RevCommit revision) {
		Author a = new Author();
		a.setChanges(null); // TODO: Vale a pena implementar isso agora?
		a.setEmail(revision.getAuthorIdent().getEmailAddress());
		a.setName(revision.getAuthorIdent().getName());

		return a;
	}
>>>>>>> branch 'master' of https://github.com/jancywdson/Version-Control-System.git

}
