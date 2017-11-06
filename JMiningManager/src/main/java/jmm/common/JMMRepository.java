package jmm.common;

import java.util.Date;
import java.util.List;

import jmm.model.Author;
import jmm.model.Change;
import jmm.model.ChangedFile;

public interface JMMRepository {

	// Queries for changes
	public Change changeCommit(String commit_code);

	public List<Change> changesBetweenCommits(String initial_commit, String final_commit);

	public List<Change> changesBetweenDates(Date initial_date, Date final_date);

	public List<Change> changesBetweenDates(String initial_date, String final_date);

	// Queries for author
	public Author authorFromCommit(String commit_code);

	public List<Author> authorsBetweenCommits(String initial_commit, String final_commit, boolean sorted);

	public List<Author> authorsBetweenDates(Date initial_date, Date final_date, boolean sorted);

	public List<Author> authorsBetweenDates(String initial_date, String final_date, boolean sorted);

	// Queries for paths
	public List<ChangedFile> pathsFromCommit(String commit_code);

	public List<ChangedFile> pathsBetweenCommits(String initial_commit, String final_commit, boolean sorted);

	public List<ChangedFile> pathsBetweenDates(Date initial_date, Date final_date, boolean sorted);

	public List<ChangedFile> pathsBetweenDates(String initial_date, String final_date, boolean sorted);

}
