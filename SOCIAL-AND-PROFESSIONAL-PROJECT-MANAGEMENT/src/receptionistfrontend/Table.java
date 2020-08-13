package receptionistfrontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import interfacePackage.Interface;
import objects.Doctor;
import objects.Patient;
import objects.Room;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Table extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void Table() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Table frame = new Table();
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
	public Table() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(75, 119, 641, 377);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setForeground(Color.black);
		Object[] coumns = {"ID", "Number", "Floor"};
		DefaultTableModel model =new DefaultTableModel();
		model.setColumnIdentifiers(coumns);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		table.setOpaque(false);
		((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
		
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Receptionistmain RM=new Receptionistmain();
				RM.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnNewButton.setBounds(605, 23, 89, 23);
		contentPane.add(btnNewButton);
		
		

		
		Object [] row=new Object[4];
		try {
			List<Room> list= new ArrayList<Room>();
			Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
			list=lg.allroom();
			for(Room P:list) {
				row[0]=P.getID();
				row[1]=P.getNumber();
				row[2]=P.getFloor();
				model.addRow(row);
	        }
	}catch(Exception ed){
		System.out.print(ed);
	}  
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Table.class.getResource("/images/160606__gif.gif")));
		lblNewLabel.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
