package jmm.model;

import java.util.Date;
import java.util.List;

public class Change {

	private String commit;
	private Author author;
	private Date date;

	private List<ChangedFile> files;

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

	public List<ChangedFile> getFiles() {
		return files;
	}

	public void setFiles(List<ChangedFile> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Change [commit=" + commit + ", author=" + author + ", date=" + date + ", files=" + files + "]";
	}

}
