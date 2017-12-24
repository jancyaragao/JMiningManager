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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import jmm.common.JMMRepository;
import jmm.common.JMMUtil;
import jmm.model.Author;
import jmm.model.Change;
import jmm.model.ChangedFile;

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
					DateScreen frame = new DateScreen(null);
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
	public DateScreen(JMMRepository repository) {
		setResizable(false);
		setFont(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("JMM - Java Mining Manager");
		setBounds(325, 125, 640, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon logo = new ImageIcon("src/image/jmm_logo.png");
		setIconImage(logo.getImage());

		setLocationRelativeTo(null);

		JLabel lblInitial = new JLabel("Initial Date:*");
		lblInitial.setHorizontalAlignment(SwingConstants.CENTER);
		lblInitial.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblInitial.setBounds(58, 56, 100, 33);
		contentPane.add(lblInitial);

		JDateChooser dateChooserInitial = new JDateChooser();
		dateChooserInitial.getCalendarButton().setFont(new Font("Cambria", Font.PLAIN, 18));
		dateChooserInitial.setToolTipText("Report the initial date");
		dateChooserInitial.setBounds(164, 56, 135, 33);
		contentPane.add(dateChooserInitial);

		JLabel lblFinal = new JLabel("Final Date:");
		lblFinal.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinal.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblFinal.setBounds(344, 56, 96, 33);
		contentPane.add(lblFinal);

		JDateChooser dateChooserFinal = new JDateChooser();
		dateChooserFinal.getCalendarButton().setFont(new Font("Cambria", Font.PLAIN, 18));
		dateChooserFinal.setToolTipText("Report the final date");
		dateChooserFinal.setBounds(450, 56, 135, 33);
		contentPane.add(dateChooserFinal);

		JLabel lblQueries = new JLabel("Queries:*");
		lblQueries.setHorizontalAlignment(SwingConstants.CENTER);
		lblQueries.setToolTipText("Queries to be searched");
		lblQueries.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblQueries.setBounds(69, 152, 75, 33);
		contentPane.add(lblQueries);

		JCheckBox chckbxForChanges = new JCheckBox("Changes");
		chckbxForChanges.setSelected(true);
		chckbxForChanges.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxForChanges.setToolTipText("Queries for change");
		chckbxForChanges.setFont(new Font("Cambria", Font.PLAIN, 18));
		chckbxForChanges.setBounds(164, 152, 125, 33);
		contentPane.add(chckbxForChanges);

		JCheckBox chckbxForAuthor = new JCheckBox("Author");
		chckbxForAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxForAuthor.setToolTipText("Queries for author");
		chckbxForAuthor.setFont(new Font("Cambria", Font.PLAIN, 18));
		chckbxForAuthor.setBounds(320, 152, 120, 33);
		contentPane.add(chckbxForAuthor);

		JCheckBox chckbxForFiles = new JCheckBox("Files");
		chckbxForFiles.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxForFiles.setToolTipText("Queries for files");
		chckbxForFiles.setFont(new Font("Cambria", Font.PLAIN, 18));
		chckbxForFiles.setBounds(466, 152, 100, 33);
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
		btnBack.setBounds(162, 253, 135, 23);
		contentPane.add(btnBack);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (dateChooserInitial.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Report the initial commit", "Attention",
							JOptionPane.WARNING_MESSAGE);
				} else {

					if (chckbxForChanges.isSelected()) {
						List<Change> changes = repository.changesBetweenDates(dateChooserInitial.getDate(),
								dateChooserFinal.getDate());
						ResultScreen rs = new ResultScreen();
						rs.resultScreenListChange(changes);
						rs.setVisible(true);
					} else if (chckbxForAuthor.isSelected()) {
						List<Author> authors = repository.authorsBetweenDates(dateChooserInitial.getDate(),
								dateChooserFinal.getDate());
						ResultScreen rs = new ResultScreen();
						rs.resultScreenListAuthor(JMMUtil.groupByAuthor(authors));
						rs.setVisible(true);
					} else if (chckbxForFiles.isSelected()) {
						List<ChangedFile> changedsFiles = repository.filesBetweenDates(dateChooserInitial.getDate(),
								dateChooserFinal.getDate());
						ResultScreen rs = new ResultScreen();
						rs.resultScreenListFiles(changedsFiles);
						rs.setVisible(true);
					}
				}
			}
		});
		btnSearch.setFont(new Font("Cambria", Font.PLAIN, 18));
		btnSearch.setBounds(363, 253, 135, 23);
		contentPane.add(btnSearch);

	}
}
