package objects;

import java.io.Serializable;

public class Doctor implements Serializable{
	String Name;
	String Email;
	int phone;
	String Address;
	String Specialist;
	
	public Doctor() {
		super();
	}
	public Doctor(String name, String email, int phone, String address, String specialist) {
		super();
		Name = name;
		Email = email;
		this.phone = phone;
		Address = address;
		Specialist = specialist;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getSpecialist() {
		return Specialist;
	}
	public void setSpecialist(String specialist) {
		Specialist = specialist;
	}
	

}
