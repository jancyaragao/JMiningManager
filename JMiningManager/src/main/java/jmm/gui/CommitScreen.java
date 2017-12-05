package jmm.gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jmm.common.JMMRepository;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CommitScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldInitial;
	private JTextField textFieldFinal;

	private JMMRepository repository;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CommitScreen frame = new CommitScreen(null);
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
	public CommitScreen(JMMRepository repository) {
		this.repository = repository;
		
		setFont(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("JMM - Java Mining Manager");
		setBounds(325, 125, 640, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon logo = new ImageIcon("src/main/java/jmm/logo/jmm_logo.png");
		setIconImage(logo.getImage());
		
		JLabel lblInitial = new JLabel("Initial Commit:*");
		lblInitial.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblInitial.setBounds(39, 65, 125, 33);
		contentPane.add(lblInitial);
		
		textFieldInitial = new JTextField();
		textFieldInitial.setToolTipText("Report the initial commit");
		textFieldInitial.setBounds(166, 65, 135, 33);
		contentPane.add(textFieldInitial);
		textFieldInitial.setColumns(10);
		
		JLabel lblFinal = new JLabel("Final Commit:");
		lblFinal.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblFinal.setBounds(337, 65, 115, 33);
		contentPane.add(lblFinal);
		
		textFieldFinal = new JTextField();
		textFieldFinal.setToolTipText("Report the final commit");
		textFieldFinal.setBounds(452, 65, 135, 33);
		contentPane.add(textFieldFinal);
		textFieldFinal.setColumns(10);
		
		JLabel lblQueries = new JLabel("Queries:*");
		lblQueries.setToolTipText("Queries to be searched");
		lblQueries.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblQueries.setBounds(57, 161, 75, 33);
		contentPane.add(lblQueries);
		
		JCheckBox chckbxForChanges = new JCheckBox("For Changes");
		chckbxForChanges.setToolTipText("Queries for change");
		chckbxForChanges.setFont(new Font("Cambria", Font.PLAIN, 18));
		chckbxForChanges.setBounds(152, 161, 125, 33);
		contentPane.add(chckbxForChanges);
		
		JCheckBox chckbxForAuthor = new JCheckBox("For Author");
		chckbxForAuthor.setToolTipText("Queries for author");
		chckbxForAuthor.setFont(new Font("Cambria", Font.PLAIN, 18));
		chckbxForAuthor.setBounds(308, 161, 120, 33);
		contentPane.add(chckbxForAuthor);
		
		JCheckBox chckbxForFiles = new JCheckBox("For Files");
		chckbxForFiles.setToolTipText("Queries for files");
		chckbxForFiles.setFont(new Font("Cambria", Font.PLAIN, 18));
		chckbxForFiles.setBounds(454, 161, 100, 33);
		contentPane.add(chckbxForFiles);
		
		ButtonGroup group1 = new ButtonGroup();
		group1.add(chckbxForChanges);
		group1.add(chckbxForAuthor);
		group1.add(chckbxForFiles);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FirstScreen firstScreen = new FirstScreen();
				firstScreen.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Cambria", Font.PLAIN, 18));
		btnBack.setBounds(150, 262, 135, 23);
		contentPane.add(btnBack);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldInitial.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Report the initial commit", "Attention", JOptionPane.WARNING_MESSAGE);
				} else if (group1.isSelected(null)) {
					JOptionPane.showMessageDialog(null, "Report the queries to be searched", "Attention", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		btnSearch.setFont(new Font("Cambria", Font.PLAIN, 18));
		btnSearch.setBounds(351, 262, 135, 23);
		contentPane.add(btnSearch);
		
		
	}

}
