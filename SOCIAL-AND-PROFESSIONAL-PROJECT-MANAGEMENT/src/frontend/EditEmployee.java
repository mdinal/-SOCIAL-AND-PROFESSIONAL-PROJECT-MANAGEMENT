package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.DateUtil;
import com.toedter.calendar.JDateChooser;

import interfacePackage.Interface;
import objects.Doctor;
import objects.Empolyee;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class EditEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Address;
	private JTextField Phone;
	private JTextField NIC;
	private JButton btnNewButton;
	private JButton btnDelete;
	private JButton btnCancle;

	/**
	 * Launch the application.
	 */
	public static void EditEmployee() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditEmployee frame = new EditEmployee();
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
	public EditEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(53, 32, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date of birth");
		lblNewLabel_1.setBounds(53, 70, 82, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setBounds(53, 108, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setBounds(53, 148, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("NIC");
		lblNewLabel_4.setBounds(53, 178, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Position");
		lblNewLabel_5.setBounds(53, 214, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		Name = new JTextField();
		Name.setBounds(156, 29, 191, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		Address = new JTextField();
		Address.setColumns(10);
		Address.setBounds(156, 105, 191, 20);
		contentPane.add(Address);
		
		Phone = new JTextField();
		Phone.setColumns(10);
		Phone.setBounds(156, 145, 191, 20);
		contentPane.add(Phone);
		
		NIC = new JTextField();
		NIC.setColumns(10);
		NIC.setBounds(156, 175, 191, 20);
		contentPane.add(NIC);
		
		JDateChooser Date = new JDateChooser();
		Date.setBounds(156, 70, 191, 20);
		contentPane.add(Date);
		
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(156, 255, 191, 14);
		contentPane.add(Error);
		
		btnNewButton = new JButton("Update");
		btnNewButton.setBounds(103, 264, 89, 23);
		contentPane.add(btnNewButton);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(243, 264, 89, 23);
		contentPane.add(btnDelete);
		
		btnCancle = new JButton("Cancle");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow MainWindow=new MainWindow();
				MainWindow.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnCancle.setBounds(391, 264, 89, 23);
		contentPane.add(btnCancle);
	
		
		JSpinner Position = new JSpinner();
		Position.setModel(new SpinnerListModel(new String[] {"receptionist", "nurse"}));
		Position.setBounds(156, 214, 194, 20);
		contentPane.add(Position);
		
		JButton btnNewButton = new JButton("Find");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Name.getText().isEmpty()) {
					Error.setText("please enter a Name");
				}else {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						Empolyee E=lg.EmployeeFind(Name.getText());
						Name.setText(E.getName());
						Phone.setText(Integer.toString(E.getPhone()));
						Position.setValue(E.getPosition());
						Address.setText(E.getAddress());
						NIC.setText(E.getNIC());
						java.util.Date dateset = new SimpleDateFormat("yyyy-MM-dd").parse(E.getDateofbirth());
						Date.setDate(dateset);
						
					}catch(Exception ed){
						System.out.print(ed);
					} 
				}
			}
		});
		btnNewButton.setBounds(387, 28, 89, 23);
		contentPane.add(btnNewButton);

	}

}
