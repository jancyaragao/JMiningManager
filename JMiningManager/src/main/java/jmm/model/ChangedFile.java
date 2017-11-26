package jmm.model;

public class ChangedFile {

	private String path;
	private int changes;
	private FileChangeType type;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getChanges() {
		return changes;
	}

	public void setChanges(int changes) {
		this.changes = changes;
	}

	public FileChangeType getType() {
		return type;
	}

	public void setType(FileChangeType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ChangedFile [path=" + path + ", changes=" + changes + ", type=" + type + "]";
	}

}
