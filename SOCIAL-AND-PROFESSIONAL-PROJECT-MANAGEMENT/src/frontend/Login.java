package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfacePackage.Interface;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField Username;
	private JTextField Password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(83, 57, 82, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(83, 103, 82, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel ErrorMsg = new JLabel("");
		ErrorMsg.setForeground(Color.RED);
		ErrorMsg.setBounds(109, 144, 208, 14);
		contentPane.add(ErrorMsg);
		
		Username = new JTextField();
		Username.setBounds(189, 54, 128, 20);
		contentPane.add(Username);
		Username.setColumns(10);
		
		Password = new JTextField();
		Password.setBounds(189, 100, 128, 20);
		contentPane.add(Password);
		Password.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{  
					Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
					 int res=lg.login(Username.getText(),Password.getText());
					 if(res==1) {
						 	MainWindow MainWindow=new MainWindow();
							MainWindow.setVisible(true);
							setVisible(false);
							dispose(); 
					 }else {
						 ErrorMsg.setText("invalid password or username");
					 }
				} 
				catch(Exception ae) 
		        { 
		            System.out.println(ae); 
		        } 
				
			}
		});
		btnNewButton.setBounds(293, 186, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
