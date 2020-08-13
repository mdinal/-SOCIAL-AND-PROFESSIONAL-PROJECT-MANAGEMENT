package guidoctor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import frontend.Login;
import interfacePackage.Interface;
import objects.Log;
import objects.Room;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.ImageIcon;

public class DoctorMain extends JFrame {

	private JPanel contentPane;
	public JLabel Name;
	private JLabel lblNewLabel_1;
	private JLabel Error;
	private JComboBox Nu;
	private JLabel lblNewLabel_2;
	/**
	 * Launch the application.
	 */
	public static void DoctorMain() {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					DoctorMain frame = new DoctorMain();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 */

	public DoctorMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Name = new JLabel("");
		Name.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Name.setBounds(209, 217, 241, 23);
		contentPane.add(Name);
		
		try {
			Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
			Log D=lg.logeduser();
			Name.setText(D.getD().getName());
		}catch(Exception ed){
			System.out.print(ed);
		}

		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
					String st=Nu.getSelectedItem().toString();
					String ID=st.substring(st.lastIndexOf("=") + 1);
					boolean re=lg.updateRoom(ID);
					if(re) {
						DoctorList D=new DoctorList();
						D.RoomID.setText(ID);
						D.setVisible(true);
						setVisible(false);
						dispose(); 
					}else {
						Error.setText("try again" );
					}
				}catch(Exception ed){
					System.out.print(ed);
				}  
			}
		});
		btnNewButton.setBounds(650, 527, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lg=new Login();
				lg.main(null);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton_1.setBounds(650, 69, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Please Enter Room Number and Floor your in");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(196, 295, 410, 23);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Hello Doctor,");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(79, 158, 184, 28);
		contentPane.add(lblNewLabel_1);
		
		Error = new JLabel("");
		Error.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Error.setForeground(Color.RED);
		Error.setBounds(209, 437, 325, 23);
		contentPane.add(Error);
		
		Nu = new JComboBox();
		Nu.setBounds(250, 379, 280, 20);
		contentPane.add(Nu);
		
		
		
		try {
			Interface lg=(Interface)Naming.lookup("rmi://localhost:6080//");  
			List<Room> re= new ArrayList<Room>();
			re=lg.allroom();
			for(Room R:re) {
				Nu.addItem("Room number:"+R.getNumber()+" and Floor Number: "+R.getFloor()+"  ID="+R.getID()+"");
			}
		}catch(Exception ed){
			System.out.print(ed);
		}  
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(DoctorMain.class.getResource("/images/main-qimg-3b02a08f616a383c88f7a378f13648d2.gif")));
		lblNewLabel_2.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel_2);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
