package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.toedter.calendar.JTextFieldDateEditor;

import interfacePackage.Interface;
import objects.Empolyee;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JButton;
import org.jdatepicker.DateModel;
import java.util.Properties;
import javax.swing.JFormattedTextField.AbstractFormatter;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class AddEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Address;
	private JTextField Phone;
	private JTextField NIC;

	/**
	 * Launch the application.
	 */
	public static void AddEmployee() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployee frame = new AddEmployee();
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
	public AddEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(459, 27, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date of birth");
		lblNewLabel_1.setBounds(459, 65, 82, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setBounds(459, 103, 82, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setBounds(459, 143, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("NIC");
		lblNewLabel_4.setBounds(459, 173, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Position");
		lblNewLabel_5.setBounds(459, 209, 64, 14);
		contentPane.add(lblNewLabel_5);
		
		Name = new JTextField();
		Name.setBounds(562, 24, 191, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		Address = new JTextField();
		Address.setColumns(10);
		Address.setBounds(562, 100, 191, 20);
		contentPane.add(Address);
		
		Phone = new JTextField();
		Phone.setColumns(10);
		Phone.setBounds(562, 140, 191, 20);
		contentPane.add(Phone);
		
		NIC = new JTextField();
		NIC.setColumns(10);
		NIC.setBounds(562, 170, 191, 20);
		contentPane.add(NIC);
		
		JDateChooser Date = new JDateChooser();
		Date.setBounds(562, 65, 191, 20);
		contentPane.add(Date);
		
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(156, 255, 191, 14);
		contentPane.add(Error);
		
	
		
		JSpinner Position = new JSpinner();
		Position.setModel(new SpinnerListModel(new String[] {"receptionist", "nurse"}));
		Position.setBounds(562, 209, 194, 20);
		contentPane.add(Position);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBackground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainWindow MainWindow=new MainWindow();
				MainWindow.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnNewButton.setBounds(626, 255, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(Color.GREEN);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Name.getText().isEmpty()) {
					Error.setText("please enter the name");
				}else if(null==Date.getDate()) {
					Error.setText("please enter Date of Birth");
				}else if(Address.getText().isEmpty()) {
					Error.setText("please enter Address");
				}else if (Phone.getText().isEmpty()) {
					Error.setText("please enter phone number");
				}else if(!(Phone.getText().toString().length()==11)) {
					Error.setText("please enter vaild phone number");
				}else if(!(NIC.getText().contains("V") || NIC.getText().contains("V"))){
					Error.setText("please enter vaild NIC number");
				}else {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
						String StringDate=dateFormat.format(Date.getDate());  
						Empolyee E=new Empolyee(Name.getText(),StringDate,Address.getText(),Integer.parseInt(Phone.getText()),NIC.getText(),Position.getValue().toString());
						boolean re=lg.AddEmployee(E);
						if(re==true) {
							JOptionPane.showMessageDialog(null, "Employee Saved Successfully");
							Name.setText("");
							Address.setText("");
							Phone.setText("");
							NIC.setText("");
							Error.setText("");
							
						}else
						{
							Error.setText("Employee not Saved");
							
						}
					}catch(Exception ed){
						System.out.print(ed);
					}  
					}
				}
			}
		);
		btnSave.setBounds(507, 255, 89, 23);
		contentPane.add(btnSave);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(AddEmployee.class.getResource("/images/160606__gif.gif")));
		lblNewLabel_6.setBounds(0, 0, 784, 561);
		contentPane.add(lblNewLabel_6);
		
		

	}
}
