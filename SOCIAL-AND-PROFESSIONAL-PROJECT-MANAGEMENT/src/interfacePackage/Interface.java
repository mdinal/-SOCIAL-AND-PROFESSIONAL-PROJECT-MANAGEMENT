package interfacePackage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {
	int login(String Password,String Username) throws RemoteException;
}
