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

public class DoctorMain extends JFrame {

	private JPanel contentPane;
	public JLabel Name;
	private JLabel lblNewLabel_1;
	private JLabel Error;
	private JComboBox Nu;
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
		setBounds(100, 100, 451, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Name = new JLabel("");
		Name.setBounds(127, 70, 223, 14);
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
		btnNewButton.setBounds(267, 252, 89, 23);
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
		btnNewButton_1.setBounds(50, 252, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Please Enter Room Number and Floor your in");
		lblNewLabel.setBounds(113, 103, 246, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Hello Doctor,");
		lblNewLabel_1.setBounds(81, 45, 178, 14);
		contentPane.add(lblNewLabel_1);
		
		Error = new JLabel("");
		Error.setForeground(Color.RED);
		Error.setBounds(113, 217, 197, 14);
		contentPane.add(Error);
		
		Nu = new JComboBox();
		Nu.setBounds(70, 159, 280, 20);
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
	}
}
