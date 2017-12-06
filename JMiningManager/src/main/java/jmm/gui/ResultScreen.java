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
import jmm.model.Change;

public class ResultScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultScreen frame = new ResultScreen(new Change());
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ResultScreen(Change c) {
		init();

		String[] columns = new String[] { "Author Name", "Author E-mail", "Commit Date", "Commit Code" };

		Object[][] rows = new Object[][] {
			{ c.getAuthor().getName(), c.getAuthor().getEmail(), DateUtil.converterDateParaString(c.getDate()), c.getCommit() }
		};

		Font f = new Font("Cambria", Font.PLAIN, 18);
		
		JTable table = new JTable(rows, columns);
		table.setFont(f);
		table.getTableHeader().setFont(f);
		table.setRowHeight(30);
		table.setEnabled(false);
		add(new JScrollPane(table));
	}
	
	public ResultScreen(List<Change> changes) {
		init();

		String[] columns = new String[] { "Author Name", "Author E-mail", "Commit Date", "Commit Code" };
		
		Object[][] rows = new Object[changes.size()][];
		
		for (int i = 0; i < rows.length; i++) {
			Change c = changes.get(i);
			rows[i] = new Object[]{ c.getAuthor().getName(), c.getAuthor().getEmail(), DateUtil.converterDateParaString(c.getDate()), c.getCommit() };
		}

		Font f = new Font("Cambria", Font.PLAIN, 18);
		
		JTable table = new JTable(rows, columns);
		table.setFont(f);
		table.getTableHeader().setFont(f);
		table.setRowHeight(30);
		table.setEnabled(false);
		add(new JScrollPane(table));
	}

	private void init() {
		//setResizable(false);
		setTitle("JMM - Java Mining Manager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("src/main/java/jmm/logo/jmm_logo.png").getImage());

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(screenSize.width * 2 / 3, screenSize.height * 2 / 3));
		
		pack();
	}

}
