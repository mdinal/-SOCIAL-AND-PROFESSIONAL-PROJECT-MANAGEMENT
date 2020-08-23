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

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import objects.Doctor;
import objects.Patient;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Reservation extends JFrame {

	private JPanel contentPane;
	private JTextField DOD;
	private JTextField Name;
	private JTextField Age;
	private JTextField Phone;
	private JTable table;
	
	int DID=-1;
	int PID=-1;

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
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Doctor ID");
		lblNewLabel.setBounds(101, 269, 86, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 21, 644, 216);
		contentPane.add(scrollPane);
		
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(150, 460, 373, 14);
		contentPane.add(Error);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setForeground(Color.black);
		Object[] coumns = {"ID", "Name", "Number", "Specialist", "Fee"};
		DefaultTableModel model =new DefaultTableModel();
		model.setColumnIdentifiers(coumns);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		table.setOpaque(false);
		((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
		
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		
		DOD = new JTextField();
		DOD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c) ||(c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		DOD.setBounds(219, 266, 250, 20);
		contentPane.add(DOD);
		DOD.setColumns(10);
		
		JLabel lblPa = new JLabel("Patient  Name");
		lblPa.setBounds(101, 314, 86, 14);
		contentPane.add(lblPa);
		
		Name = new JTextField();
		Name.setColumns(10);
		Name.setBounds(219, 311, 250, 20);
		contentPane.add(Name);
		
		JLabel lblNewLabel_1_1 = new JLabel("Age");
		lblNewLabel_1_1.setBounds(101, 356, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		Age = new JTextField();
		Age.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c) ||(c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		Age.setColumns(10);
		Age.setBounds(219, 353, 250, 20);
		contentPane.add(Age);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Contact ");
		lblNewLabel_1_1_1.setBounds(101, 405, 86, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
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
		Phone.setBounds(219, 402, 250, 20);
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
		btnNewButton.setBounds(506, 310, 68, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(DOD.getText().isEmpty()) {
					Error.setText("please enter Doctor ID");
				}else {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						boolean res=lg.addReservation(Integer.parseInt(DOD.getText()), PID);
						if(res) {
							JOptionPane.showMessageDialog(null, "Reservation added Successfully");

						}else {
							Error.setText("Reservation adding error");
						}
					}catch(Exception ed){
						System.out.print(ed);
					}
				}
			}
			
		});
		btnNewButton_1.setBounds(196, 490, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Receptionistmain RM=new Receptionistmain();
				RM.setVisible(true);
				setVisible(false);
				dispose(); 
			}
			
		});
		btnNewButton_1_1.setBounds(475, 490, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
		
		
		contentPane.add(btnNewButton_1_1);
		
		JButton Clear = new JButton("Save patient");
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Name.getText().isEmpty()) {
					Error.setText("please enter name");
				}else if(Age.getText().isEmpty()) {
					Error.setText("please enter Age");
				}else if(Phone.getText().isEmpty()) {
					Error.setText("please enter Phone number");
				}else if(!(Phone.getText().length()==10)){
					Error.setText("please enter vaild number");
				}else {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//"); 
						Patient p=new Patient(Name.getText(),Integer.parseInt(Age.getText()),Integer.parseInt(Phone.getText()));
						boolean re=lg.AddPatient(p);
						if(re) {
							JOptionPane.showMessageDialog(null, "Patient added Successfully");
							try {
								Interface lg1=(Interface)Naming.lookup("rmi://localhost:6080//");  
								Patient P=lg1.findbyName(Name.getText());
								Age.setText(Integer.toString(P.getAge()));
								Phone.setText(Integer.toString(P.getPhone()));
								PID=P.getID();
								System.out.print(PID);
						        
						}catch(Exception ed){
							System.out.print(ed);
						}  
						}
					}catch(Exception ed){
						System.out.print(ed);
					} 
				}
				
			}

		});
		Clear.setBounds(584, 310, 115, 23);
		contentPane.add(Clear);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Reservation.class.getResource("/images/unimed-dribbble-gif-01.gif")));
		lblNewLabel_1.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel_1);
		

		setUndecorated(true);
		setLocationRelativeTo(null);
		
		
	}
}
