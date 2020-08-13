package receptionistfrontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfacePackage.Interface;
import objects.Reservation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class ReservationConfirm extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	String ID=null;
	/**
	 * Launch the application.
	 */
	public static void ReservationConfirm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationConfirm frame = new ReservationConfirm();
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
	public ReservationConfirm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(146, 153, 74, 29);
		contentPane.add(lblNewLabel);
		JLabel Error = new JLabel("");
		Error.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Error.setForeground(Color.RED);
		Error.setBounds(256, 448, 319, 29);
		contentPane.add(Error);
		
		JLabel Doctor = new JLabel("");
		Doctor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Doctor.setBounds(404, 281, 193, 29);
		contentPane.add(Doctor);
		
		JLabel Phone = new JLabel("");
		Phone.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Phone.setBounds(404, 337, 225, 23);
		contentPane.add(Phone);
		
		JLabel Fee = new JLabel("");
		Fee.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Fee.setBounds(404, 218, 193, 29);
		contentPane.add(Fee);
		
		Name = new JTextField();
		Name.setBounds(404, 160, 171, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Number");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(146, 389, 118, 23);
		contentPane.add(lblNewLabel_4);
		
		JLabel Number = new JLabel("");
		Number.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Number.setBounds(404, 389, 193, 23);
		contentPane.add(Number);
		JLabel lblNewLabel_1 = new JLabel("fee :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(146, 214, 145, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Doctor's Name :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(146, 277, 167, 23);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(146, 337, 118, 23);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Receptionistmain RM=new Receptionistmain();
				RM.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnNewButton.setBounds(296, 508, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Confirm");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ID==null) {
					Error.setText("ERROR Can't Confrim");
				}else {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						boolean res=lg.confrmresavation(ID);
						if(res) {
							JOptionPane.showMessageDialog(null, "reservation Confrimed Successfully");
							Doctor.setText("");
							Phone.setText("");
							Number.setText("");
							ID=null;
						}else {
							Error.setText("reservation Confrimed ERROR");
						}
					}catch(Exception ed){
						System.out.print(ed);
					} 
				}
			}
			
		});
		btnNewButton_1.setBounds(452, 508, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		JButton btnNewButton_2 = new JButton("Find");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
					Reservation R=lg.findreservation(Name.getText());
					if(R.getDID()==null) {
						Error.setText("no reservation found");
					}else {
						Error.setText("");
						Doctor.setText(R.getDName());
						Phone.setText(R.getPhone());
						Number.setText(R.getNumber());
						ID=R.getID();
						
					}
					
				}catch(Exception ed){
					System.out.print(ed);
				} 
			}
		});
		btnNewButton_2.setBounds(615, 159, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(ReservationConfirm.class.getResource("/images/hospital.gif")));
		lblNewLabel_5.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel_5);
		setUndecorated(true);
		setLocationRelativeTo(null);
		

		

	}
}
