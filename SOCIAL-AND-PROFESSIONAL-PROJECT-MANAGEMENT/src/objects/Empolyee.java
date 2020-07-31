package objects;

import java.io.Serializable;
import java.sql.Date;

public class Empolyee implements Serializable {
	int ID;
	String Name;
	String Dateofbirth;
	String Address;
	int Phone ;
	String NIC;
	String Position;
	
	public Empolyee() {
		super();
	}
	public Empolyee(String name, String dateofbirth, String address, int phone, String nIC, String position) {
		super();
		Name = name;
		Dateofbirth = dateofbirth;
		Address = address;
		Phone = phone;
		NIC = nIC;
		Position = position;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDateofbirth() {
		return Dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		Dateofbirth = dateofbirth;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getPhone() {
		return Phone;
	}
	public void setPhone(int phone) {
		Phone = phone;
	}
	public String getNIC() {
		return NIC;
	}
	public void setNIC(String nIC) {
		NIC = nIC;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}

}
