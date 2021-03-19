/* Subclass of Bike */

package bikehire;

public class CityBike extends Bike{
	private String type = "City";
	private double price = 39.00;
	
	public CityBike(){
	}
	
	public CityBike(boolean isRented){
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
