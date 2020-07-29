package interfacePackage;

import java.rmi.Remote;
import java.rmi.RemoteException;

import objects.Doctor;
import objects.Empolyee;
import objects.Room;

public interface Interface extends Remote {
	int login(String Password,String Username) throws RemoteException;
	boolean addDoctor(Doctor D)throws RemoteException;
	boolean AddEmployee(Empolyee E)throws RemoteException;
	boolean addRoom(Room R)throws RemoteException;
}
