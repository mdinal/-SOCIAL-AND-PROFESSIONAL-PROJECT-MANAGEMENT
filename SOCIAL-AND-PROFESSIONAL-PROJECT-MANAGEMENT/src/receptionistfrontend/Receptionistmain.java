package receptionistfrontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import frontend.Login;
import frontend.MainWindow;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 450, 300);
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
		btnNewButton.setBounds(112, 48, 188, 23);
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
		btnConfirmReservation.setBounds(112, 94, 188, 23);
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
		btnFindARoom.setBounds(137, 143, 146, 23);
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
		btnNewButton_1.setBounds(313, 227, 89, 23);
		contentPane.add(btnNewButton_1);
	}

}
