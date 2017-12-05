package jmm.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FirstScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldRepository;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstScreen frame = new FirstScreen();
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
	public FirstScreen() {
		setFont(null);
		setTitle("JMM - Java Mining Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(325, 125, 640, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon logo = new ImageIcon("src/main/java/jmm/logo/jmm_logo.png");
		setIconImage(logo.getImage());
		
		JLabel lblVersion = new JLabel("Which version control system?*");
		lblVersion.setToolTipText("Select version control system");
		lblVersion.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblVersion.setBounds(50, 50, 250, 33);
		contentPane.add(lblVersion);
		
		JRadioButton rdbtnGit = new JRadioButton("Git");
		rdbtnGit.setToolTipText("Git version control system");
		rdbtnGit.setFont(new Font("Cambria", Font.PLAIN, 18));
		rdbtnGit.setBounds(359, 55, 55, 23);
		contentPane.add(rdbtnGit);
		
		JRadioButton rdbtnSvn = new JRadioButton("SVN");
		rdbtnSvn.setToolTipText("SVN version control system");
		rdbtnSvn.setFont(new Font("Cambria", Font.PLAIN, 18));
		rdbtnSvn.setBounds(459, 55, 69, 23);
		contentPane.add(rdbtnSvn);
		
		ButtonGroup group1 = new ButtonGroup();
		group1.add(rdbtnSvn);
		group1.add(rdbtnGit);
		
		JLabel lblSearch = new JLabel("Search by:*");
		lblSearch.setToolTipText("Select how to search");
		lblSearch.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblSearch.setBounds(129, 113, 90, 23);
		contentPane.add(lblSearch);
		
		JRadioButton rdbtnCommit = new JRadioButton("Commit");
		rdbtnCommit.setToolTipText("Search by commit");
		rdbtnCommit.setFont(new Font("Cambria", Font.PLAIN, 18));
		rdbtnCommit.setBounds(338, 113, 86, 23);
		contentPane.add(rdbtnCommit);
		
		JRadioButton rdbtnDate = new JRadioButton("Date");
		rdbtnDate.setToolTipText("Search by date");
		rdbtnDate.setFont(new Font("Cambria", Font.PLAIN, 18));
		rdbtnDate.setBounds(456, 113, 61, 23);
		contentPane.add(rdbtnDate);
		
		ButtonGroup group2 = new ButtonGroup();
		group2.add(rdbtnCommit);
		group2.add(rdbtnDate);
		
		JLabel lblRepository = new JLabel("Repository:*");
		lblRepository.setToolTipText("Repository to search");
		lblRepository.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblRepository.setBounds(123, 168, 100, 35);
		contentPane.add(lblRepository);
		
		textFieldRepository = new JTextField();
		textFieldRepository.setToolTipText("Report the repository");
		textFieldRepository.setFont(new Font("Cambria", Font.PLAIN, 14));
		textFieldRepository.setBounds(317, 171, 250, 33);
		contentPane.add(textFieldRepository);
		textFieldRepository.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Cambria", Font.PLAIN, 18));
		btnCancel.setBounds(150, 262, 135, 23);
		contentPane.add(btnCancel);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (group1.isSelected(null)) {
					JOptionPane.showMessageDialog(null, "Select version control system", "Attention", JOptionPane.WARNING_MESSAGE);
				} else if (group2.isSelected(null)) {
					JOptionPane.showMessageDialog(null, "Select how to search", "Attention", JOptionPane.WARNING_MESSAGE);
				} else if (textFieldRepository.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Report the repository", "Attention", JOptionPane.WARNING_MESSAGE);
				} else {
					if (rdbtnCommit.isSelected()) {
						CommitScreen commitScreen = new CommitScreen();
						commitScreen.setVisible(true);
						dispose();
					} else if (rdbtnDate.isSelected()) {
						DateScreen dateScreen = new DateScreen();
						dateScreen.setVisible(true);
						dispose();
					}
				}
			}
		});
		
		btnNext.setFont(new Font("Cambria", Font.PLAIN, 18));
		btnNext.setBounds(351, 262, 135, 23);
		contentPane.add(btnNext);
		
	}
}