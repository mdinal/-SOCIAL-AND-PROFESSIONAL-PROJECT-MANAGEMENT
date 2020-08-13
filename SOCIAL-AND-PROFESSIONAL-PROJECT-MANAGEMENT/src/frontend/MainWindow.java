package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton AddDoctor = new JButton("Add Doctor");
		AddDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DoctorAdd DA=new DoctorAdd();
				DA.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		AddDoctor.setBounds(147, 11, 125, 23);
		contentPane.add(AddDoctor);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(147, 55, -168, -23);
		contentPane.add(btnNewButton);
		
		JButton btnAddEmployee = new JButton("Add Employee");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEmployee AE=new AddEmployee();
				AE.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnAddEmployee.setBounds(147, 51, 125, 23);
		contentPane.add(btnAddEmployee);
		
		JButton btnAddRoom = new JButton("Add Room");
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddROmm AR=new AddROmm();
				AR.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnAddRoom.setBounds(147, 91, 125, 23);
		contentPane.add(btnAddRoom);
		
		JButton btnEditDoctor = new JButton("Edit Doctor");
		btnEditDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditDoctor AR=new EditDoctor();
				AR.setVisible(true);
				setVisible(false);
				dispose(); 
			}
			
		});
		btnEditDoctor.setBounds(147, 133, 125, 23);
		contentPane.add(btnEditDoctor);
		
		JButton btnEditemployee = new JButton("Edit Employee");
		btnEditemployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditEmployee AR=new EditEmployee();
				AR.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnEditemployee.setBounds(147, 178, 125, 23);
		contentPane.add(btnEditemployee);
		
		JButton btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lg=new Login();
				lg.main(null);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton_1.setBounds(315, 227, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Ambulance");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ambulance a=new Ambulance();
				a.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton_2.setBounds(147, 212, 125, 23);
		contentPane.add(btnNewButton_2);
	}
}
