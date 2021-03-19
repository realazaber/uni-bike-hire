package bikehire;

public class Customer {
	private int custID;
	private String fname;
	private String lname;
	private String address;
	private int phone;
	private static int nextID = 1;
	
	public Customer(){
	}
	
	public Customer(String fname, String lname, String address, int phone){
		this.custID = nextID++;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.phone = phone;
	}
	
	public int getCustID(){
		return custID;
	}
	
	public String getFirstName(){
		return fname;
	}
	
	public String getLastName(){
		return lname;
	}
	
	public String getAddress(){
		return address;
	}
	
	public int getPhone(){
		return phone;
	}
	
	public String toString(){
		return "\nID: " + custID + "\nName: " + fname + " " + lname + 
				"\nAddress: " + address + "\nPhone: " + phone;
	}
}
