package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import interfacePackage.Interface;



public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try
        { 
           Interface face=new implementation();
           LocateRegistry.createRegistry(6080); 
           Naming.rebind("rmi://localhost:6080//",face);
        } 
        catch(Exception error) 
        { 
            System.out.println(error); 
        } 
	}

}
