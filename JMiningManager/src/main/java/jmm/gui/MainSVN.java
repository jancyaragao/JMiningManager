package jmm.gui;

import java.util.Date;

import jmm.common.DateUtil;
import jmm.common.JMMRepository;
import jmm.model.Author;
import jmm.model.Change;
import jmm.model.ChangedFile;
import jmm.svn.JMMSVN;

public class MainSVN {

	public static void main(String[] args) {
		testChangesBetweenCommits("http://argouml.tigris.org/svn/argouml", "guest", "");
		// testTCCExample();
	}

	public static void testTCCExample() {
		String url = "http://argouml.tigris.org/svn/argouml";
		String user = "guest";
		String pwd = "";

		JMMRepository svn = new JMMSVN(url, user, pwd);

		Date initial_date = DateUtil.stringToDate("12/11/2001");
		Date final_date = DateUtil.stringToDate("21/11/2001");

		for (Change c : svn.changesBetweenDates(initial_date, final_date))
			System.out.println(c);

		System.out.println("----------------------------");

		for (Author a : svn.authorsBetweenDates(initial_date, final_date))
			System.out.println(a);

		System.out.println("----------------------------");

		for (ChangedFile cf : svn.filesFromCommit("2001"))
			System.out.println(cf);
	}

	public static void testFilesFromCommit(String url, String user, String pwd) {
		JMMRepository svn = new JMMSVN(url, user, pwd);

		for (ChangedFile cf : svn.filesFromCommit("2001"))
			System.out.println(cf);
	}

	public static void testFilesBetweenCommits(String url, String user, String pwd) {
		JMMRepository svn = new JMMSVN(url, user, pwd);

		for (ChangedFile cf : svn.filesBetweenCommits("1000", "1050"))
			System.out.println(cf);
	}

	public static void testFilesBetweenDates(String url, String user, String pwd) {
		JMMRepository svn = new JMMSVN(url, user, pwd);

		Date initial_date = DateUtil.stringToDate("12/11/2001");
		Date final_date = DateUtil.stringToDate("21/11/2001");

		for (ChangedFile cf : svn.filesBetweenDates(initial_date, final_date))
			System.out.println(cf);
	}

	public static void testAuthorFromCommit(String url, String user, String pwd) {
		JMMRepository svn = new JMMSVN(url, user, pwd);
		System.out.println(svn.authorFromCommit("1000"));
	}

	public static void testAuthorsBetweenCommits(String url, String user, String pwd) {
		JMMRepository svn = new JMMSVN(url, user, pwd);

		for (Author a : svn.authorsBetweenCommits("1000", "1050"))
			System.out.println(a);
	}

	public static void testAuthorsBetweenDates(String url, String user, String pwd) {
		JMMRepository svn = new JMMSVN(url, user, pwd);

		Date initial_date = DateUtil.stringToDate("12/11/2001");
		Date final_date = DateUtil.stringToDate("21/11/2001");

		for (Author a : svn.authorsBetweenDates(initial_date, final_date))
			System.out.println(a);
	}

	public static void testChangeFromCommit(String url, String user, String pwd) {
		JMMRepository svn = new JMMSVN(url, user, pwd);
		System.out.println(svn.changeFromCommit("1000"));
	}

	public static void testChangesBetweenCommits(String url, String user, String pwd) {
		JMMRepository svn = new JMMSVN(url, user, pwd);

		for (Change c : svn.changesBetweenCommits("1000", "1050"))
			System.out.println(c);
	}

	public static void testChangesBetweenDates(String url, String user, String pwd) {
		JMMRepository svn = new JMMSVN(url, user, pwd);

		Date initial_date = DateUtil.stringToDate("12/11/2001");
		Date final_date = DateUtil.stringToDate("21/11/2001");

		for (Change c : svn.changesBetweenDates(initial_date, final_date))
			System.out.println(c);
	}

}
