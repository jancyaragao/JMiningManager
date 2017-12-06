package jmm.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import jmm.model.Author;
import jmm.model.Change;

public class ResultScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultScreen frame = new ResultScreen(new Author());
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
	 */
	public ResultScreen(Change c) {
		init();
	}
	
	public ResultScreen(Author a) {
		init();
		JTextArea tarea = new JTextArea();
		
		tarea.setBounds(0, 0, 448, 233);
		tarea.setEditable(false);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Nome: " + a.getName() + System.lineSeparator());
		sb.append("E-Mail: " + a.getEmail() + System.lineSeparator());
		sb.append("--------------------------");
		
		tarea.setText(sb.toString());
		
		contentPane.add(tarea);
	}

	private void init() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		JScrollBar scrollBar = new JScrollBar();
//		scrollBar.setBounds(447, 0, 17, 233);
//		contentPane.add(scrollBar);
//		
//		DefaultTableModel jTableModel = new DefaultTableModel(null, new String[] {});
//		table = new JTable(jTableModel);
//		table.setBounds(0, 0, 448, 233);
//		contentPane.add(table);
	}
}
