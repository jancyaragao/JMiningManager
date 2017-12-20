package jmm.gui;

import java.util.Date;
import java.util.List;

import jmm.common.DateUtil;
import jmm.common.JMMRepository;
import jmm.git.JMMGit;
import jmm.model.ChangedFile;

public class MainGit {

	public static void main(String[] args) {
		testAuthorFromCommit();
	}

	public static void testFilesFromCommit() {
		JMMRepository git = new JMMGit("C:/Users/felipe/git/wicket", "", "");

		List<ChangedFile> files = git.filesFromCommit("7729e4d469ff728a66dfec3818e584a504b9753c");

		System.out.println(files.size());
		System.out.println(files);
	}

	public static void testFilesBetweenCommits() {
		JMMRepository git = new JMMGit("C:/Users/jancy/git/wicket", "", "");

		List<ChangedFile> files = git.filesBetweenCommits("7729e4d469ff728a66dfec3818e584a504b9753c", "1421ea2dc9207143cdadb735f3c79421674d924d");

		System.out.println(files.size());
		System.out.println(files);
	}

	public static void testAuthorFromCommit() {
		JMMRepository git = new JMMGit("C:/Users/jancy/git/wicket", "", "");
		System.out.println(git.authorFromCommit("7729e4d469ff728a66dfec3818e584a504b9753c"));
	}

	public static void testChangeFromCommit() {
		JMMRepository git = new JMMGit("C:/Users/jancy/git/wicket", "", "");
		System.out.println(git.changeFromCommit("81684f93dde752bed5208353a035a524430d08be"));
	}

	public static void testFilesBetweenDates() {
		JMMRepository git = new JMMGit("C:/Users/jancy/git/wicket", "", "");

		Date initial_date = DateUtil.stringToDate("01/03/2017");
		Date final_date = DateUtil.stringToDate("20/03/2017");

		List<ChangedFile> files = git.filesBetweenDates(initial_date, final_date);

		System.out.println(files.size());
		System.out.println(files);
	}

}
