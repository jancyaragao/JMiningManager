package jmm.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import jmm.common.JMMRepository;
import jmm.model.Author;
import jmm.model.Change;
import jmm.model.ChangedFile;

public class CommitScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldInitial;
	private JTextField textFieldFinal;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CommitScreen frame = new CommitScreen(null);
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
	public CommitScreen(JMMRepository repository) {
		setResizable(false);
		setFont(null);
		setTitle("JMM - Java Mining Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(325, 125, 640, 360);
 		contentPane = new JPanel();
 		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
 		setContentPane(contentPane);
 		contentPane.setLayout(null);
		ImageIcon logo = new ImageIcon("src/image/jmm_logo.png");
		setIconImage(logo.getImage());
		
		setLocationRelativeTo(null);
		
		JLabel lblInitial = new JLabel("Initial Commit:*");
		lblInitial.setHorizontalAlignment(SwingConstants.CENTER);
		lblInitial.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblInitial.setBounds(58, 56, 125, 33);
		contentPane.add(lblInitial);
		
		textFieldInitial = new JTextField();
		textFieldInitial.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldInitial.setFont(new Font("Cambria", Font.PLAIN, 16));
		textFieldInitial.setToolTipText("Report the initial commit");
		textFieldInitial.setBounds(197, 56, 370, 33);
		contentPane.add(textFieldInitial);
		textFieldInitial.setColumns(10);
		
		JLabel lblFinal = new JLabel("Final Commit:");
		lblFinal.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinal.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblFinal.setBounds(59, 115, 115, 33);
		contentPane.add(lblFinal);
		
		textFieldFinal = new JTextField();
		textFieldFinal.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFinal.setFont(new Font("Cambria", Font.PLAIN, 16));
		textFieldFinal.setToolTipText("Report the final commit");
		textFieldFinal.setBounds(197, 115, 370, 33);
		contentPane.add(textFieldFinal);
		textFieldFinal.setColumns(10);
		
		JLabel lblQueries = new JLabel("Queries:*");
		lblQueries.setHorizontalAlignment(SwingConstants.CENTER);
		lblQueries.setToolTipText("Queries to be searched");
		lblQueries.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblQueries.setBounds(78, 170, 75, 33);
		contentPane.add(lblQueries);
		
		JCheckBox chckbxForChanges = new JCheckBox("Changes");
		chckbxForChanges.setSelected(true);
		chckbxForChanges.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxForChanges.setToolTipText("Queries for change");
		chckbxForChanges.setFont(new Font("Cambria", Font.PLAIN, 18));
		chckbxForChanges.setBounds(173, 170, 125, 33);
		contentPane.add(chckbxForChanges);
		
		JCheckBox chckbxForAuthor = new JCheckBox("Authors");
		chckbxForAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxForAuthor.setToolTipText("Queries for author");
		chckbxForAuthor.setFont(new Font("Cambria", Font.PLAIN, 18));
		chckbxForAuthor.setBounds(329, 170, 120, 33);
		contentPane.add(chckbxForAuthor);
		
		JCheckBox chckbxForFiles = new JCheckBox("Files");
		chckbxForFiles.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxForFiles.setToolTipText("Queries for files");
		chckbxForFiles.setFont(new Font("Cambria", Font.PLAIN, 18));
		chckbxForFiles.setBounds(475, 170, 100, 33);
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
				} else {
					if (chckbxForChanges.isSelected()) {
						
						if (textFieldFinal.getText().length() == 0) {
							Change c = repository.changeFromCommit(textFieldInitial.getText());
							ResultScreen rs = new ResultScreen();
							rs.resultScreenChange(c);
							rs.setVisible(true);
						} else {
							List<Change> changes = repository.changesBetweenCommits(textFieldInitial.getText(), textFieldFinal.getText());
							ResultScreen rs = new ResultScreen();
							rs.resultScreenListChange(changes);
							rs.setVisible(true);
						}
						
					} else if (chckbxForAuthor.isSelected()) {
					
						if (textFieldFinal.getText().length() == 0) {
							Author a = repository.authorFromCommit(textFieldInitial.getText());
							ResultScreen rs = new ResultScreen();
							rs.resultScreenAuthor(a);
							rs.setVisible(true);
						}  else {
							List<Author> authors = repository.authorsBetweenCommits(textFieldInitial.getText(), textFieldFinal.getText(), false);
							ResultScreen rs = new ResultScreen();
							rs.resultScreenListAuthor(authors);
							rs.setVisible(true);
						} 
						
					} else if (chckbxForFiles.isSelected()) {
						if (textFieldFinal.getText().length() == 0) {
							List<ChangedFile> changedsFiles = repository.filesFromCommit(textFieldInitial.getText());
							ResultScreen rs = new ResultScreen();
							rs.resultScreenListFiles(changedsFiles);
							rs.setVisible(true);
						} else {
							List<ChangedFile> changedsFiles = repository.filesBetweenCommits(textFieldInitial.getText(), textFieldFinal.getText());
							ResultScreen rs = new ResultScreen();
							rs.resultScreenListFiles(changedsFiles);
							rs.setVisible(true);
						}
						
					}
				}
			}
		});
		btnSearch.setFont(new Font("Cambria", Font.PLAIN, 18));
		btnSearch.setBounds(351, 262, 135, 23);
		contentPane.add(btnSearch);
		
	}

}
