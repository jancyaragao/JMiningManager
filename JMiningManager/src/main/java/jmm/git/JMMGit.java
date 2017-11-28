package jmm.git;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.CommitTimeRevFilter;
import org.eclipse.jgit.revwalk.filter.RevFilter;

import jmm.common.JMMRepository;
import jmm.model.Author;
import jmm.model.Change;
import jmm.model.ChangedFile;
import jmm.model.FileChangeType;

public class JMMGit implements JMMRepository {

	private Git git;

	public JMMGit(String path) {
		try {
			git = Git.open(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Queries for changes
	@Override
	public List<Change> changesBetweenDates(Date initial_date, Date final_date) {
		RevFilter filtro = CommitTimeRevFilter.between(initial_date, final_date);

		Iterable<RevCommit> revisions = null;
		try {
			revisions = git.log().setRevFilter(filtro).call();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return GitUtil.fromRevCommitToChange(revisions);
	}

	@Override
	public List<Change> changesBetweenCommits(String initial_commit, String final_commit) {
		Repository rep = git.getRepository();
		Iterable<RevCommit> revisions = null;

		try {
			ObjectId initial_revision = rep.resolve(initial_commit);
			ObjectId final_revision = rep.resolve(final_commit);

			revisions = git.log().addRange(initial_revision, final_revision).call();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return GitUtil.fromRevCommitToChange(revisions);
	}

	@Override
	public Change changeFromCommit(String commit_code) {
		Repository rep = git.getRepository();
		RevCommit revision = null;

		try {
			RevWalk rw = new RevWalk(rep);
			revision = rw.parseCommit(rep.resolve(commit_code));
			rw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return GitUtil.fromRevCommitToChange(revision);
	}

	// Queries for files
	@Override
	public List<ChangedFile> filesFromCommit(String commit_code) {
		Repository rep = git.getRepository();

		RevCommit commit_revision = null;
		RevCommit parent_revision = null;
		RevWalk rw = new RevWalk(rep);

		try {
			commit_revision = rw.parseCommit(rep.resolve(commit_code));
			parent_revision = rw.parseCommit(commit_revision.getParent(0).getId());
		} catch (IOException e) {
			e.printStackTrace();
		}

		rw.close();

		return GitUtil.filesBetweenRevCommits(git.getRepository(), parent_revision, commit_revision);
	}

	@Override
	public List<ChangedFile> filesBetweenCommits(String initial_commit, String final_commit) {
		Repository rep = git.getRepository();

		RevCommit initial_revision = null;
		RevCommit final_revision = null;
		RevWalk rw = new RevWalk(rep);

		try {
			initial_revision = rw.parseCommit(rep.resolve(initial_commit));
			final_revision = rw.parseCommit(rep.resolve(final_commit));
		} catch (IOException e) {
			e.printStackTrace();
		}

		rw.close();

		return GitUtil.filesBetweenRevCommits(git.getRepository(), initial_revision, final_revision);
	}

	@Override
	public List<ChangedFile> filesBetweenDates(Date initial_date, Date final_date) {
		RevFilter filter = CommitTimeRevFilter.between(initial_date, final_date);

		Iterable<RevCommit> revisions = null;
		try {
			revisions = git.log().setRevFilter(filter).call();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* TODO: Temos como melhorar isso? */
		RevCommit initial_revision = revisions.iterator().next();
		RevCommit final_revision = initial_revision;

		for (RevCommit rev : revisions) {
			Date revdate = rev.getAuthorIdent().getWhen();

			if (revdate.before(initial_revision.getAuthorIdent().getWhen()))
				initial_revision = rev;

			if (revdate.after(final_revision.getAuthorIdent().getWhen()))
				final_revision = rev;
		}

		return GitUtil.filesBetweenRevCommits(git.getRepository(), initial_revision, final_revision);
	}

	// Queries for author
	@Override
	public Author authorFromCommit(String commit_code) {
		Repository rep = git.getRepository();
		RevCommit revision = null;

		RevWalk rw = new RevWalk(rep);
		try {
			revision = rw.parseCommit(rep.resolve(commit_code));
			rw.close();
		} catch (RevisionSyntaxException | IOException e) {
			e.printStackTrace();
		}

		return GitUtil.fromRevCommitToAuthor(revision);
	}

	@Override
	public List<Author> authorsBetweenCommits(String initial_commit, String final_commit, boolean sorted) {
		Repository rep = git.getRepository();
		Iterable<RevCommit> revisions = null;

		try {
			ObjectId initial_revision = rep.resolve(initial_commit);
			ObjectId final_revision = rep.resolve(final_commit);
			revisions = git.log().addRange(initial_revision, final_revision).call();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return GitUtil.fromRevCommitToAuthor(revisions);
	}

	@Override
	public List<Author> authorsBetweenDates(Date initial_date, Date final_date, boolean sorted) {
		RevFilter filter = CommitTimeRevFilter.between(initial_date, final_date);

		Iterable<RevCommit> revisions = null;

		try {
			revisions = git.log().setRevFilter(filter).call();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}

		return GitUtil.fromRevCommitToAuthor(revisions);
	}

	@Override
	public List<ChangedFile> filterFilesByChangeType(List<ChangedFile> files, FileChangeType[] types) {
		return null;
	}

	@Override
	public void closeJMMRepository() {
		git.close();
	}

}
