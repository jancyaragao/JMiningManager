package jmm.gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ScreenTwo extends JFrame {

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
					ScreenTwo frame = new ScreenTwo();
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
	public ScreenTwo() {
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
