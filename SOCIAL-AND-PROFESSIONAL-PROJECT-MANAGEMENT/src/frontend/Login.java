package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import guidoctor.DoctorMain;
import interfacePackage.Interface;
import nurse.Nurse;
import objects.Log;
import receptionistfrontend.Receptionistmain;
import shared.ResetPassword;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(144, 270, 82, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(144, 316, 82, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel ErrorMsg = new JLabel("");
		ErrorMsg.setForeground(Color.RED);
		ErrorMsg.setBounds(248, 359, 378, 14);
		contentPane.add(ErrorMsg);
		
		Username = new JTextField();
		Username.setBounds(248, 266, 349, 20);
		contentPane.add(Username);
		Username.setColumns(10);
		
		Password = new JTextField();
		Password.setBounds(248, 312, 349, 20);
		contentPane.add(Password);
		Password.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{  
					Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
					 Log res=lg.login(Username.getText(),Password.getText());
					 if(res==null) {
						 ErrorMsg.setText("invalid password or username");
					 }else {
						 int re=Integer.parseInt(res.getReturn());
						 if(re==1) {
							 	MainWindow MainWindow=new MainWindow();
								MainWindow.setVisible(true);
								setVisible(false);
								dispose(); 
						 }else if(re==2) {
							 String type=res.getE().getPosition();
							 if(type.compareTo("receptionist")==0) {
								 Receptionistmain RM=new Receptionistmain();
								 RM.setVisible(true);
								 setVisible(false);
								 dispose(); 
							 }else if(type.compareTo("nurse")==0) {
								 Nurse n=new Nurse();
								 n.setVisible(true);
								 setVisible(false);
								 dispose();
							 }

						 }else if(re==3){
							 DoctorMain DM=new DoctorMain();
							 DM.setVisible(true);
							 setVisible(false);
							 dispose(); 
						 }
					 }
					 
				} 
				catch(Exception ae) 
		        { 
		            System.out.println(ae); 
		        } 
				
			}
		});
		btnNewButton.setBounds(412, 398, 160, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Forgot Password ");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblNewLabel_3.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_3.setForeground(Color.black);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ResetPassword rp=new ResetPassword();
				rp.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		lblNewLabel_3.setBounds(248, 402, 121, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/images/hospital_icon.gif")));
		lblNewLabel_2.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel_2);
		
		
		
		
	}
}
