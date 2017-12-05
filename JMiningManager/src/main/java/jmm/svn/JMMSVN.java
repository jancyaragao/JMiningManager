package jmm.svn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import jmm.common.DataUtil;
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
	
	public Collection<?> searchByRevision(String r_initial, String r_final) throws NumberFormatException, SVNException {
		return searchByRevision(Long.parseLong(r_initial), Long.parseLong(r_initial));
	}
	
	public Collection<?> searchByRevision(long initialRevision, long finalRevision) throws SVNException {
		
		Collection<?> revisions = repository.log(new String[] { "" }, null, initialRevision, finalRevision, true, true);
		return revisions;
		
	}
	
	public Collection<?> searchByDate(String initialDate, String finalDate) throws SVNException {
		
		Date start = DataUtil.converterStringParaDate(initialDate);
		Date end = DataUtil.converterStringParaDate(finalDate);
		
		long initialReviosion = repository.getDatedRevision(start);
		long finalReviosion = repository.getDatedRevision(end);
		
		Collection<?> reviosions = repository.log(new String[] { "" }, null, initialReviosion, finalReviosion, true, true);
		return reviosions;
		
	}
	
	// TODO: Adaptar para usar apenas uma lista (economizar mem�ria).
	public List<String> listFiles(String id, FileChangeType[] types) throws SVNException {
		
		Collection<?> revisions = searchByRevision(1000, 1050);
		
		ArrayList<String> added = new ArrayList<>();
		ArrayList<String> removed = new ArrayList<>();
		ArrayList<String> modified = new ArrayList<>();
		ArrayList<String> replaced = new ArrayList<>();
		
		for (Object objeto : revisions) {
			SVNLogEntry rev = (SVNLogEntry) objeto;
			Collection<SVNLogEntryPath> arquivos = rev.getChangedPaths().values();
			
			for (SVNLogEntryPath arq : arquivos) {
				
				char aux = arq.getType();
				
				if (aux == 'M' && !modified.contains(arq.getPath())) {
					modified.add(arq.getPath());
				} else if (aux == 'A' && !added.contains(arq.getPath())) {
					added.add(arq.getPath());
				} else if (aux == 'D' && !removed.contains(arq.getPath())) {
					removed.add(arq.getPath());
				} else if (aux == 'R' && !replaced.contains(arq.getPath())) {
					replaced.add(arq.getPath());
				}
				
			}
			
		}
		
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
			result.addAll(replaced);
		}

		return result;
		
	}
	
	public String listAuthorByCommit(String d1, String d2) throws SVNException {
		
		Collection<?> revisions = searchByDate("12/11/2001", "21/11/2001");
		Map<String, Integer> count = new HashMap<String, Integer>();
		
		for (Object object : revisions) {
			SVNLogEntry rev = (SVNLogEntry) object;
			String author = rev.getAuthor();
			
			Integer iAuthor = count.get(author);
			
			if (iAuthor == null) {
				count.put(author, 1);
			} else {
				count.put(author, iAuthor + 1);
			}
			
		}
		
		int number_changes = 0;
		String author_more_modifications = "";
		
		for (String author : count.keySet()) {
			Integer i = count.get(author);
			
			if (i > number_changes) {
				number_changes = i;
				author_more_modifications = author;
			}
			
		}
		
		return author_more_modifications + number_changes;
	}

	@Override
	public Change changeFromCommit(String commit_code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Change> changesBetweenCommits(String initial_commit, String final_commit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Change> changesBetweenDates(Date initial_date, Date final_date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author authorFromCommit(String commit_code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> authorsBetweenCommits(String initial_commit, String final_commit, boolean sorted) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> authorsBetweenDates(Date initial_date, Date final_date, boolean sorted) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChangedFile> filesFromCommit(String commit_code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChangedFile> filesBetweenCommits(String initial_commit, String final_commit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChangedFile> filesBetweenDates(Date initial_date, Date final_date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChangedFile> filterFilesByChangeType(List<ChangedFile> files, FileChangeType[] types) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeJMMRepository() {
		// TODO Auto-generated method stub
		
	}
	
}
