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
		setBounds(100, 100, 503, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(68, 23, 54, 14);
		contentPane.add(lblNewLabel);
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(116, 225, 185, 14);
		contentPane.add(Error);
		
		JLabel Doctor = new JLabel("");
		Doctor.setBounds(175, 111, 138, 14);
		contentPane.add(Doctor);
		
		JLabel Phone = new JLabel("");
		Phone.setBounds(175, 143, 160, 14);
		contentPane.add(Phone);
		
		JLabel Fee = new JLabel("");
		Fee.setBounds(175, 76, 185, 14);
		contentPane.add(Fee);
		
		Name = new JTextField();
		Name.setBounds(175, 20, 171, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Number");
		lblNewLabel_4.setBounds(68, 168, 89, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel Number = new JLabel("");
		Number.setBounds(185, 168, 89, 14);
		contentPane.add(Number);
		JLabel lblNewLabel_1 = new JLabel("fee :");
		lblNewLabel_1.setBounds(68, 76, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Doctor's Name :");
		lblNewLabel_2.setBounds(68, 111, 111, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone :");
		lblNewLabel_3.setBounds(68, 143, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Cancle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Receptionistmain RM=new Receptionistmain();
				RM.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnNewButton.setBounds(68, 254, 89, 23);
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
		btnNewButton_1.setBounds(224, 254, 89, 23);
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
		btnNewButton_2.setBounds(375, 19, 89, 23);
		contentPane.add(btnNewButton_2);
		

		

	}
}
