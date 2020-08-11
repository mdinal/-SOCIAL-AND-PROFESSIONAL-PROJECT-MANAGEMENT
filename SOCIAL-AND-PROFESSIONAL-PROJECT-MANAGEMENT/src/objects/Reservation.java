package objects;

import java.io.Serializable;

public class Reservation implements Serializable {
	String ID;
	String DID;
	String PID;
	String Fee;
	String DName;
	String Phone;
	String Number;
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reservation(String dID, String pID, String fee, String dName,String phone,String number,String iD) {
		super();
		DID = dID;
		PID = pID;
		Fee = fee;
		DName = dName;
		Phone=phone;
		Number=number;
		ID=iD;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getDID() {
		return DID;
	}
	public void setDID(String dID) {
		DID = dID;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public String getFee() {
		return Fee;
	}
	public void setFee(String fee) {
		Fee = fee;
	}
	public String getDName() {
		return DName;
	}
	public void setDName(String dName) {
		DName = dName;
	}
	
}
