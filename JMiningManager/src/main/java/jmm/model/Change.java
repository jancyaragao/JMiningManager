package jmm.model;

import java.util.Date;
import java.util.List;

public class Change {

	private String commit;
	private Author author;
	private Date date;

	private List<ChangedFile> paths;

	public String getCommit() {
		return commit;
	}

	public void setCommit(String commit) {
		this.commit = commit;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<ChangedFile> getPaths() {
		return paths;
	}

	public void setPaths(List<ChangedFile> paths) {
		this.paths = paths;
	}

}
