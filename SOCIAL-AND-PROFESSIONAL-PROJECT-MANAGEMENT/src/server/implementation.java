package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import interfacePackage.Interface;
import objects.Doctor;
import objects.Empolyee;
import objects.Item;
import objects.Log;
import objects.Patient;
import objects.Reservation;
import objects.Room;
import objects.Vehicle;
import security.encryptionClass;


public class implementation extends UnicastRemoteObject implements Interface{

	Log userCurrent=new Log();
	String RoomID;
	protected implementation() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Log login(String Username,String Password) throws RemoteException {
		// TODO Auto-generated method stub
		Log Data=new Log();
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
//				Data.setID(rs.getString("ID"));
				Data.setReturn("1");
				return Data;
			}else {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
					Statement st1=con1.createStatement();
					String SQL1="SELECT * FROM employee WHERE Name='"+Username+"' AND Password='"+encryptionpassword+"'";
					ResultSet rs1=st1.executeQuery(SQL1);
					if(rs1.next()) {

						Data.setID(rs1.getString("ID"));
						Empolyee e=new Empolyee();
						e.setName(rs1.getString("Name"));
						e.setID(Integer.parseInt(rs1.getString("ID")));
						e.setPosition(rs1.getString("Position"));
						Data.setE(e);
						Data.setReturn("2");
						userCurrent=Data;
						return Data;
					}else {
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
							Statement st2=con2.createStatement();
							String SQL2="SELECT * FROM doctor WHERE Name='"+Username+"' AND Password='"+encryptionpassword+"'";
							ResultSet rs2=st2.executeQuery(SQL2);
							if(rs2.next()) {
								Data.setID(rs2.getString("ID"));
								Doctor d=new Doctor();
								d.setName(rs2.getString("Name"));
								d.setID(Integer.parseInt(rs2.getString("ID")));
								Data.setD(d);
								Data.setReturn("3");
								userCurrent=Data;
								return Data;
							}
						}catch(Exception ex){
							System.out.print(ex);
						}
					}
				}catch(Exception ex){
					System.out.print(ex);
				}
			}
		} catch(Exception ex){
			System.out.print(ex);
			
		}
		return null;
		
	}

	@Override
	public boolean addDoctor(Doctor D) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String Sql="INSERT INTO `doctor` (`Name`, `Email`, `Phone`, `Address`, `Specialist`, `ID`) VALUES ('"+D.getName()+"', '"+D.getEmail().toLowerCase()+"', '"+D.getPhone()+"', '"+D.getAddress()+"', '"+D.getSpecialist()+"', NULL); ";
			
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
			String Sql="INSERT INTO `employee` (`ID`, `Name`, `Date of Birth`, `Address`, `Phone`, `NIC`, `Position`,`Password`,`Email`) VALUES (NULL, '"+E.getName()+"', '"+E.getDateofbirth()+"', '"+E.getAddress()+"', '"+E.getPhone()+"', '"+E.getNIC()+"', '"+E.getPosition()+"','8mzCNJUNtvSJJSpQl9fUJg==','"+E.getEmail().toLowerCase()+"');";
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
			String SQL="INSERT INTO `reservation` (`PID`, `DID`,  `Confirmed`, `Number`) SELECT '"+PID+"','"+DID+"','0',COUNT("+DID+")+1 FROM `reservation` WHERE DID="+DID+";";
			
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
				E.setEmail(rs.getString("Email"));
				
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
	public boolean editDoctor(Doctor D) throws RemoteException {
		// TODO Auto-generated method stub
		  try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "UPDATE doctor SET Name = ?, Email = ?,Phone =?,Address=?,Specialist=? WHERE ID = ?");

		    ps.setString(1,D.getName());
		    ps.setString(2,D.getEmail().toLowerCase());
		    ps.setInt(3,D.getPhone());
		    ps.setString(4, D.getAddress());
		    ps.setString(5,D.getSpecialist());
		    ps.setInt(6, D.getID());

		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (Exception se)
		  {
			  System.out.print(se);
			  return false;
		  }
		return true;
	}
	public boolean deleteDoctor(int ID) throws RemoteException {
		try
	    {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
	      

	      String query = "delete from doctor where ID = ?";
	      PreparedStatement preparedStmt = con.prepareStatement(query);
	      preparedStmt.setInt(1, ID);


	      preparedStmt.execute();
	      
	      con.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println(e.getMessage());
	    }
		return true;
	}
	public boolean editEMP(Empolyee E) throws RemoteException {
		// TODO Auto-generated method stub
		  try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "UPDATE `employee` SET `Name` = ?, `Date of Birth` = ?,`Address`=?,`Phone`=?,`NIC`=?,`Position`=?,`Email`=? WHERE `ID` = ?");

		    ps.setString(1,E.getName());
		    ps.setString(2,E.getDateofbirth());
		    ps.setString(3,E.getAddress());
		    ps.setInt(4,E.getPhone());
		    ps.setString(5,E.getNIC());
		    ps.setString(6, E.getPosition());
		    ps.setString(7, E.getEmail().toLowerCase());
		    ps.setInt(8, E.getID());

		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (Exception se)
		  {
			  System.out.print(se);
			  return false;
		  }
		return true;
	}
	public boolean deleteEMP(int ID) throws RemoteException {
		try
	    {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
	      

	      String query = "delete from employee where ID = ?";
	      PreparedStatement preparedStmt = con.prepareStatement(query);
	      preparedStmt.setInt(1, ID);


	      preparedStmt.execute();
	      
	      con.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println(e.getMessage());
	    }
		return true;
	}
	public boolean updateRoom(String ID) throws RemoteException {
		try
	    {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "UPDATE room SET Availability = 1 WHERE ID = ? ");

		    ps.setString(1,ID);

		    ps.executeUpdate();
		    ps.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println(e.getMessage());
	    }
		return true;
	}
	public Log logeduser() throws RemoteException{
//		System.out.println(userCurrent.getD().getName());
		return userCurrent;
		
	}
	public List allroom()throws RemoteException{
		List<Room> list= new ArrayList<Room>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String SQL="SELECT * FROM `room` WHERE Availability=0";
			ResultSet rs=st.executeQuery(SQL);
			while(rs.next()) {
				Room R=new Room();
				R.setFloor(rs.getString("Floor"));
				R.setNumber(rs.getString("Number"));
				R.setID(rs.getString("ID"));
				list.add(R);
			}
		} catch (Exception e){
		      System.err.println(e.getMessage());
		    }
		return list;
	}
	public boolean DowngradeRoom(String ID) throws RemoteException {
		try
	    {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "UPDATE room SET Availability = 0 WHERE ID = ? ");

		    ps.setString(1,ID);

		    ps.executeUpdate();
		    ps.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println(e.getMessage());
	    }
		return true;
	}
	public List plist()throws RemoteException {

		List<Patient> list= new ArrayList<Patient>();
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String SQL="SELECT * FROM `reservation` INNER JOIN patient ON reservation.PID=patient.ID WHERE Confirmed =1 AND DID="+userCurrent.getD().getID()+"";
			ResultSet rs=st.executeQuery(SQL);
			
			while(rs.next()) {
				Patient P=new Patient();
				P.setID(Integer.parseInt(rs.getString("PID")));
				P.setName(rs.getString("Name"));
				P.setAge(Integer.parseInt(rs.getString("Age")));
				P.setNumber(rs.getString("Number"));
				list.add(P);
			}
		
		}catch (Exception e){
		      System.err.println(e.getMessage());
		    }
		return list;
		
	}
	public Patient findP(String ID)throws RemoteException {
		Patient P=new Patient();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String SQL="SELECT * FROM `patient` WHERE ID="+ID+"";
			ResultSet rs=st.executeQuery(SQL);
			if(rs.next()) {
				P.setDetails(rs.getString("Details"));
				return P;
			}
		}catch (Exception e){
		      System.err.println(e.getMessage());
		    }
		return null;
	}
	public boolean updatepatient(String ID,String d)throws RemoteException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			PreparedStatement ps = con.prepareStatement(
				      "UPDATE patient SET Details = ? WHERE ID = ?");
			 
			ps.setString(1,d);
			ps.setString(2,ID);
			int re=ps.executeUpdate();
			ps.close();
			if(re>0) {
				Statement st1=con.createStatement();
				String sql="DELETE FROM `reservation` WHERE PID="+ID+"";
				st1.execute(sql);
				return true;
			}
	}catch(Exception ex){
		System.out.print(ex);
		
	}
		return false;
}
	public boolean addVehicle(Vehicle V) throws RemoteException {
		// TODO Auto-generated method stub
		  try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "INSERT INTO vehicle (ID, Number, Availability, Model) VALUES (NULL, ?, ?, ?);");

		    ps.setString(1,V.getNumber());
		    ps.setString(2, V.getAvailability());
		    ps.setString(3,V.getModel());

		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (Exception se)
		  {
			  System.out.print(se);
			  return false;
		  }
		return true;
	}
	public boolean EditVehicle(Vehicle V) throws RemoteException {
		// TODO Auto-generated method stub
		  try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "UPDATE `vehicle` SET `Number`=?,`Availability`=?,`Model`=? WHERE ID=?");

		    ps.setString(1,V.getNumber());
		    ps.setString(2,V.getAvailability());
		    ps.setString(3,V.getModel());
		    ps.setString(4,V.getID());

		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (Exception se)
		  {
			  System.out.print(se);
			  return false;
		  }
		return true;
	}
	
	public boolean DeleteVehicle(String V) throws RemoteException {
		// TODO Auto-generated method stub
		  try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "DELETE FROM `vehicle` WHERE ID=?");

		    ps.setString(1,V);
		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (Exception se)
		  {
			  System.out.print(se);
			  return false;
		  }
		return true;
	}
	public List Vehiclelist()throws RemoteException {

		List<Vehicle> list= new ArrayList<Vehicle>();
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String SQL="SELECT * FROM `vehicle`";
			ResultSet rs=st.executeQuery(SQL);
			
			while(rs.next()) {
				Vehicle V=new Vehicle();
				V.setNumber(rs.getString("Number"));
				V.setID(rs.getString("ID"));
				V.setAvailability(rs.getString("Availability"));
				V.setModel(rs.getString("Model"));
				list.add(V);
			}
		
		}catch (Exception e){
		      System.err.println(e.getMessage());
		    }
		return list;
		
	}
	public boolean VehicleAvailable(String D) throws RemoteException {
		// TODO Auto-generated method stub
		  try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "UPDATE `vehicle` SET `Availability`=? WHERE ID=?");

		    ps.setString(1,"Available");
		    ps.setString(2,D);

		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (Exception se)
		  {
			  System.out.print(se);
			  return false;
		  }
		return true;
	}
	public boolean VehicleUnavailable(String D) throws RemoteException {
		// TODO Auto-generated method stub
		  try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "UPDATE `vehicle` SET `Availability`=? WHERE ID=?");

		    ps.setString(1,"Unavailable");
		    ps.setString(2,D);

		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (Exception se)
		  {
			  System.out.print(se);
			  return false;
		  }
		return true;
	}
	public boolean updateEbyName(String D,String Name) throws RemoteException {
		// TODO Auto-generated method stub
		  try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "UPDATE `employee` SET `Name`=? WHERE ID=?");

		    ps.setString(1,Name);
		    ps.setString(2,D);

		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (Exception se)
		  {
			  System.out.print(se);
			  return false;
		  }
		return true;
	}
	public boolean updateEbyNamePassword(String D,String Name,String Password) throws RemoteException {
		// TODO Auto-generated method stub
		encryptionClass encryption = null;
		try {
			encryption = new encryptionClass();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String encryptionpassword=encryption.encrypt(Password);

		  try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "UPDATE `employee` SET `Name`=? , `Password`=? WHERE ID=?");

		    ps.setString(1,Name);
		    ps.setString(2,encryptionpassword);
		    ps.setString(3, D);

		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (Exception se)
		  {
			  System.out.print(se);
			  return false;
		  }
		return true;
	}
	public String getroomID()throws RemoteException{
		return RoomID;	
	}
	public void setroomID(String ID)throws RemoteException{
		RoomID=ID;
	}
	public boolean updateDbyName(String D,String Name) throws RemoteException {
		// TODO Auto-generated method stub
		  try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "UPDATE `doctor` SET `Name`=? WHERE ID=?");

		    ps.setString(1,Name);
		    ps.setString(2,D);

		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (Exception se)
		  {
			  System.out.print(se);
			  return false;
		  }
		return true;
	}
	public boolean updateDbyNamePassword(String D,String Name,String Password) throws RemoteException {
		// TODO Auto-generated method stub
		encryptionClass encryption = null;
		try {
			encryption = new encryptionClass();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String encryptionpassword=encryption.encrypt(Password);

		  try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "UPDATE `doctor` SET `Name`=? , `Password`=? WHERE ID=?");

		    ps.setString(1,Name);
		    ps.setString(2,encryptionpassword);
		    ps.setString(3, D);

		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (Exception se)
		  {
			  System.out.print(se);
			  return false;
		  }
		return true;
	}
	public int resetpassword(String Email)throws RemoteException{
		String ID;
		int re=-1;
		int code;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String SQL="SELECT * FROM doctor WHERE Email='"+Email.toLowerCase()+"' ";
			ResultSet rs=st.executeQuery(SQL);
			if(rs.next()) {
				ID=rs.getString("ID");
				System.out.print(ID);
				re=1;
			}else {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
					Statement st1=con1.createStatement();
					String SQL1="SELECT * FROM employee WHERE Email='"+Email.toLowerCase()+"' ";
					ResultSet rs1=st1.executeQuery(SQL1);
					if(rs1.next()) {
						ID=rs1.getString("ID");
						System.out.print(ID);
						re=2;
					}
				}catch (Exception se){
					  System.out.print(se);
				  }
			}
		
		}catch (Exception se){
			  System.out.print(se);
		  }
		try {
			Random rand=new Random();
			code=rand.nextInt(999999);
			String host="smtp.mail.yahoo.com";
			String user="infohospitalrecovery@yahoo.com";
			String pass="#batman1234";
			String subject="Reseting Code";
			String message="Your reset Code is :"+code;
			boolean sessuinDebug=false;
			Properties pros=System.getProperties();
			pros.put("mail.smtp.starttls.enable", "true");
			pros.put("mail.smtp.host", "465");
			pros.put("mail.smtp.auth","true");
			pros.put("mail.smtp.starttls.required","true");
			
		}catch (Exception se){
			  System.out.print(se);
		  }
		return re;
		
	}
	public boolean additem(Item V) throws RemoteException {
		// TODO Auto-generated method stub
		  try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "INSERT INTO item (ID, Name, Quantity) VALUES (NULL, ?, ?);");

		    ps.setString(1,V.getName());
		    ps.setString(2, V.getQuantity());

		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (Exception se)
		  {
			  System.out.print(se);
			  return false;
		  }
		return true;
	}
	public boolean EditItem(Item V) throws RemoteException {
		// TODO Auto-generated method stub
		  try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "UPDATE `item` SET `Name`=?,`Quantity`=? WHERE ID=?");

		    ps.setString(1,V.getName());
		    ps.setString(2,V.getQuantity());
		    ps.setString(3,V.getID());

		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (Exception se)
		  {
			  System.out.print(se);
			  return false;
		  }
		return true;
	}
	
	public boolean DeleteItem(String V) throws RemoteException {
		// TODO Auto-generated method stub
		  try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");

		    PreparedStatement ps = con.prepareStatement(
		      "DELETE FROM `item` WHERE ID=?");

		    ps.setString(1,V);
		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (Exception se)
		  {
			  System.out.print(se);
			  return false;
		  }
		return true;
	}
	public List Itemlist()throws RemoteException {

		List<Item> list= new ArrayList<Item>();
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			Statement st=con.createStatement();
			String SQL="SELECT * FROM `item`";
			ResultSet rs=st.executeQuery(SQL);
			
			while(rs.next()) {
				Item V=new Item();
				V.setName(rs.getString("Name"));
				V.setID(rs.getString("ID"));
				V.setQuantity(rs.getString("Quantity"));
				list.add(V);
			}
		
		}catch (Exception e){
		      System.err.println(e.getMessage());
		    }
		return list;
		
	}

}
