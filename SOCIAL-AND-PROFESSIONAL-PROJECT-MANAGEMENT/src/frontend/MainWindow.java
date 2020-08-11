package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void MainWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton AddDoctor = new JButton("Add Doctor");
		AddDoctor.setFont(new Font("Calibri", Font.PLAIN, 14));
		AddDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DoctorAdd DA=new DoctorAdd();
				DA.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		AddDoctor.setBounds(624, 30, 125, 23);
		contentPane.add(AddDoctor);
		
		JButton btnAddEmployee = new JButton("Add Employee");
		btnAddEmployee.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEmployee AE=new AddEmployee();
				AE.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnAddEmployee.setBounds(624, 64, 125, 23);
		contentPane.add(btnAddEmployee);
		
		JButton btnAddRoom = new JButton("Add Room");
		btnAddRoom.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddROmm AR=new AddROmm();
				AR.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnAddRoom.setBounds(624, 98, 125, 23);
		contentPane.add(btnAddRoom);
		
		JButton btnEditDoctor = new JButton("Edit Doctor");
		btnEditDoctor.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnEditDoctor.setBounds(624, 132, 125, 23);
		contentPane.add(btnEditDoctor);
		
		JButton btnEditemployee = new JButton("Edit Employee");
		btnEditemployee.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnEditemployee.setBounds(624, 166, 125, 23);
		contentPane.add(btnEditemployee);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/images/medical_building_800x600.gif")));
		lblNewLabel.setBounds(0, 0, 784, 561);
		contentPane.add(lblNewLabel);
	}
}
