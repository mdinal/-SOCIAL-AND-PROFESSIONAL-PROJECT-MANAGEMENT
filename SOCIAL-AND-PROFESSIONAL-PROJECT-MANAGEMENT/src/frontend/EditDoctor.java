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

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

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
	private JLabel lblNewLabel_5;
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
		lblNewLabel.setBounds(457, 28, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(457, 75, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone");
		lblNewLabel_2.setBounds(457, 117, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setBounds(457, 159, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Specialist ");
		lblNewLabel_4.setBounds(457, 200, 70, 14);
		contentPane.add(lblNewLabel_4);
		
		Name = new JTextField();
		Name.setBounds(548, 25, 138, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(548, 72, 138, 20);
		contentPane.add(Email);
		
		Phone = new JTextField();
		Phone.setColumns(10);
		Phone.setBounds(548, 114, 138, 20);
		contentPane.add(Phone);
		
		Address = new JTextField();
		Address.setColumns(10);
		Address.setBounds(548, 156, 138, 20);
		contentPane.add(Address);
		
		Specialist = new JTextField();
		Specialist.setColumns(10);
		Specialist.setBounds(548, 197, 138, 20);
		contentPane.add(Specialist);
		
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(179, 232, 250, 14);
		contentPane.add(Error);
		
		btnNewButton = new JButton("Update");
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setBounds(457, 244, 89, 23);
		contentPane.add(btnNewButton);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.ORANGE);
		btnDelete.setBounds(556, 244, 89, 23);
		contentPane.add(btnDelete);
		
		btnCancle = new JButton("Cancle");
		btnCancle.setBackground(Color.RED);
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow MainWindow=new MainWindow();
				MainWindow.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnCancle.setBounds(655, 244, 89, 23);
		contentPane.add(btnCancle);
		
		btnFind = new JButton("Find");
		btnFind.setBackground(Color.GREEN);
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
		btnFind.setBounds(694, 25, 80, 23);
		contentPane.add(btnFind);
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(EditDoctor.class.getResource("/images/hospital_icon.gif")));
		lblNewLabel_5.setBounds(0, 0, 784, 561);
		contentPane.add(lblNewLabel_5);
		
	}
}
