package guidoctor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import frontend.Login;
import interfacePackage.Interface;
import objects.Log;
import objects.Patient;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;

public class DoctorList extends JFrame {

	private JPanel contentPane;
	public JLabel RoomID;
	private JTable table;
	private JTextField ID;
	private JTextField Details;

	/**
	 * Launch the application.
	 */
	public static void DoctorList() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorList frame = new DoctorList();
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
	public DoctorList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		RoomID = new JLabel("");
		RoomID.setBounds(10, 15, 46, 14);
		contentPane.add(RoomID);
		
		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
					boolean re=lg.DowngradeRoom(RoomID.getText());
					if(re) {
						Login login=new Login();
						login.main(null);
						setVisible(false);
						dispose();
					}
				}catch(Exception ed){
					System.out.print(ed);
				} 
				
			}
		});
		btnNewButton.setBounds(335, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 53, 381, 75);
		contentPane.add(scrollPane);
		
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(96, 273, 215, 14);
		contentPane.add(Error);
		
		table = new JTable();
		Object[] coumns = {"ID", "Name", "Number","Age"};
		DefaultTableModel model =new DefaultTableModel();
		model.setColumnIdentifiers(coumns);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(50, 159, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Details");
		lblNewLabel_1.setBounds(50, 197, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		ID = new JTextField();
		ID.setBounds(139, 156, 86, 20);
		contentPane.add(ID);
		ID.setColumns(10);
		
		Details = new JTextField();
		Details.setBounds(139, 194, 263, 68);
		contentPane.add(Details);
		Details.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Find");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");
					Patient P=lg.findP(ID.getText());	
					if(P==null) {
						Error.setText("please check the ID");
					}else {
						Details.setText(P.getDetails());
					}
				}catch(Exception ed){
					System.out.print(ed);
				} 
			}
		});
		
		btnNewButton_1.setBounds(235, 155, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
					boolean re=lg.updatepatient(ID.getText(),Details.getText() );
					if(!re) {
						Error.setText("Error updating");
					}else {
						Object [] row=new Object[4];
						try {
							List<Patient> list= new ArrayList<Patient>();
							Interface lg1=(Interface)Naming.lookup("rmi://localhost:6080//");  
							list=lg.plist();
							model.setRowCount(0);
							for(Patient P:list) {
								row[0]=P.getID();
								row[1]=P.getName();
								row[2]=P.getNumber();
								row[3]=P.getAge();
								model.addRow(row);
							}
							ID.setText("");
							Details.setText("");
						}catch(Exception ed){
							System.out.print(ed);
						} 
					}
				}catch(Exception ed){
					System.out.print(ed);
				}
			}
		});
		btnNewButton_2.setBounds(311, 315, 89, 23);
		contentPane.add(btnNewButton_2);
		
	
		Object [] row=new Object[4];
		try {
			List<Patient> list= new ArrayList<Patient>();
			Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
			list=lg.plist();
			for(Patient P:list) {
				row[0]=P.getID();
				row[1]=P.getName();
				row[2]=P.getNumber();
				row[3]=P.getAge();
				model.addRow(row);
			}
		}catch(Exception ed){
			System.out.print(ed);
		}  
		

		try {
			Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
			Log D=lg.logeduser();
		}catch(Exception ed){
			System.out.print(ed);
		}

	}
}
