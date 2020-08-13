package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import interfacePackage.Interface;
import objects.Doctor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;

public class EditDoctor extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Email;
	private JTextField Phone;
	private JTextField Address;
	private JTextField Specialist;
	private JButton btnNewButton;
	private JButton btnDelete;
	private JButton btnCancle;
	private JButton btnFind;
	int ID=-1;
	/**
	 * Launch the application.
	 */
	public static void EditDoctor() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditDoctor frame = new EditDoctor();
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
	public EditDoctor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(200, 109, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(200, 175, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone");
		lblNewLabel_2.setBounds(200, 243, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setBounds(200, 309, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Specialist ");
		lblNewLabel_4.setBounds(200, 375, 70, 14);
		contentPane.add(lblNewLabel_4);
		
		Name = new JTextField();
		Name.setBounds(291, 106, 263, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(291, 172, 263, 20);
		contentPane.add(Email);
		
		Phone = new JTextField();
		Phone.setColumns(10);
		Phone.setBounds(291, 240, 263, 20);
		contentPane.add(Phone);
		
		Address = new JTextField();
		Address.setColumns(10);
		Address.setBounds(291, 306, 263, 20);
		contentPane.add(Address);
		
		Specialist = new JTextField();
		Specialist.setColumns(10);
		Specialist.setBounds(291, 372, 263, 20);
		contentPane.add(Specialist);
		
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(255, 440, 315, 14);
		contentPane.add(Error);
		
		btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Name.getText().isEmpty()) {
					Error.setText("Please enter a name");
				}else if(Email.getText().isEmpty()) {
					Error.setText("please enter email");
				}else if(!(Email.getText().contains("@") && Email.getText().contains("."))) {
					Error.setText("please enter Vaild email");
				}else if(Phone.getText().isEmpty()) {
					Error.setText("please enter phone number");
				}else if(Phone.getText().length()==10) {
					Error.setText("please enter vaild phone number");
				}else if(Specialist.getText().isEmpty()) {
					Error.setText("Specialist can't be empty");
				}else {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						Doctor D=new Doctor(Name.getText(),Email.getText(),Integer.parseInt(Phone.getText()),Address.getText(),Specialist.getText());
						D.setID(ID);
						boolean res=lg.editDoctor(D);
						if(res) {
							JOptionPane.showMessageDialog(null, "Doctor updated Successfully");
						}else {
							Error.setText("ERROR UPDATEING");
						}
					}catch(Exception ed){
						System.out.print(ed);
					} 
				}
			}
		});
		btnNewButton.setBounds(215, 510, 89, 23);
		contentPane.add(btnNewButton);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ID==-1) {
					Error.setText("Find Doctor ");
				}else {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						boolean res=lg.deleteDoctor(ID);
						if(res) {
							JOptionPane.showMessageDialog(null, "Doctor Deleted Successfully");
							Name.setText("");
							Email.setText("");
							Phone.setText("");
							Address.setText("");
							Specialist.setText("");
							ID=-1;
							Error.setText("");
						}else {
							Error.setText("Error deleteing");
						}
					}catch(Exception ed){
						System.out.print(ed);
					} 
				}
			}
		});
		btnDelete.setBounds(355, 510, 89, 23);
		contentPane.add(btnDelete);
		
		btnCancle = new JButton("Back");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow MainWindow=new MainWindow();
				MainWindow.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnCancle.setBounds(503, 510, 89, 23);
		contentPane.add(btnCancle);
		
		btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Name.getText().isEmpty()) {
					Error.setText("please enter a Name");
				}else {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						Doctor D=lg.DoctorFind(Name.getText());
						ID=D.getID();
						Name.setText(D.getName());
						Phone.setText(Integer.toString(D.getPhone()));
						Email.setText(D.getEmail());
						Address.setText(D.getAddress());
						Specialist.setText(D.getSpecialist());
						System.out.print(ID);
						

					}catch(Exception ed){
						System.out.print(ed);
					} 
				}
			}
		});
		btnFind.setBounds(589, 105, 89, 23);
		contentPane.add(btnFind);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(DoctorAdd.class.getResource("/images/HandmadeUltimateHoneybee-size_restricted.gif")));
		lblNewLabel_5.setBounds(0, 0, 800,600);
		contentPane.add(lblNewLabel_5);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
