package objects;

public class Admin {
	int ID;
	String Password;
	String Username;
	
	
	public Admin() {
		super();
	}
	public Admin(int iD, String password, String username) {
		super();
		ID = iD;
		Password = password;
		Username = username;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	
}
