package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfacePackage.Interface;
import objects.Doctor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DoctorAdd extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Email;
	private JTextField Phone;
	private JTextField Address;
	private JTextField Specialist;

	/**
	 * Launch the application.
	 */
	public static void DoctorAdd() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorAdd frame = new DoctorAdd();
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
	public DoctorAdd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(265, 117, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(265, 192, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone");
		lblNewLabel_2.setBounds(265, 258, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setBounds(265, 318, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Specialist ");
		lblNewLabel_4.setBounds(265, 376, 70, 14);
		contentPane.add(lblNewLabel_4);
		
		Name = new JTextField();
		Name.setBounds(356, 114, 302, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(356, 189, 302, 20);
		contentPane.add(Email);
		
		Phone = new JTextField();
		Phone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c) ||(c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		Phone.setColumns(10);
		Phone.setBounds(356, 255, 302, 20);
		contentPane.add(Phone);
		
		Address = new JTextField();
		Address.setColumns(10);
		Address.setBounds(356, 315, 302, 20);
		contentPane.add(Address);
		
		Specialist = new JTextField();
		Specialist.setColumns(10);
		Specialist.setBounds(356, 373, 302, 20);
		contentPane.add(Specialist);
		
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(265, 416, 250, 14);
		contentPane.add(Error);
		
		JButton Cancel = new JButton("Back");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow MainWindow=new MainWindow();
				MainWindow.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		Cancel.setBounds(265, 457, 89, 23);
		contentPane.add(Cancel);
		
		JButton Save = new JButton("Save");
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!(Email.getText().contains("@")||Email.getText().contains("."))) {
					Error.setText("Plese enter vaild email");
				}else if(!(Phone.getText().length()==10)) {
					Error.setText("Plese enter vaild Phone number");
				}
				else {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						Doctor D=new Doctor(Name.getText(),Email.getText(),Integer.parseInt(Phone.getText()),Address.getText(),Specialist.getText());
						boolean re=lg.addDoctor(D);
						if(re) {
							JOptionPane.showMessageDialog(null, "Doctor Saved Successfully");
							Name.setText("");
							Email.setText("");
							Phone.setText("");
							Address.setText("");
							Specialist.setText("");
							Error.setText("");
							
						}else {
							Error.setText("Doctor not Saved");
						}
					}catch(Exception ed){
						System.out.print(ed);
					}  
				}
					
				
			}
		});
		Save.setBounds(423, 457, 89, 23);
		contentPane.add(Save);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(DoctorAdd.class.getResource("/images/HandmadeUltimateHoneybee-size_restricted.gif")));
		lblNewLabel_5.setBounds(0, 0, 800,600);
		contentPane.add(lblNewLabel_5);
		
		setUndecorated(true);
		setLocationRelativeTo(null);

	}
}
