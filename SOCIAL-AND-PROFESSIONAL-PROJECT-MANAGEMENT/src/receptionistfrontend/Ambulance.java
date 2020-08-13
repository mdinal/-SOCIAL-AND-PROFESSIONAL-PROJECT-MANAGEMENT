package receptionistfrontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import interfacePackage.Interface;
import objects.Vehicle;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ambulance extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private int ID;
	DefaultTableModel model =new DefaultTableModel();
	private JLabel lblNewLabel;
	private JLabel VID;
	private JButton btnNewButton_2;
	private JLabel Error;

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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 11, 732, 327);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				TableModel model=table.getModel();
				VID.setText(model.getValueAt(i,0).toString());
				ID=Integer.parseInt(model.getValueAt(i,0).toString());
			}
		});
		table.setForeground(Color.BLACK);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		Object[] coumns = {"ID", "Number", "Availability", "Model"};
		model.setColumnIdentifiers(coumns);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		table.setOpaque(false);
		((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
		
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		
	
		
		lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(60, 382, 125, 14);
		contentPane.add(lblNewLabel);
		
		VID = new JLabel("");
		VID.setFont(new Font("Tahoma", Font.BOLD, 16));
		VID.setBounds(290, 382, 46, 14);
		contentPane.add(VID);
		
		
		JButton btnNewButton = new JButton("Available");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ID>0) {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						boolean re=lg.VehicleAvailable(Integer.toString(ID));
						if(re) {
							JOptionPane.showMessageDialog(null, "Vehicle update Successfully");
							tablecheck();
							Error.setText("");
						}
					}catch(Exception ed){
						System.out.print(ed);
					}
				}else {
					Error.setText("Select something");
				}
			}
		});
		btnNewButton.setBounds(435, 378, 117, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Unavailable");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ID>0) {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						boolean re=lg.VehicleUnavailable(Integer.toString(ID));
						if(re) {
							JOptionPane.showMessageDialog(null, "Vehicle update Successfully");
							tablecheck();
							Error.setText("");

						}
					}catch(Exception ed){
						System.out.print(ed);
					}
				}else {
					Error.setText("Select something");
				}
			}
		});
		btnNewButton_1.setBounds(594, 378, 109, 23);
		contentPane.add(btnNewButton_1);
		
		tablecheck();
		
		
		
		
		btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Receptionistmain RM=new Receptionistmain();
				RM.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnNewButton_2.setBounds(608, 490, 89, 23);
		contentPane.add(btnNewButton_2);
		
		Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(160, 445, 277, 14);
		contentPane.add(Error);
		
		JLabel Backgorund = new JLabel("New label");
		Backgorund.setIcon(new ImageIcon(Ambulance.class.getResource("/images/hospital_emergency.gif")));
		Backgorund.setBounds(0, 0, 800, 600);
		contentPane.add(Backgorund);
		
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
