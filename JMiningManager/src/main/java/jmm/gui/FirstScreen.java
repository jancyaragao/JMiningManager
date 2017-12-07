package jmm.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import jmm.common.JMMRepository;
import jmm.git.JMMGit;
import jmm.svn.JMMSVN;

public class FirstScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldURL;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstScreen frame = new FirstScreen();
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
	public FirstScreen() {
		setResizable(false);
		setFont(null);
		setTitle("JMM - Java Mining Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon logo = new ImageIcon("src/image/jmm_logo.png");
		setIconImage(logo.getImage());
		
		JLabel lblVersion = new JLabel("Which version control system?*");
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersion.setToolTipText("Select version control system");
		lblVersion.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblVersion.setBounds(190, 94, 250, 33);
		contentPane.add(lblVersion);
		
		JRadioButton rdbtnGit = new JRadioButton("Git");
		rdbtnGit.setSelected(true);
		rdbtnGit.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnGit.setToolTipText("Git version control system");
		rdbtnGit.setFont(new Font("Cambria", Font.PLAIN, 18));
		rdbtnGit.setBounds(499, 99, 55, 23);
		contentPane.add(rdbtnGit);
		
		JRadioButton rdbtnSvn = new JRadioButton("SVN");
		rdbtnSvn.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnSvn.setToolTipText("SVN version control system");
		rdbtnSvn.setFont(new Font("Cambria", Font.PLAIN, 18));
		rdbtnSvn.setBounds(599, 99, 69, 23);
		contentPane.add(rdbtnSvn);
		
		ButtonGroup group1 = new ButtonGroup();
		group1.add(rdbtnSvn);
		group1.add(rdbtnGit);
		
		JLabel lblSearch = new JLabel("Search by:*");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setToolTipText("Select how to search");
		lblSearch.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblSearch.setBounds(268, 144, 90, 23);
		contentPane.add(lblSearch);
		
		JRadioButton rdbtnCommit = new JRadioButton("Commit");
		rdbtnCommit.setSelected(true);
		rdbtnCommit.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnCommit.setToolTipText("Search by commit");
		rdbtnCommit.setFont(new Font("Cambria", Font.PLAIN, 18));
		rdbtnCommit.setBounds(477, 144, 86, 23);
		contentPane.add(rdbtnCommit);
		
		JRadioButton rdbtnDate = new JRadioButton("Date");
		rdbtnDate.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnDate.setToolTipText("Search by date");
		rdbtnDate.setFont(new Font("Cambria", Font.PLAIN, 18));
		rdbtnDate.setBounds(595, 144, 61, 23);
		contentPane.add(rdbtnDate);
		
		ButtonGroup group2 = new ButtonGroup();
		group2.add(rdbtnCommit);
		group2.add(rdbtnDate);
		
		JLabel lblURL = new JLabel("URL:*");
		lblURL.setHorizontalAlignment(SwingConstants.CENTER);
		lblURL.setToolTipText("Repository path to search for");
		lblURL.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblURL.setBounds(289, 188, 50, 35);
		contentPane.add(lblURL);
		
		textFieldURL = new JTextField();
		textFieldURL.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldURL.setToolTipText("Report the repository");
		textFieldURL.setFont(new Font("Cambria", Font.PLAIN, 14));
		textFieldURL.setBounds(457, 191, 250, 33);
		contentPane.add(textFieldURL);
		textFieldURL.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setToolTipText("Repository username");
		lblUsername.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblUsername.setBounds(258, 234, 100, 35);
		contentPane.add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUsername.setToolTipText("Report the repository username");
		textFieldUsername.setFont(new Font("Cambria", Font.PLAIN, 14));
		textFieldUsername.setBounds(457, 237, 250, 33);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setToolTipText("Repository password");
		lblPassword.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblPassword.setBounds(258, 282, 100, 35);
		contentPane.add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPassword.setToolTipText("Report the repository password");
		textFieldPassword.setFont(new Font("Cambria", Font.PLAIN, 14));
		textFieldPassword.setBounds(457, 283, 250, 33);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		btnCancel.setFont(new Font("Cambria", Font.PLAIN, 18));
		btnCancel.setBounds(290, 345, 135, 23);
		contentPane.add(btnCancel);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldURL.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Report the repository", "Attention", JOptionPane.WARNING_MESSAGE);
				} else {
					JMMRepository repository = null;
					if (rdbtnGit.isSelected()) {
						repository = new JMMGit(textFieldURL.getText(), textFieldUsername.getText(), textFieldPassword.getText());
					}
					else {
						repository = new JMMSVN(textFieldURL.getText(), textFieldUsername.getText(), textFieldPassword.getText());
					}
					
					if (rdbtnCommit.isSelected()) {
						CommitScreen commitScreen = new CommitScreen(repository);
						commitScreen.setVisible(true);
						setVisible(false);
					} else if (rdbtnDate.isSelected()) {
						DateScreen dateScreen = new DateScreen(repository);
						dateScreen.setVisible(true);
						setVisible(false);
					}
				}
			}
		});
		btnNext.setFont(new Font("Cambria", Font.PLAIN, 18));
		btnNext.setBounds(491, 345, 135, 23);
		contentPane.add(btnNext);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(screenSize.width * 2 / 3, screenSize.height * 2 / 3));
		pack();
	}
}