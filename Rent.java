package bikehire;

import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class Rent implements Serializable{
	private int rentID;
	private int custID;
	private int bikeID;
	private String date;
	private double duration;
	private static int nextID = 1;
	/* Assignment specifications unclear on this ArrayList. */
	private ArrayList<Integer> bikesID;
	
	public Rent(){
	}
	
	public Rent(int custID, int bikeID, String date, double duration){
		rentID = nextID++;
		this.custID = custID;
		this.bikeID = bikeID;
		this.date = date;
		this.duration = duration;
		/* Assignment specifications unclear on this ArrayList. */
		bikesID = new ArrayList<Integer>();
	}
	
	public int getRentID(){
		return rentID;
	}
	
	public int getCustID(){
		return custID;
	}
	public int getBikeID(){
		return bikeID;
	}
	
	/* Assignment specifications unclear on this ArrayList. */
	public void addBike(int bikeID){
		bikesID.add(bikeID);
	}
	
	public String getDate(){
		return date;
	}
	
	public double getDuration(){
		return duration;
	}
	
	public String toString(){
		return "\nRent ID: " + rentID + "\nCustomer ID: " + custID +
				"\nDate: " + date + "\nDuration: " + duration;
	}
}
