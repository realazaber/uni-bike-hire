/* Subclass of Bike */

package bikehire;

public class RoadBike extends Bike{
	private String type = "Road";
	private double price = 59.00;
	
	public RoadBike(){
	}
	
	public RoadBike(boolean isRented){
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
