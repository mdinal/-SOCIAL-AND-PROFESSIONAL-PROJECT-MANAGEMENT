package guidoctor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import frontend.Login;
import interfacePackage.Interface;
import objects.Log;
import objects.Patient;
import shared.passwordD;

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
import javax.swing.ImageIcon;

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
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		RoomID = new JLabel("");
		RoomID.setBounds(221, 29, 46, 14);
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
		btnNewButton.setBounds(652, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 93, 608, 179);
		contentPane.add(scrollPane);
		
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(237, 491, 369, 14);
		contentPane.add(Error);
		
		table = new JTable();
		table.setForeground(Color.WHITE);
		Object[] coumns = {"ID", "Name", "Number","Age"};
		DefaultTableModel model =new DefaultTableModel();
		model.setColumnIdentifiers(coumns);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		table.setOpaque(false);
		((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
		
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(191, 341, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Details");
		lblNewLabel_1.setBounds(191, 379, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		ID = new JTextField();
		ID.setBounds(280, 338, 86, 20);
		contentPane.add(ID);
		ID.setColumns(10);
		
		Details = new JTextField();
		Details.setBounds(280, 376, 414, 96);
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
		
		btnNewButton_1.setBounds(376, 337, 89, 23);
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
		btnNewButton_2.setBounds(627, 514, 89, 23);
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
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				passwordD p=new passwordD();
				p.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnProfile.setBounds(38, 20, 89, 23);
		contentPane.add(btnProfile);
		
		try {
			Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
			RoomID.setText(lg.getroomID());
		}catch(Exception ed){
			System.out.print(ed);
		}
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(DoctorList.class.getResource("/images/33ba21ccda561739ab950d66e5616b82.gif")));
		lblNewLabel_2.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel_2);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
