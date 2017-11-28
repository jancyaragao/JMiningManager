package jmm.gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ScreenOne extends JFrame {

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
					ScreenOne frame = new ScreenOne();
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
	public ScreenOne() {
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
		
		JRadioButton rdbtnGit = new JRadioButton("Git");
		rdbtnGit.setFont(new Font("Cambria", Font.PLAIN, 18));
		rdbtnGit.setBounds(344, 52, 55, 23);
		contentPane.add(rdbtnGit);
		
		JRadioButton rdbtnSvn = new JRadioButton("SVN");
		rdbtnSvn.setFont(new Font("Cambria", Font.PLAIN, 18));
		rdbtnSvn.setBounds(444, 52, 69, 23);
		contentPane.add(rdbtnSvn);
		
		ButtonGroup group1 = new ButtonGroup();
		group1.add(rdbtnSvn);
		group1.add(rdbtnGit);
		
		JLabel lblVersion = new JLabel("Which version control system?");
		lblVersion.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblVersion.setBounds(35, 47, 243, 33);
		contentPane.add(lblVersion);
		
		JLabel lblSearch = new JLabel("Search by:");
		lblSearch.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblSearch.setBounds(114, 110, 86, 23);
		contentPane.add(lblSearch);
		
		JRadioButton rdbtnDate = new JRadioButton("Date");
		rdbtnDate.setFont(new Font("Cambria", Font.PLAIN, 18));
		rdbtnDate.setBounds(334, 110, 61, 23);
		contentPane.add(rdbtnDate);
		
		JRadioButton rdbtnCommit = new JRadioButton("Commit");
		rdbtnCommit.setFont(new Font("Cambria", Font.PLAIN, 18));
		rdbtnCommit.setBounds(437, 110, 86, 23);
		contentPane.add(rdbtnCommit);
		
		ButtonGroup group2 = new ButtonGroup();
		group2.add(rdbtnDate);
		group2.add(rdbtnCommit);
		
		JLabel lblRepository = new JLabel("Repository:");
		lblRepository.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblRepository.setBounds(108, 165, 112, 35);
		contentPane.add(lblRepository);
		
		textFieldRepository = new JTextField();
		textFieldRepository.setFont(new Font("Cambria", Font.PLAIN, 14));
		textFieldRepository.setBounds(302, 168, 250, 33);
		contentPane.add(textFieldRepository);
		textFieldRepository.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Cambria", Font.PLAIN, 18));
		btnCancelar.setBounds(135, 274, 135, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAvanar = new JButton("Avan\u00E7ar");
		btnAvanar.setFont(new Font("Cambria", Font.PLAIN, 18));
		btnAvanar.setBounds(336, 274, 135, 23);
		contentPane.add(btnAvanar);
		
	}
	
}