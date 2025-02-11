package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfacePackage.Interface;
import objects.Room;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class AddROmm extends JFrame {

	private JPanel contentPane;
	private JTextField Number;
	private JTextField Floor;

	/**
	 * Launch the application.
	 */
	public static void AddROmm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddROmm frame = new AddROmm();
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
	public AddROmm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Number");
		lblNewLabel.setBounds(543, 43, 46, 14);
		contentPane.add(lblNewLabel);
		
		Number = new JTextField();
		Number.setBounds(624, 37, 123, 20);
		contentPane.add(Number);
		Number.setColumns(10);
		
		Floor = new JTextField();
		Floor.setColumns(10);
		Floor.setBounds(624, 84, 123, 20);
		contentPane.add(Floor);
		
		JLabel lblFloor = new JLabel("Floor");
		lblFloor.setBounds(543, 90, 46, 14);
		contentPane.add(lblFloor);
		
		JLabel Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(137, 181, 190, 14);
		contentPane.add(Error);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Number.getText().isEmpty()) {
					Error.setText("Room number is Empty");
				}else if(Floor.getText().isEmpty()) {
					Error.setText("Room Floor is Empty");

				}else {
					try {
						Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
						Room R=new Room(Number.getText(),Floor.getText());
						boolean re=lg.addRoom(R);
						if(re) {
							JOptionPane.showMessageDialog(null, "Room Saved Successfully");
							Number.setText("");
							Floor.setText("");
							Error.setText("");
						}else {
							Error.setText("Room not saved try changeing number");
						}
					}catch(Exception ed){
						System.out.print(ed);
					}  
				}
			}
		});
		btnNewButton.setBounds(559, 138, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow MainWindow=new MainWindow();
				MainWindow.setVisible(true);
				setVisible(false);
				dispose(); 
			}
		});
		btnNewButton_1.setBounds(658, 138, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AddROmm.class.getResource("/images/dribbble_2.gif")));
		lblNewLabel_1.setBounds(0, 0, 784, 561);
		contentPane.add(lblNewLabel_1);
		
		
	}
}
