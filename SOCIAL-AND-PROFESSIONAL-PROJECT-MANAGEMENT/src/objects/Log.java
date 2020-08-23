package objects;

import java.io.Serializable;

public class Log implements Serializable {
	String Return;
	String ID;
	Doctor D;
	Empolyee E;
	
	public Empolyee getE() {
		return E;
	}
	public void setE(Empolyee e) {
		E = e;
	}
	public Doctor getD() {
		return D;
	}
	public void setD(Doctor d) {
		D = d;
	}
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Log(String return1, String iD) {
		super();
		Return = return1;
		ID = iD;
	}
	public String getReturn() {
		return Return;
	}
	public void setReturn(String return1) {
		Return = return1;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
}
