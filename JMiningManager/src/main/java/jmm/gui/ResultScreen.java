package jmm.gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import jmm.model.Author;
import jmm.model.Change;

public class ResultScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable tableResult;
	
//	private final String colunas[] = {};
//	private final String dados[][] = {};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultScreen frame = new ResultScreen(null);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public ResultScreen(Change c) {
		init();
		final String colunas[] = {"Commit", "Author", "Date", "Files"};
		final String dados[] = {String.valueOf(c.getCommit()), c.getAuthor().getName(), String.valueOf(c.getDate()), String.valueOf(c.getFiles())};
		System.out.println(colunas + System.lineSeparator() + dados);
	}
	
//	public ResultScreen(Author a) {
//		init();
//		
////		JTextArea tarea = new JTextArea();
////		
////		tarea.setBounds(0, 0, 448, 233);
////		tarea.setEditable(false);
////		
////		StringBuilder sb = new StringBuilder();
////		sb.append("Nome: " + a.getName() + System.lineSeparator());
////		sb.append("E-Mail: " + a.getEmail() + System.lineSeparator());
////		sb.append("--------------------------");
////		
////		tarea.setText(sb.toString());
////		
////		contentPane.add(tarea);
//	}

	private void init() {
		setResizable(false);
		setFont(null);
		setTitle("JMM - Java Mining Manager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon logo = new ImageIcon("src/main/java/jmm/logo/jmm_logo.png");
		setIconImage(logo.getImage());
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(457, 0, 17, 243);
		contentPane.add(scrollBar);
		
		tableResult = new JTable();
		tableResult.setBounds(0, 0, 464, 243);
		contentPane.add(tableResult);
	}
}
