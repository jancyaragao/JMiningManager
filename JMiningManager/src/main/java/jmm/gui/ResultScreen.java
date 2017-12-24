package jmm.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import jmm.common.DateUtil;
import jmm.model.Author;
import jmm.model.Change;
import jmm.model.ChangedFile;

public class ResultScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultScreen frame = new ResultScreen();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ResultScreen() {
		setTitle("JMM - Java Mining Manager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("src/image/jmm_logo.png").getImage());

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(screenSize.width * 2 / 3, screenSize.height * 2 / 3));

		pack();
	}

	public void resultScreenChange(Change c) {
		String[] columns = new String[] { "Commit Date", "Commit Code", "Author Name", "Author E-mail" };

		Object[][] rows = new Object[][] { { DateUtil.dateToString(c.getDate()), c.getCommit(), c.getAuthor().getName(), c.getAuthor().getEmail() } };

		Font f = new Font("Cambria", Font.PLAIN, 18);

		JTable table = new JTable(rows, columns);
		table.setFont(f);
		table.getTableHeader().setFont(f);
		table.setRowHeight(30);
		table.setEnabled(false);
		//table.setAutoCreateRowSorter(true);
		add(new JScrollPane(table));
	}

	public void resultScreenListChange(List<Change> changes) {

		String[] columns = new String[] { "Commit Date", "Commit Code", "Author Name", "Author E-mail" };

		Object[][] rows = new Object[changes.size()][];

		for (int i = 0; i < rows.length; i++) {
			Change c = changes.get(i);
			rows[i] = new Object[] { DateUtil.dateToString(c.getDate()), c.getCommit(), c.getAuthor().getName(), c.getAuthor().getEmail() };
		}

		Font f = new Font("Cambria", Font.PLAIN, 18);

		JTable table = new JTable(rows, columns);
		table.setFont(f);
		table.getTableHeader().setFont(f);
		table.setRowHeight(30);
		table.setEnabled(false);
		//table.setAutoCreateRowSorter(true);
		add(new JScrollPane(table));
	}

	public void resultScreenAuthor(Author a) {
		String[] columns = new String[] { "Author Name", "Author E-mail", "Commit Date", "Commit Code" };

		Object[][] rows = new Object[][] { { a.getName(), a.getEmail() } };

		Font f = new Font("Cambria", Font.PLAIN, 18);

		JTable table = new JTable(rows, columns);
		table.setFont(f);
		table.getTableHeader().setFont(f);
		table.setRowHeight(30);
		table.setEnabled(false);
		//table.setAutoCreateRowSorter(true);
		add(new JScrollPane(table));
	}
	
	public void resultScreenListAuthor(List<Author> authors) {
		String[] columns = new String[] { "Author Name", "Author E-mail", "Number of Changes" };
		
		Object[][] rows = new Object[authors.size()][];
		
		for (int i = 0; i < rows.length; i++) {
			Author a = authors.get(i);
			rows[i] = new Object[]{ a.getName(), a.getEmail(), a.getChanges() };
		}

		Font f = new Font("Cambria", Font.PLAIN, 18);
		
		JTable table = new JTable(rows, columns);
		table.setFont(f);
		table.getTableHeader().setFont(f);
		table.setRowHeight(30);
		table.setEnabled(false);
		//table.setAutoCreateRowSorter(true);
		add(new JScrollPane(table));
	}
	
	public void resultScreenListFiles(List<ChangedFile> changedsFiles) {
		String[] columns = new String[] { "File Path", "File Type" };
		
		Object[][] rows = new Object[changedsFiles.size()][];
		
		for (int i = 0; i < rows.length; i++) {
			ChangedFile cf = changedsFiles.get(i);
			rows[i] = new Object[]{ cf.getPath(), cf.getType() };
		}

		Font f = new Font("Cambria", Font.PLAIN, 18);
		
		JTable table = new JTable(rows, columns);
		table.setFont(f);
		table.getTableHeader().setFont(f);
		table.setRowHeight(30);
		table.setEnabled(false);
		//table.setAutoCreateRowSorter(true);
		add(new JScrollPane(table));
	}

}
