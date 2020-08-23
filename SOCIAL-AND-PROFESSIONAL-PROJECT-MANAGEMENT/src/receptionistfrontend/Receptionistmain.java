package receptionistfrontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import frontend.Login;
import frontend.MainWindow;
import interfacePackage.Interface;
import objects.Log;
import shared.Password;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;

public class Receptionistmain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void Receptionistmain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receptionistmain frame = new Receptionistmain();
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
	public Receptionistmain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add doctor reservation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reservation R=new Reservation();
				R.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnNewButton.setBounds(201, 143, 357, 23);
		contentPane.add(btnNewButton);
		
		JButton btnConfirmReservation = new JButton("Confirm reservation");
		btnConfirmReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReservationConfirm r=new ReservationConfirm();
				r.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnConfirmReservation.setBounds(201, 218, 357, 23);
		contentPane.add(btnConfirmReservation);
		
		JButton btnFindARoom = new JButton("Find a room");
		btnFindARoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Table t=new Table();
				t.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnFindARoom.setBounds(201, 277, 357, 23);
		contentPane.add(btnFindARoom);
		
		JButton btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lg=new Login();
				lg.main(null);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton_1.setBounds(621, 30, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Ambulance");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ambulance a=new Ambulance();
				a.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton_2.setBounds(201, 362, 357, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Profile");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Password p=new Password();
				p.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton_3.setBounds(41, 46, 105, 38);
		contentPane.add(btnNewButton_3);
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Receptionistmain.class.getResource("/images/dribbble_2.gif")));
		lblNewLabel.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}

}
