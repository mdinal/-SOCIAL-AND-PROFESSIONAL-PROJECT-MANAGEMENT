package shared;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import frontend.Login;
import interfacePackage.Interface;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class changeP extends JFrame {

	private JPanel contentPane;
	private JTextField Password1;
	private JTextField Password2;
	private JLabel lblReenterPassword;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void changeP() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					changeP frame = new changeP();
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
	public changeP() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(98, 183, 105, 20);
		contentPane.add(lblNewLabel);
		
		Password1 = new JTextField();
		Password1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Password1.setBounds(323, 180, 279, 26);
		contentPane.add(Password1);
		Password1.setColumns(10);
		
		Password2 = new JTextField();
		Password2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Password2.setColumns(10);
		Password2.setBounds(323, 359, 279, 26);
		contentPane.add(Password2);
		
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Error.setBounds(232, 436, 233, 20);
		contentPane.add(Error);
		
		lblReenterPassword = new JLabel("Reenter Password");
		lblReenterPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReenterPassword.setBounds(98, 362, 181, 20);
		contentPane.add(lblReenterPassword);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Password1.getText().equals(Password2.getText())) {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						boolean re=lg.chagepassowrd(Password2.getText());
						if(re) {
							JOptionPane.showMessageDialog(null, " Password  changed ");
							Error.setText("");
							Login log=new Login();
							log.main(null);
							setVisible(false);
							dispose();
						}else {
							Error.setText("password chage error");
						}
					}catch (Exception se){
						  System.out.print(se);
					  }
				}else {
					Error.setText("Password do not match");
				}
			}
		});
		btnNewButton.setBounds(590, 481, 89, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(changeP.class.getResource("/images/dribbble_2.gif")));
		lblNewLabel_1.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel_1);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
		
	
	}
}
