package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import interfacePackage.Interface;
import objects.Doctor;
import security.encryptionClass;


public class implementation extends UnicastRemoteObject implements Interface{


	protected implementation() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int login(String Password,String Username) throws RemoteException {
		// TODO Auto-generated method stub
	    encryptionClass encryption = null;
		try {
			encryption = new encryptionClass();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String encryptionpassword=encryption.encrypt(Password);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String SQL="SELECT * FROM admin WHERE UserName='"+Username+"' AND Password='"+encryptionpassword+"'";
			ResultSet rs=st.executeQuery(SQL);
			if(rs.next()) {
				return 1;
			}
		}  catch(Exception ae) 
        { 
            System.out.println(ae); 
        } 
		return 0;
	}

	@Override
	public boolean addDoctor(Doctor D) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String Sql="INSERT INTO `doctor` (`Name`, `Email`, `Phone`, `Address`, `Specialist`, `ID`) VALUES ('"+D.getName()+"', '"+D.getEmail()+"', '"+D.getPhone()+"', '"+D.getAddress()+"', '"+D.getSpecialist()+"', NULL); ";
			
			int q = st.executeUpdate(Sql);
			if(q==1 || q==2) {
				return true;
			}else {
				return false;
			}
		} catch(Exception ex){
			System.out.print(ex);
			
		}
		return false;
	}





}
