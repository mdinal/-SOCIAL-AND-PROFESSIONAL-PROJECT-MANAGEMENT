package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import interfacePackage.Interface;
import objects.Doctor;
import objects.Empolyee;
import objects.Room;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class DB extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void DB() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DB frame = new DB();
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
	public DB() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow MainWindow=new MainWindow();
				MainWindow.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnNewButton_1.setBounds(637, 25, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 112, 747, 425);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setForeground(Color.black);
		scrollPane.setViewportView(table);
		table.setOpaque(false);
		((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
		
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		
		JButton btnNewButton = new JButton("Doctor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] coumns = {"ID", "Name", "Email","Phone","Specialist"};
				DefaultTableModel model =new DefaultTableModel();
				model.setColumnIdentifiers(coumns);
				table.setModel(model);
				Object [] row=new Object[6];
				try {
					List<Doctor> list= new ArrayList<Doctor>();
					Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
					list=lg.doctorlist();
					model.setRowCount(0);
					for(Doctor P:list) {
						row[0]=P.getID();
						row[1]=P.getName();
						row[2]=P.getEmail();
						row[3]=P.getPhone();
						row[4]=P.getSpecialist();
						model.addRow(row);
			        }
			}catch(Exception ed){
				System.out.print(ed);
			} 
			}
		});
		btnNewButton.setBounds(52, 25, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnEmployee = new JButton("Employee");
		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] coumns = {"ID", "Name", "Date of Birth","Address","Phone","NIC","Position"};
				DefaultTableModel model =new DefaultTableModel();
				model.setColumnIdentifiers(coumns);
				table.setModel(model);
				Object [] row=new Object[7];
				try {
					List<Empolyee> list= new ArrayList<Empolyee>();
					Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
					list=lg.Emplist();
					model.setRowCount(0);
					for(Empolyee P:list) {
						row[0]=P.getID();
						row[1]=P.getName();
						row[2]=P.getDateofbirth();
						row[3]=P.getAddress();
						row[4]=P.getPhone();
						row[5]=P.getNIC();
						row[6]=P.getPosition();
								
						model.addRow(row);
			        }
			}catch(Exception ed){
				System.out.print(ed);
			} 
			}
		});
		btnEmployee.setBounds(207, 25, 89, 23);
		contentPane.add(btnEmployee);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(DoctorAdd.class.getResource("/images/HandmadeUltimateHoneybee-size_restricted.gif")));
		lblNewLabel_5.setBounds(0, 0, 800,600);
		contentPane.add(lblNewLabel_5);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
