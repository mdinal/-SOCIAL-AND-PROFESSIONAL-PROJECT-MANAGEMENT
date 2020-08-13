package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import interfacePackage.Interface;
import objects.Doctor;
import objects.Vehicle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.rmi.Naming;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ambulance extends JFrame {

	private JPanel contentPane;
	private JTextField Number;
	private JTextField Model;
	private JTextField Availability;
	private JTable table;
	DefaultTableModel model =new DefaultTableModel();
	private int ID=-1;

	/**
	 * Launch the application.
	 */
	public static void Ambulance() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ambulance frame = new Ambulance();
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
	public Ambulance() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(100, 182, 366, 14);
		contentPane.add(Error);
		
		JLabel lblNewLabel_1 = new JLabel("Number");
		lblNewLabel_1.setBounds(34, 40, 74, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Model");
		lblNewLabel_2.setBounds(34, 85, 74, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Availability");
		lblNewLabel_3.setBounds(34, 141, 74, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton INSERT = new JButton("INSERT");
		INSERT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Number.getText().isEmpty()) {
					Error.setText("Please enter Number");
				}else if(Model.getText().isEmpty()) {
					Error.setText("Please enter Model");
				}else if(Availability.getText().isEmpty()) {
					Error.setText("Please enter Availability");
				}
				else {
					Vehicle V=new Vehicle();
					V.setNumber(Number.getText());
					V.setModel(Model.getText());
					V.setAvailability(Availability.getText());
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						boolean re=lg.addVehicle(V);
						if(re) {
							tablecheck();
							Error.setText("");
							JOptionPane.showMessageDialog(null, "Vehicle added Successfully");
						}
					}catch(Exception ed){
						System.out.print(ed);
					}
				}
			}
		});
		INSERT.setBounds(404, 36, 89, 23);
		contentPane.add(INSERT);
		
		
		
		Number = new JTextField();
		Number.setBounds(144, 37, 196, 20);
		contentPane.add(Number);
		Number.setColumns(10);
		
		Model = new JTextField();
		Model.setColumns(10);
		Model.setBounds(144, 82, 196, 20);
		contentPane.add(Model);
		
		Availability = new JTextField();
		Availability.setColumns(10);
		Availability.setBounds(144, 138, 196, 20);
		contentPane.add(Availability);
		
		
		JButton UPDATE = new JButton("UPDATE");
		UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Number.getText().isEmpty()) {
					Error.setText("Please enter Number");
				}else if(Model.getText().isEmpty()) {
					Error.setText("Please enter Model");
				}else if(Availability.getText().isEmpty()) {
					Error.setText("Please enter Availability");
				}else {
					Vehicle V=new Vehicle();
					V.setID(Integer.toString(ID));
					V.setNumber(Number.getText());
					V.setModel(Model.getText());
					V.setAvailability(Availability.getText());
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						boolean re=lg.EditVehicle(V);
						if(re) {
							tablecheck();
							Error.setText("");
							JOptionPane.showMessageDialog(null, "Vehicle update Successfully");
						}
					}catch(Exception ed){
						System.out.print(ed);
					}
				}
				
			}
		});
		UPDATE.setBounds(404, 81, 89, 23);
		contentPane.add(UPDATE);
		
		JButton DELETE = new JButton("DELETE ");
		DELETE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ID>0) {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						boolean re=lg.DeleteVehicle(Integer.toString(ID));
						if(re) {
							tablecheck();
							Error.setText("");
							JOptionPane.showMessageDialog(null, "Vehicle Delete Successfully");
						}
					}catch(Exception ed){
						System.out.print(ed);
					}
				}else {
					Error.setText("please select something to delete");
				}
			}
		});
		DELETE.setBounds(404, 137, 89, 23);
		contentPane.add(DELETE);
		
			
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow MainWindow=new MainWindow();
				MainWindow.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnNewButton.setBounds(648, 36, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 207, 736, 370);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				TableModel model=table.getModel();
				Number.setText(model.getValueAt(i,1).toString());
				Availability.setText(model.getValueAt(i,2).toString());
				Model.setText(model.getValueAt(i,3).toString());
				ID=Integer.parseInt(model.getValueAt(i,0).toString());
			}
		});
		table.setForeground(Color.WHITE);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		Object[] coumns = {"ID", "Number", "Availability", "Model"};
		model.setColumnIdentifiers(coumns);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		table.setOpaque(false);
		((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
		
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		
		tablecheck();
		
		
		JLabel Background = new JLabel("New label");
		Background.setIcon(new ImageIcon(Ambulance.class.getResource("/images/plano_camilla_2_1.gif")));
		Background.setBounds(0, 0, 800, 600);
		contentPane.add(Background);
		
		
		
		
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}

	private void tablecheck() {
		Object [] row=new Object[5];
		try {
			Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
			List <Vehicle> V=lg.Vehiclelist();
			model.setRowCount(0);
			for(int i = 0; i < V.size(); i++) {
				row[0]=V.get(i).getID();
				row[1]=V.get(i).getNumber();
				row[2]=V.get(i).getAvailability();
				row[3]=V.get(i).getModel();


				model.addRow(row);
	        }
	}catch(Exception ed){
		System.out.print(ed);
	}
		
	}
}
