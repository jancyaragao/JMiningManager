package jmm.svn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import jmm.common.JMMRepository;
import jmm.model.Author;
import jmm.model.Change;
import jmm.model.ChangedFile;
import jmm.model.FileChangeType;

public class JMMSVN implements JMMRepository {

	private SVNRepository repository;

	public JMMSVN(String url, String user, String password) {
		DAVRepositoryFactory.setup();

		try {
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
		} catch (SVNException e) {
			e.printStackTrace();
		}

		ISVNAuthenticationManager authenticator = SVNWCUtil.createDefaultAuthenticationManager(user, password.toCharArray());
		repository.setAuthenticationManager(authenticator);
	}

	@Override
	public Change changeFromCommit(String commit_code) {
		List<Change> c = changesBetweenCommits(commit_code, commit_code);
		return c.isEmpty() ? null : c.get(0);
	}

	@Override
	public List<Change> changesBetweenCommits(String initial_commit, String final_commit) {
		long initial_revision = Long.parseLong(initial_commit);
		long final_revision = Long.parseLong(final_commit);

		Collection<?> logs = null;
		List<Change> changes = new ArrayList<Change>();

		try {
			logs = repository.log(new String[] { "" }, null, initial_revision, final_revision, true, true);
		} catch (SVNException e) {
			e.printStackTrace();
		}

		if (logs != null) {
			for (Object elem : logs) {
				SVNLogEntry r = (SVNLogEntry) elem;

				Author a = new Author();
				a.setChanges(null); // TODO: Vale a pena implementar isso agora?
				a.setEmail(""); // TODO: Sem e-mail para SVN?
				a.setName(r.getAuthor());

				Change c = new Change();
				c.setAuthor(a);
				c.setCommit(String.valueOf(r.getRevision()));
				c.setDate(r.getDate());
				c.setFiles(null); // TODO: Vale a pena implementar isso agora?

				changes.add(c);
			}
		}

		return changes;
	}

	@Override
	public List<Change> changesBetweenDates(Date initial_date, Date final_date) {
		long initial_revision = 0;
		long final_revision = 0;

		try {
			initial_revision = repository.getDatedRevision(initial_date);
			final_revision = repository.getDatedRevision(final_date);
		} catch (SVNException e) {
			e.printStackTrace();
		}

		return changesBetweenCommits(String.valueOf(initial_revision), String.valueOf(final_revision));
	}

	@Override
	public Author authorFromCommit(String commit_code) {
		List<Author> a = authorsBetweenCommits(commit_code, commit_code, false);
		return a.isEmpty() ? null : a.get(0);
	}

	@Override
	public List<Author> authorsBetweenCommits(String initial_commit, String final_commit, boolean sorted) {
		long initial_revision = Long.parseLong(initial_commit);
		long final_revision = Long.parseLong(final_commit);

		Collection<?> logs = null;
		List<Author> authors = new ArrayList<Author>();

		try {
			logs = repository.log(new String[] { "" }, null, initial_revision, final_revision, true, true);
		} catch (SVNException e) {
			e.printStackTrace();
		}

		if (logs != null) {
			for (Object elem : logs) {
				SVNLogEntry r = (SVNLogEntry) elem;

				Author a = new Author();
				a.setChanges(null); // TODO: Vale a pena implementar isso agora?
				a.setEmail(""); // TODO: Sem e-mail para SVN?
				a.setName(r.getAuthor());

				authors.add(a);
			}
		}

		return authors;
	}

	@Override
	public List<Author> authorsBetweenDates(Date initial_date, Date final_date, boolean sorted) {
		long initial_revision = 0;
		long final_revision = 0;

		try {
			initial_revision = repository.getDatedRevision(initial_date);
			final_revision = repository.getDatedRevision(final_date);
		} catch (SVNException e) {
			e.printStackTrace();
		}

		return authorsBetweenCommits(String.valueOf(initial_revision), String.valueOf(final_revision), sorted);
	}

	@Override
	public List<ChangedFile> filesFromCommit(String commit_code) {
		return filesBetweenCommits(commit_code, commit_code);
	}

	@Override
	public List<ChangedFile> filesBetweenCommits(String initial_commit, String final_commit) {
		long initial_revision = Long.parseLong(initial_commit);
		long final_revision = Long.parseLong(final_commit);

		Collection<?> logs = null;
		List<ChangedFile> changed_files = new ArrayList<ChangedFile>();

		try {
			logs = repository.log(new String[] { "" }, null, initial_revision, final_revision, true, true);
		} catch (SVNException e) {
			e.printStackTrace();
		}

		if (logs != null) {
			for (Object elem : logs) {
				SVNLogEntry r = (SVNLogEntry) elem;

				for (SVNLogEntryPath log_path : r.getChangedPaths().values()) {
					ChangedFile cf = new ChangedFile();
					char type = log_path.getType();

					cf.setChanges(-1); // TODO: Vale a pena implementar isso agora?
					cf.setPath(log_path.getPath());

					if (type == 'M' || type == 'R')
						cf.setType(FileChangeType.MODIFIED);
					else if (type == 'A')
						cf.setType(FileChangeType.ADDED);
					else if (type == 'D')
						cf.setType(FileChangeType.DELETED);

					changed_files.add(cf);
				}

			}
		}

		return changed_files;
	}

	@Override
	public List<ChangedFile> filesBetweenDates(Date initial_date, Date final_date) {
		long initial_revision = 0;
		long final_revision = 0;

		try {
			initial_revision = repository.getDatedRevision(initial_date);
			final_revision = repository.getDatedRevision(final_date);
		} catch (SVNException e) {
			e.printStackTrace();
		}

		return filesBetweenCommits(String.valueOf(initial_revision), String.valueOf(final_revision));
	}

	@Override
	public List<ChangedFile> filterFilesByChangeType(List<ChangedFile> files, FileChangeType[] types) {
		return null;
	}

	@Override
	public void closeJMMRepository() {
		repository.closeSession();
	}

}
