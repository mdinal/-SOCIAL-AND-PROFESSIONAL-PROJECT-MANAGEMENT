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
		btnEditDoctor.setBounds(147, 133, 125, 23);
		contentPane.add(btnEditDoctor);
		
		JButton btnEditemployee = new JButton("Edit Employee");
		btnEditemployee.setBounds(147, 178, 125, 23);
		contentPane.add(btnEditemployee);
	}
}
