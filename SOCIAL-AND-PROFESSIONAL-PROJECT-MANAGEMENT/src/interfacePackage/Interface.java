package interfacePackage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import objects.Doctor;
import objects.Empolyee;
import objects.Log;
import objects.Patient;
import objects.Reservation;
import objects.Room;
import objects.Vehicle;

public interface Interface extends Remote {
	public Log login(String Password,String Username) throws RemoteException;
	boolean addDoctor(Doctor D)throws RemoteException;
	boolean AddEmployee(Empolyee E)throws RemoteException;
	boolean addRoom(Room R)throws RemoteException;
	public List showdoctor() throws RemoteException;
	public Patient findbyName(String name) throws RemoteException;
	public boolean AddPatient(Patient P) throws RemoteException;
	public boolean addReservation(int DID,int PID) throws RemoteException;
	public Doctor DoctorFind(String name) throws RemoteException ;
	public Empolyee EmployeeFind(String name) throws RemoteException;
	public Reservation findreservation(String Name)throws RemoteException;
	public boolean confrmresavation(String ID)throws RemoteException;
	public boolean editDoctor(Doctor D) throws RemoteException ;
	public boolean deleteDoctor(int ID) throws RemoteException ;
	public boolean editEMP(Empolyee E) throws RemoteException ;
	public boolean deleteEMP(int ID)throws RemoteException ;
	public boolean updateRoom(String ID) throws RemoteException;
	public Log logeduser() throws RemoteException;
	public List allroom()throws RemoteException;
	public boolean DowngradeRoom(String ID) throws RemoteException;
	public List plist()throws RemoteException; 
	public Patient findP(String ID)throws RemoteException;
	public boolean updatepatient(String ID,String d)throws RemoteException;
	public boolean addVehicle(Vehicle V) throws RemoteException ;
	public boolean EditVehicle(Vehicle V) throws RemoteException;
	public boolean DeleteVehicle(String V) throws RemoteException;
	public List Vehiclelist()throws RemoteException;
	public boolean VehicleAvailable(String D) throws RemoteException;
	public boolean VehicleUnavailable(String D) throws RemoteException;
	
}
