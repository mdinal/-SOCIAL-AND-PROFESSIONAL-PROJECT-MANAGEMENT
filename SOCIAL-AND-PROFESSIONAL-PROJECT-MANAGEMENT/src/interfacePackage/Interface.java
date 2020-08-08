package interfacePackage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import objects.Doctor;
import objects.Empolyee;
import objects.Patient;
import objects.Reservation;
import objects.Room;

public interface Interface extends Remote {
	int login(String Password,String Username) throws RemoteException;
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
}
