package nurse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import frontend.Ambulance;
import frontend.Login;
import frontend.MainWindow;
import interfacePackage.Interface;
import objects.Item;
import objects.Vehicle;
import shared.PasswordN;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Nurse extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Quantity;
	private JTable table;
	DefaultTableModel model =new DefaultTableModel();
	private int ID=-1;

	/**
	 * Launch the application.
	 */
	public static void Nurse() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nurse frame = new Nurse();
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
	public Nurse() {
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
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(34, 53, 74, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setBounds(34, 109, 74, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton INSERT = new JButton("INSERT");
		INSERT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(Name.getText().isEmpty()) {
					Error.setText("Please enter Name");
				}else if(Quantity.getText().isEmpty()) {
					Error.setText("Please enter Quantity");
				}
				else {
					Item V=new Item();
					V.setName(Name.getText());
					V.setQuantity(Quantity.getText());
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						boolean re=lg.additem(V);
						if(re) {
							tablecheck();
							Error.setText("");
							JOptionPane.showMessageDialog(null, "Item added Successfully");
						}
					}catch(Exception ed){
						System.out.print(ed);
					}
				}
			}
		});
		INSERT.setBounds(404, 36, 89, 23);
		contentPane.add(INSERT);
		
		Name = new JTextField();
		Name.setColumns(10);
		Name.setBounds(144, 50, 196, 20);
		contentPane.add(Name);
		
		Quantity = new JTextField();
		Quantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(!(Character.isDigit(c) ||(c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE))) {
					arg0.consume();
				}
			}
		});
		Quantity.setColumns(10);
		Quantity.setBounds(144, 106, 196, 20);
		contentPane.add(Quantity);
		
		
		JButton UPDATE = new JButton("UPDATE");
		UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(Name.getText().isEmpty()) {
					Error.setText("Please enter Name");
				}else if(Quantity.getText().isEmpty()) {
					Error.setText("Please enter Quantity");
				}else {
					Item V=new Item();
					V.setID(Integer.toString(ID));
					V.setName(Name.getText());
					V.setQuantity(Quantity.getText());
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						boolean re=lg.EditItem(V);
						if(re) {
							tablecheck();
							Error.setText("");
							JOptionPane.showMessageDialog(null, "Item update Successfully");
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
						boolean re=lg.DeleteItem(Integer.toString(ID));
						if(re) {
							tablecheck();
							Error.setText("");
							JOptionPane.showMessageDialog(null, "Item Delete Successfully");
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
		
			
		JButton btnNewButton = new JButton("LogOut");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lg=new Login();
				lg.main(null);
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
				Quantity.setText(model.getValueAt(i,2).toString());
				Name.setText(model.getValueAt(i,1).toString());
				ID=Integer.parseInt(model.getValueAt(i,0).toString());
			}
		});
//		table.setForeground(Color.WHITE);
//		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		Object[] coumns = {"ID", "Name", "Quantity"};
		model.setColumnIdentifiers(coumns);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		table.setOpaque(false);
		((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
		
		
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		
		tablecheck();
		
		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x=Integer.parseInt(Quantity.getText());
				int va=x+1;
				Quantity.setText(Integer.toString(va));
			}
		});
		btnNewButton_1.setBounds(83, 158, 47, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("-");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x=Integer.parseInt(Quantity.getText());
				int va=x-1;
				Quantity.setText(Integer.toString(va));
			}
		});
		btnNewButton_1_1.setBounds(223, 158, 47, 23);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PasswordN n=new PasswordN();
				n.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnProfile.setBounds(648, 100, 89, 23);
		contentPane.add(btnProfile);
		
		
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
			List <Item> V=lg.Itemlist();
			model.setRowCount(0);
			for(int i = 0; i < V.size(); i++) {
				row[0]=V.get(i).getID();
				row[1]=V.get(i).getName();
				row[2]=V.get(i).getQuantity();

				model.addRow(row);
	        }
	}catch(Exception ed){
		System.out.print(ed);
	}
		
	}
}
