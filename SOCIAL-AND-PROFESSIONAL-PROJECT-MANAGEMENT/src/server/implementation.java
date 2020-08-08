package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfacePackage.Interface;
import objects.Doctor;
import objects.Empolyee;
import objects.Patient;
import objects.Reservation;
import objects.Room;
import security.encryptionClass;


public class implementation extends UnicastRemoteObject implements Interface{


	protected implementation() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int login(String Username,String Password) throws RemoteException {
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
			}else {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
					Statement st1=con1.createStatement();
					String SQL1="SELECT * FROM employee WHERE Name='"+Username+"' AND Password='"+encryptionpassword+"'";
					ResultSet rs1=st1.executeQuery(SQL1);
					if(rs1.next()) {
						return 2;
					}
				}catch(Exception ex){
					System.out.print(ex);
				}
			}
		} catch(Exception ex){
			System.out.print(ex);
			
		}
		return 3;
		
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

	@Override
	public boolean AddEmployee(Empolyee E) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String Sql="INSERT INTO `employee` (`ID`, `Name`, `Date of Birth`, `Address`, `Phone`, `NIC`, `Position`,`Password`) VALUES (NULL, '"+E.getName()+"', '"+E.getDateofbirth()+"', '"+E.getAddress()+"', '"+E.getPhone()+"', '"+E.getNIC()+"', '"+E.getPosition()+"','8mzCNJUNtvSJJSpQl9fUJg==');";
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

	@Override
	public boolean addRoom(Room R) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String Sql="INSERT INTO `room` (`ID`, `Number`, `Floor`) VALUES (NULL, '"+R.getNumber()+"', '"+R.getFloor()+"'); ";
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


	public List showdoctor() throws RemoteException {
		List <Doctor> arrylist=new ArrayList<Doctor>();
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
	         
	         Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * FROM doctor");

	         
	         while (rs.next()) {
	        	 Doctor D=new Doctor();
	        	 D.setID(Integer.parseInt(rs.getString("ID")));
	            D.setName(rs.getString("Name")); 
	            D.setEmail(rs.getString("Email"));
	            D.setPhone(Integer.parseInt(rs.getString("Phone")));
	            D.setAddress(rs.getString("Address"));
	            D.setSpecialist(rs.getString("Specialist"));
	            arrylist.add(D);
	         }
	      } catch(Exception ex) {
	    	  System.out.print(ex);
	      }
		return arrylist;
}
	public Patient findbyName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		Patient P = new Patient() ;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String SQL="SELECT * FROM patient WHERE Name='"+name+"'";
			ResultSet rs=st.executeQuery(SQL);
			
			while(rs.next()) {
				P.setID(Integer.parseInt(rs.getString("ID")));
				P.setAge(Integer.parseInt(rs.getString("Age")));
				P.setPhone(Integer.parseInt(rs.getString("Phone")));
				
			}
		
			
		} catch(Exception ex){
			System.out.print(ex);
			
		}
		return P;
	}
	public boolean AddPatient(Patient P) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String Sql="INSERT INTO `patient` (`ID`, `Name`, `Age`, `Phone`) VALUES (NULL, '"+P.getName()+"', '"+P.getAge()+"', '"+P.getPhone()+"');";
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
	public boolean addReservation(int DID,int PID) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String Sql="INSERT INTO `reservation` (`PID`, `DID`, `ID`) VALUES ('"+PID+"', '"+DID+"', NULL); ";
			String SQL="INSERT INTO `reservation` (`PID`, `DID`, `ID`, `Confirmed`, `Number`) SELECT '"+PID+"','"+DID+"',NUll,'0',COUNT("+DID+")+1 FROM `reservation` WHERE DID="+DID+";";
			
			int q = st.executeUpdate(SQL);
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
	public Doctor DoctorFind(String name) throws RemoteException {
		// TODO Auto-generated method stub
		Doctor D = new Doctor() ;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String SQL="SELECT * FROM doctor WHERE Name='"+name+"'";
			ResultSet rs=st.executeQuery(SQL);
			
			while(rs.next()) {
				D.setID(Integer.parseInt(rs.getString("ID")));
				D.setName(rs.getString("Name"));
				D.setPhone(Integer.parseInt(rs.getString("Phone")));
				D.setEmail(rs.getString("Email"));
				D.setAddress(rs.getString("Address"));
				D.setSpecialist(rs.getString("Specialist"));


				
			}
		
			
		} catch(Exception ex){
			System.out.print(ex);
			
		}
		return D;
	}
	public Empolyee EmployeeFind(String name) throws RemoteException {
		// TODO Auto-generated method stub
		Empolyee E = new Empolyee() ;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String SQL="SELECT * FROM employee WHERE Name='"+name+"'";
			ResultSet rs=st.executeQuery(SQL);
			
			while(rs.next()) {
				E.setID(Integer.parseInt(rs.getString("ID")));
				E.setName(rs.getString("Name"));
				E.setDateofbirth(rs.getString("Date of Birth"));
				E.setAddress(rs.getString("Address"));
				E.setPhone(Integer.parseInt(rs.getString("Phone")));
				E.setNIC(rs.getString("NIC"));
				E.setPosition(rs.getString("Position"));
				
			}
		
			
		} catch(Exception ex){
			System.out.print(ex);
			
		}
		return E;
	}
	public Reservation findreservation(String Name)throws RemoteException {
		Reservation R=new Reservation();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String SQL="SELECT * FROM patient WHERE Name='"+Name+"'";
			ResultSet rs=st.executeQuery(SQL);
			while(rs.next()) {
				R.setPID(rs.getString("ID"));
				R.setPhone(rs.getString("Phone"));
			}
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
				Statement st1=con.createStatement();
				String SQL1="SELECT * FROM reservation WHERE PID='"+R.getPID()+"'";
				ResultSet rs1=st.executeQuery(SQL1);
				while(rs1.next()) {
					R.setDID(rs1.getString("DID"));
					R.setNumber(rs1.getString("Number"));
					R.setID(rs1.getString("ID"));

					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
						Statement st2=con2.createStatement();
						String SQL2="SELECT * FROM doctor WHERE ID='"+R.getDID()+"'";
						ResultSet rs2=st2.executeQuery(SQL2);
						while(rs2.next()) {
							R.setDName(rs2.getString("Name"));
						} 
						}catch(Exception ex){
						System.out.print(ex);
						
					}
				}
				
			} catch(Exception ex){
				System.out.print(ex);
				
			}
			
		} catch(Exception ex){
			System.out.print(ex);
			
		}
		
		return R;
	}
	public boolean confrmresavation(String ID)throws RemoteException {
		String SQL="UPDATE `reservation` SET `Confirmed`=1 WHERE ID="+ID+"";
		Reservation R=new Reservation();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			int re=st.executeUpdate(SQL);
			if(re>0) {
				return true;
			}
	}catch(Exception ex){
		System.out.print(ex);
		
	}
		return false;
}
}
