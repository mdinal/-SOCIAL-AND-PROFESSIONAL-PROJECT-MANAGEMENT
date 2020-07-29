package objects;

import java.io.Serializable;

public class Room implements Serializable{
 int number;
 int floor;
 
public Room() {
	super();
}
public Room(int number, int floor) {
	super();
	this.number = number;
	this.floor = floor;
}
public int getNumber() {
	return number;
}
public void setNumber(int number) {
	this.number = number;
}
public int getFloor() {
	return floor;
}
public void setFloor(int floor) {
	this.floor = floor;
}
 
}
