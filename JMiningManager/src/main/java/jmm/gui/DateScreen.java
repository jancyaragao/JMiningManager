package jmm.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class DateScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DateScreen frame = new DateScreen();
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
	public DateScreen() {
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
		
		JLabel lblInitial = new JLabel("Initial Date:*");
		lblInitial.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblInitial.setBounds(46, 65, 100, 33);
		contentPane.add(lblInitial);
		
		JDateChooser dateChooserInitial = new JDateChooser();
		dateChooserInitial.setBounds(152, 65, 135, 33);
		contentPane.add(dateChooserInitial);
		
		JLabel lblFinal = new JLabel("Final Date:");
		lblFinal.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblFinal.setBounds(332, 65, 96, 33);
		contentPane.add(lblFinal);
		
		JDateChooser dateChooserFinal = new JDateChooser();
		dateChooserFinal.setBounds(438, 65, 135, 33);
		contentPane.add(dateChooserFinal);
		
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
			public void actionPerformed(ActionEvent e) {
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
				if (dateChooserInitial.getDate() == null) {
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
