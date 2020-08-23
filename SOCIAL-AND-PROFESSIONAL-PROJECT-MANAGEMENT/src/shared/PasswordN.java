package shared;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import interfacePackage.Interface;
import nurse.Nurse;
import objects.Log;
import receptionistfrontend.Receptionistmain;

public class PasswordN extends JFrame {

	private JPanel contentPane;
	private JTextField Username;
	private JTextField Password;
	private JTextField Repassword;
	private String ID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordN frame = new PasswordN();
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
	public PasswordN() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(187, 128, 84, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(187, 252, 78, 20);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("retype password");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(187, 387, 135, 20);
		contentPane.add(lblNewLabel_1_1_1);
		
		Username = new JTextField();
		Username.setBounds(405, 128, 259, 29);
		contentPane.add(Username);
		Username.setColumns(10);
		
		Password = new JTextField();
		Password.setColumns(10);
		Password.setBounds(405, 258, 259, 29);
		contentPane.add(Password);
		
		Repassword = new JTextField();
		Repassword.setColumns(10);
		Repassword.setBounds(405, 393, 259, 29);
		contentPane.add(Repassword);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Nurse n=new Nurse();
				 n.setVisible(true);
				 setVisible(false);
				 dispose();
						}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(54, 43, 94, 29);
		contentPane.add(btnNewButton);
		
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setFont(new Font("Tahoma", Font.BOLD, 16));
		Error.setBounds(224, 445, 259, 20);
		contentPane.add(Error);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Password.getText().isEmpty()) {
					if(Username.getText().isEmpty()) {
						Error.setText("Username empty ");
					} else {
						try {
							Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//"); 
							boolean re=lg.updateEbyName(ID, Username.getText());
							if(re) {
								JOptionPane.showMessageDialog(null, " update Successfully");
							}else {
								Error.setText("Update Errror");
							}
						}catch(Exception e1) {
							System.out.print(e1);
						}
					}
				}else if(Password.getText().compareTo(Repassword.getText())==0){
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//"); 
						boolean re=lg.updateEbyNamePassword(ID, Username.getText(), Password.getText());
						if(re) {
							Error.setText("");
							JOptionPane.showMessageDialog(null, " update Successfully");
						}else {
							Error.setText("Update Errror");
						}
					}catch(Exception e2) {
						System.out.print(e2);
					}
				}else {
					Error.setText("Password Do not match");
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSave.setBounds(570, 485, 94, 29);
		contentPane.add(btnSave);
		
		try {
			Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
			Log D=lg.logeduser();
			Username.setText(D.getE().getName());
			
			ID=D.getID();

		}catch(Exception ed){
			System.out.print(ed);
		} 
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Password.class.getResource("/images/source.gif")));
		lblNewLabel_1.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel_1);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}

}
