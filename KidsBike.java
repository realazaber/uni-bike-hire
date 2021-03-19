/* Subclass of Bike */

package bikehire;

public class KidsBike extends Bike{
	private String type = "Kids";
	private double price = 22.00;
	
	public KidsBike(){
	}
	
	public KidsBike(boolean isRented){
		super(isRented);
	}
	
	public String getType(){
		return type;
	}
	
	public double getPrice(){
		return price;
	}
	
	public String toString(){
		return super.toString() + "\nType: " + type + "\nPrice per day: "+ price;
	}
}
