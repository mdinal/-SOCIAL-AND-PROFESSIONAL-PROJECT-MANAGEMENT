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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;

public class ResetPassword extends JFrame {

	private JPanel contentPane;
	private JTextField Email;
	private JTextField Code;

	/**
	 * Launch the application.
	 */
	public static void ResetPassword() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetPassword frame = new ResetPassword();
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
	public ResetPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(128, 147, 42, 22);
		contentPane.add(lblNewLabel);
		
		Email = new JTextField();
		Email.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Email.setBounds(255, 144, 207, 28);
		contentPane.add(Email);
		Email.setColumns(10);
		
		JLabel Error1 = new JLabel("");
		Error1.setForeground(Color.RED);
		Error1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Error1.setBounds(255, 223, 225, 22);
		contentPane.add(Error1);
		
		JLabel lblCode = new JLabel("Code");
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCode.setBounds(128, 362, 42, 22);
		contentPane.add(lblCode);
		
		Code = new JTextField();
		Code.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Code.setColumns(10);
		Code.setBounds(255, 359, 207, 28);
		contentPane.add(Code);
		
		JLabel Error2 = new JLabel("");
		Error2.setForeground(Color.RED);
		Error2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Error2.setBounds(255, 451, 225, 22);
		contentPane.add(Error2);
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");
					boolean re=lg.checkcode(Integer.parseInt(Code.getText()));
					if(re) {
						JOptionPane.showMessageDialog(null, " veried");
						changeP p=new changeP();
						p.setVisible(true);
						setVisible(false);
						dispose();
					}else {
						Error2.setText("Code do not match");
					}
				}catch (Exception se){
					  System.out.print(se);
				  }
			}
		});
		btnVerify.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVerify.setBounds(554, 358, 85, 31);
		contentPane.add(btnVerify);
		
		
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
					int re=lg.resetpassword(Email.getText());
					if(re>0) {
						JOptionPane.showMessageDialog(null, " Email send ");
						Error1.setText("");
					}else {
						Error1.setText("User not found");
					}
				}catch (Exception se){
					  System.out.print(se);
				  }
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(554, 143, 85, 31);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login log=new Login();
				log.main(null);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton_1.setBounds(629, 48, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(changeP.class.getResource("/images/dribbble_2.gif")));
		lblNewLabel_1.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel_1);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
