package jmm.model;

public class Author {

	private String name;
	private String email;
	private int changes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getChanges() {
		return changes;
	}

	public void setChanges(int changes) {
		this.changes = changes;
	}

	@Override
	public String toString() {
		return "Author [name=" + name + ", email=" + email + ", changes=" + changes + "]";
	}

}
