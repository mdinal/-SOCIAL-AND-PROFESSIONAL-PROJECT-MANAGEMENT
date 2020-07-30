package receptionistfrontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import interfacePackage.Interface;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import objects.Doctor;
import objects.Patient;

import java.awt.Color;

public class Reservation extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField Name;
	private JTextField Age;
	private JTextField Phone;
	private JTable table;
	
	int DID;
	int PID;

	/**
	 * Launch the application.
	 */
	public static void Reservation() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservation frame = new Reservation();
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
	public Reservation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Doctor ID");
		lblNewLabel.setBounds(43, 137, 86, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 21, 584, 79);
		contentPane.add(scrollPane);
		
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(92, 328, 373, 14);
		contentPane.add(Error);
		
		table = new JTable();
		Object[] coumns = {"ID", "Name", "Number", "Specialist", "Fee"};
		DefaultTableModel model =new DefaultTableModel();
		model.setColumnIdentifiers(coumns);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		textField = new JTextField();
		textField.setBounds(161, 134, 250, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPa = new JLabel("Patient  Name");
		lblPa.setBounds(43, 182, 86, 14);
		contentPane.add(lblPa);
		
		Name = new JTextField();
		Name.setColumns(10);
		Name.setBounds(161, 179, 250, 20);
		contentPane.add(Name);
		
		JLabel lblNewLabel_1_1 = new JLabel("Age");
		lblNewLabel_1_1.setBounds(43, 224, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		Age = new JTextField();
		Age.setColumns(10);
		Age.setBounds(161, 221, 250, 20);
		contentPane.add(Age);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Contact ");
		lblNewLabel_1_1_1.setBounds(43, 273, 46, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		Phone = new JTextField();
		Phone.setColumns(10);
		Phone.setBounds(161, 270, 250, 20);
		contentPane.add(Phone);
		
		Object [] row=new Object[5];
		try {
			Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
			List <Doctor> D=lg.showdoctor();
			model.setRowCount(0);
			for(int i = 0; i < D.size(); i++) {
				row[0]=D.get(i).getID();
				row[1]=D.get(i).getName();
				row[2]=D.get(i).getPhone();
				row[3]=D.get(i).getSpecialist();


				model.addRow(row);
	        }
	}catch(Exception ed){
		System.out.print(ed);
	}  
		
		JButton btnNewButton = new JButton("Find");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Name.getText().isEmpty()) {
					Error.setText("Please enter a name");
				}else {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						Patient P=lg.findbyName(Name.getText());
						Age.setText(Integer.toString(P.getAge()));
						Phone.setText(Integer.toString(P.getPhone()));
						PID=P.getID();
				        
				}catch(Exception ed){
					System.out.print(ed);
				}  
				}
				
			}
		});
		btnNewButton.setBounds(448, 178, 68, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(PID==-1) {
					if(textField.getText().isEmpty()) {
						Error.setText("please enter Doctor ID ");
					}
					else if(Name.getText().isEmpty()) {
						Error.setText("please enter patient's name");
					}else if(Age.getText().isEmpty()) {
						Error.setText("please enter patient's Age");
					}else if(Phone.getText().isEmpty()) {
						Error.setText("please enter patient's phone number");
					}else if(Phone.getText().length()== 10) {
						Error.setText("please enter patient's phone not Valid");
					}else {
						try {
							Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
							Patient P=new Patient(Name.getText(),Integer.parseInt(Age.getText()),Integer.parseInt(Phone.getText()));
							boolean re=lg.AddPatient(P);
							if(re) {
								JOptionPane.showMessageDialog(null, "new Patient Saved Successfully");
								lg.findbyName(Name.getText());
								Name.setText("");
								Age.setText("");
								Phone.setText("");
								Error.setText("");
								
							}else {
								Error.setText("Patient not Saved");
							}
						}catch(Exception ed){
							System.out.print(ed);
						} 
					}
				}else if(textField.getText().isEmpty()) {
					Error.setText("please enter Doctor ID ");
				}else {
					
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						boolean re=lg.addReservation(Integer.parseInt(textField.getText()), PID);
						if(re) {
							JOptionPane.showMessageDialog(null, "reservation Saved Successfully");
							textField.setText("");
							PID=-1;
							Name.setText("");
							Age.setText("");
							Phone.setText("");
							Error.setText("");
							
						}else {
							Error.setText("reservation not Saved");
						}

					}catch(Exception ed){
						System.out.print(ed);
					}
				}
				
			}
		});
		btnNewButton_1.setBounds(138, 358, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Cancel");
		btnNewButton_1_1.setBounds(417, 358, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
		
		
		contentPane.add(btnNewButton_1_1);
		
		JButton Clear = new JButton("Clear Data");
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PID=-1;
				Name.setText("");
				Age.setText("");
				Phone.setText("");
				Error.setText("");
			}
		});
		Clear.setBounds(526, 178, 89, 23);
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Receptionistmain R=new Receptionistmain();
				R.setVisible(true);
				setVisible(false);
				dispose(); 
					
				}
			});
		contentPane.add(Clear);
		
		
	}
}
