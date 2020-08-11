package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Position;

import com.toedter.calendar.DateUtil;
import com.toedter.calendar.JDateChooser;

import interfacePackage.Interface;
import objects.Doctor;
import objects.Empolyee;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;

public class EditEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Address;
	private JTextField Phone;
	private JTextField NIC;
	private JButton btnNewButton;
	private JButton btnDelete;
	private JButton btnCancle;
	int ID=-1;

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
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel.setBounds(456, 33, 65, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date of birth");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(456, 95, 93, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(456, 133, 82, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(456, 173, 65, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("NIC");
		lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(456, 213, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Position");
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(456, 249, 65, 14);
		contentPane.add(lblNewLabel_5);
		
		Name = new JTextField();
		Name.setBounds(559, 30, 191, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		Address = new JTextField();
		Address.setColumns(10);
		Address.setBounds(559, 130, 191, 20);
		contentPane.add(Address);
		
		Phone = new JTextField();
		Phone.setColumns(10);
		Phone.setBounds(559, 170, 191, 20);
		contentPane.add(Phone);
		
		NIC = new JTextField();
		NIC.setColumns(10);
		NIC.setBounds(559, 210, 191, 20);
		contentPane.add(NIC);
		
		JDateChooser Date = new JDateChooser();
		Date.setBounds(559, 95, 191, 20);
		contentPane.add(Date);
		
		JLabel Error = new JLabel("");
		Error.setFont(new Font("Calibri", Font.PLAIN, 15));
		Error.setForeground(Color.RED);
		Error.setBounds(512, 285, 191, 14);
		contentPane.add(Error);
		
		JSpinner Position = new JSpinner();
		Position.setModel(new SpinnerListModel(new String[] {"receptionist", "nurse"}));
		Position.setBounds(559, 249, 194, 20);
		contentPane.add(Position);
		
		btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Name.getText().isEmpty()) {
					Error.setText("Enter name");
				}else if(Address.getText().isEmpty()) {
					Error.setText("Enter Address");
				}else if(Phone.getText().isEmpty()) {
					Error.setText("Enter Phone");
				}else if(!(Phone.getText().length()==10)) {
					Error.setText("Enter vaild phone number");
				}else if(NIC.getText().isEmpty()) {
					Error.setText("Enter NIC number");
				}else if(!(NIC.getText().contains("V")||NIC.getText().contains("V"))) {
					Error.setText("Enter vaild NIC number");
				}else {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
						String StringDate=dateFormat.format(Date.getDate());  
						Empolyee E=new Empolyee(Name.getText(),StringDate,Address.getText(),Integer.parseInt(Phone.getText()),NIC.getText(),Position.getValue().toString());
						E.setID(ID);
						boolean re=lg.editEMP(E);
						if(re) {
							JOptionPane.showMessageDialog(null, "Employee updated Successfully");
						}else {
							Error.setText("ERROR UPDATEING");
						}
					}catch(Exception ed){
						System.out.print(ed);
					} 
				}
			}
		});
		btnNewButton.setBounds(103, 311, 89, 23);
		contentPane.add(btnNewButton);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ID==-1) {
					Error.setText("Find Employee ");
				}else {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						boolean res=lg.deleteEMP(ID);
						if(res) {
							JOptionPane.showMessageDialog(null, "Employee Deleted Successfully");
							Name.setText("");
							Phone.setText("");
							Address.setText("");
							NIC.setText("");
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
		btnDelete.setBounds(243, 311, 89, 23);
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
		btnCancle.setBounds(391, 311, 89, 23);
		contentPane.add(btnCancle);
	
		

		
		JButton btnNewButton = new JButton("Find");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Name.getText().isEmpty()) {
					Error.setText("please enter a Name");
				}else {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						Empolyee E=lg.EmployeeFind(Name.getText());
						ID=E.getID();
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
		btnNewButton.setBounds(614, 61, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(EditEmployee.class.getResource("/images/unimed-dribbble-gif-01.gif")));
		lblNewLabel_6.setBounds(0, 0, 784, 561);
		contentPane.add(lblNewLabel_6);

	}
}
