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
		lblNewLabel.setBounds(531, 26, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(531, 64, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone");
		lblNewLabel_2.setBounds(531, 106, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setBounds(531, 148, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Specialist ");
		lblNewLabel_4.setBounds(531, 189, 70, 14);
		contentPane.add(lblNewLabel_4);
		
		Name = new JTextField();
		Name.setBounds(622, 23, 138, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(622, 61, 138, 20);
		contentPane.add(Email);
		
		Phone = new JTextField();
		Phone.setColumns(10);
		Phone.setBounds(622, 103, 138, 20);
		contentPane.add(Phone);
		
		Address = new JTextField();
		Address.setColumns(10);
		Address.setBounds(622, 145, 138, 20);
		contentPane.add(Address);
		
		Specialist = new JTextField();
		Specialist.setColumns(10);
		Specialist.setBounds(622, 186, 138, 20);
		contentPane.add(Specialist);
		
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(179, 232, 250, 14);
		contentPane.add(Error);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.setBackground(Color.RED);
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow MainWindow=new MainWindow();
				MainWindow.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		Cancel.setBounds(660, 232, 89, 23);
		contentPane.add(Cancel);
		
		JButton Save = new JButton("Save");
		Save.setBackground(Color.GREEN);
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
		Save.setBounds(561, 232, 89, 23);
		contentPane.add(Save);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(DoctorAdd.class.getResource("/images/33ba21ccda561739ab950d66e5616b82.gif")));
		lblNewLabel_5.setBounds(0, 0, 784, 561);
		contentPane.add(lblNewLabel_5);

	}
}
