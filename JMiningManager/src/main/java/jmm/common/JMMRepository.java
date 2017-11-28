package jmm.common;

import java.util.Date;
import java.util.List;

import jmm.model.Author;
import jmm.model.Change;
import jmm.model.ChangedFile;
import jmm.model.FileChangeType;

public interface JMMRepository {

	// Queries for changes
	public Change changeFromCommit(String commit_code);

	public List<Change> changesBetweenCommits(String initial_commit, String final_commit);

	public List<Change> changesBetweenDates(Date initial_date, Date final_date);

	// Queries for author
	public Author authorFromCommit(String commit_code);

	public List<Author> authorsBetweenCommits(String initial_commit, String final_commit, boolean sorted);

	public List<Author> authorsBetweenDates(Date initial_date, Date final_date, boolean sorted);

	// Queries for files
	public List<ChangedFile> filesFromCommit(String commit_code);

	public List<ChangedFile> filesBetweenCommits(String initial_commit, String final_commit);

	public List<ChangedFile> filesBetweenDates(Date initial_date, Date final_date);

	// Other operations
	public List<ChangedFile> filterFilesByChangeType(List<ChangedFile> files, FileChangeType[] types);

	public void closeJMMRepository();

}
