/* Bike superclass */

package bikehire;

public class Bike {
	protected int bikeID;
	protected boolean isRented;
	protected static int nextID = 1;
	
	public Bike(){
	}
	
	public Bike(boolean isRented){
		this.bikeID = nextID++;
		this.isRented = isRented;
	}
	
	public int getBikeID(){
		return bikeID;
	}
	
	public boolean getIsRented(){
		return isRented;
	}
	
	public void setIsRented(boolean isRented){
		this.isRented = isRented;
	}
	
	public String toString(){
		return "\nID: " + bikeID + "\nRent status: " + isRented;
	}
}
