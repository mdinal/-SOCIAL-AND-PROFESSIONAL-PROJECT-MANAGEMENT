package objects;

import java.io.Serializable;

public class Room implements Serializable{
 String number;
 String floor;
 String ID;
 String Availability;
 
public String getAvailability() {
	return Availability;
}
public void setAvailability(String availability) {
	Availability = availability;
}
public String getID() {
	return ID;
}
public void setID(String iD) {
	ID = iD;
}
public Room() {
	super();
}
public Room(String number, String floor) {
	super();
	this.number = number;
	this.floor = floor;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public String getFloor() {
	return floor;
}
public void setFloor(String floor) {
	this.floor = floor;
}
 
}
