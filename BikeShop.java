package bikehire;

import java.io.*;
import java.util.*;

public class BikeShop {
	private ArrayList<Bike> bikes;
	private ArrayList<Customer> customers;
	private ArrayList<Rent> rents;
	
	public BikeShop(){
		bikes = new ArrayList<Bike>();
		customers = new ArrayList<Customer>();
		rents = new ArrayList<Rent>();
		
		//create five bikes
		CityBike b1 = new CityBike(false);
		RoadBike b2 = new RoadBike(false);
		KidsBike b3 = new KidsBike(false);
		CityBike b4 = new CityBike(false);
		RoadBike b5 = new RoadBike(false);
		
		//add bikes to ArrayList
		bikes.add(b1);
		bikes.add(b2);
		bikes.add(b3);
		bikes.add(b4);
		bikes.add(b5);
	}
	
	private void listAvailableBikes(){
		int bikeCount = 0;
		try{
			for(Bike b: bikes){
				if(b.getIsRented() == false){
					System.out.println(b);
					bikeCount++;
				}
			}
			if(bikeCount < 1){
				throw new Exception("No bikes available.");
			}
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	 
	private void addCustomer(){
		Scanner input = null;
		try{
			input = new Scanner(System.in);
			System.out.println("First name: ");
			String fname = input.nextLine();
			System.out.println("Last name: ");
			String lname = input.nextLine();
			System.out.println("Customer address: ");
			String address = input.nextLine();
			System.out.println("Customer phone: ##########");
			int phone = input.nextInt();
	   
			//create customer
			Customer c = new Customer(fname, lname, address, phone);
			//add to ArrayList
			customers.add(c);
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	 
	private void displayCustomers(){
		int custCount = 0;
		try{
			for(Customer c: customers){
				System.out.println(c);
				custCount++;
			}
			if(custCount < 1){
				throw new Exception("No customers registered.");
			}
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	 
	private int searchForCustomer(String fname, String lname){
		int id = 0;
		for(Customer c: customers){
			if(fname.equalsIgnoreCase(c.getFirstName()) && lname.equalsIgnoreCase(c.getLastName())){
				id = c.getCustID();
			}
		}
		return id;
	}
	 
	private int searchForBikeByType(int type){
		int id = 0;
		try{
			switch(type){
			case 1:
				for(Bike b: bikes){
					if(b instanceof CityBike && b.getIsRented() == false){
						id = b.getBikeID();
						break;
					}
				}
				break;
			case 2:
				for(Bike b: bikes){
					if(b instanceof RoadBike && b.getIsRented() == false){
						id = b.getBikeID();
						break;
					}
				}
				break;
			case 3:
				for(Bike b: bikes){
					if(b instanceof KidsBike && b.getIsRented() == false){
						id = b.getBikeID();
						break;
					}
				}
				break;
			default:
				System.out.println("Invalid selection. Select a number between 1 and 3.");
			}
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return id;
	}
	 
	private void addRent(){
		Scanner input = null;
		try{
			int custID = 0;
			
			input = new Scanner(System.in);
			System.out.println("Customer details");
			System.out.println("Enter first name: ");
			String fname = input.nextLine();
			System.out.println("Enter last name: ");
			String lname = input.nextLine();
			
			custID = searchForCustomer(fname, lname);
			if(custID != 0){
				System.out.println("Enter date: ##-##-####");
				String date = input.nextLine();
				System.out.println("Enter duration: ");
				double duration = input.nextDouble();
				if(duration > 30){
					throw new Exception("Duration cannot exceed 30 days.");
				}
				else if(duration < 1){
					throw new Exception("Duration must be at least 1 day.");
				}
				else{
					System.out.println("CustID selected: " + custID);
					
					boolean flag = true;
					
					while(flag){
						System.out.println("Enter type of bike: " +
											"\n1. City bike" +
											"\n2. Road bike" +
											"\n3. Kids bike");
						int type = input.nextInt();
	      
						if(type < 1 || type > 3){
							throw new Exception("Invalid bike type selected.");
						}
						else{
							//get first bikeID of type selected
							int bikeID = searchForBikeByType(type);
							//ArrayList index starts from zero
							int bikeArrayListID = bikeID - 1;
							
							//retrieve bike from ArrayList
							Bike b = bikes.get(bikeArrayListID);
							
							//set bike to isRented
							b.setIsRented(true);
							
							//create rent
							Rent r = new Rent(custID, bikeID, date, duration);
							
							//add bikeID to ArrayList of bikeID in class Rent
							/* Assignment specifications unclear on this ArrayList. */
							r.addBike(bikeID);
							
							//add rent to local ArrayList
							rents.add(r);
	           
							input.nextLine();
							System.out.println("Input 'N' to complete order. Otherwise 'Enter' to add another bike to customer order.");
							String loopAgain = input.nextLine();
							
							if(loopAgain.equals("N") || loopAgain.equals("n")){
								flag = false;
								break;
							}
						}
					}
				}
			}
			else{
				throw new Exception("Customer not found.");
			}
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	private void displayRents(){
		int rentCount = 0;
		try{
			for(Rent r: rents){
				System.out.println(r);
				rentCount++;
			}
			if(rentCount < 1){
				throw new Exception("No rentals found.");
			}
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	 
	private void SearchRentalsByCustID(){
		Scanner input = null;
		try{
			input = new Scanner(System.in);
			System.out.println("\nSearch options: " + 
								"\n1. I want to find a customer ID." +
								"\n2. I already have a customer ID.");
			int choice = input.nextInt();
			
			if(choice < 1 || choice > 2){
				throw new Exception("Invalid selection.");
			}
			
			int custID = 0;
	   
			switch(choice){
			case 1:
				input.nextLine();
				System.out.println("Enter first name: ");
				String fname = input.nextLine();
				System.out.println("Enter last name: ");
				String lname = input.nextLine();
	    
				//call searchForCustomer
				custID = searchForCustomer(fname, lname);
	    
				if(custID == 0){
					throw new Exception("Customer not found.");
				}
				break;
			case 2:
				System.out.println("Enter customer ID: ");
				custID = input.nextInt();
				if(custID < 1){
					throw new Exception("Customer ID cannot be below one.");
				}
				break;
			default:
				System.out.println("Incorrect input. Please enter either 1 or 2."); 
			}
	   
			if(custID != 0){
				int rentCount = 0;
	    
				for(Rent r: rents){
					if(custID == r.getCustID()){
						System.out.println(r);
						rentCount++;
					}
				}
	    
				if(rentCount < 1){
					throw new Exception("No rentals found for that customer.");
				}
			}
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	private void saveRentals(){
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
	   
		try{
			fos = new FileOutputStream("rentals.dat");
			oos = new ObjectOutputStream(fos);
	    
			for(Rent r: rents){
				oos.writeObject(r);
			}
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		finally{
			try{
				fos.close();
				oos.close();
			}
			catch(Exception fe){
				System.err.println("Error: " + fe.getMessage());
			} 
		}
	}
	 
	/* This method prints the contents of rentdals.dat to the console. 
	* This is my interpretation of the requirements. */
	private void readRentals(){
		FileInputStream fis = null;
		ObjectInputStream ois = null;
	  
		try{
			fis = new FileInputStream("rentals.dat");
			ois = new ObjectInputStream(fis);
	   
			Rent r = null;
			while(true){
				try{
					Object obj = ois.readObject();
					r = (Rent)obj;
					System.out.println(r);
				}
				catch(EOFException eofe){
					break;
				}
			}
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		finally{
			try{
				fis.close();
				ois.close();
			}
			catch(Exception fe){
				System.err.println("Error: " + fe.getMessage());
			}
		}
	}
	 
	private void writeInvoice(){
		Scanner input = null;
		PrintWriter output = null;
		try{
			int custID = 0, bikeID = 0, phone = 0;
			double duration = 0.0, pricePerDay = 0.0;
			String name = null, address = null, type = null, date = null;
	   
			input = new Scanner(System.in);
			System.out.println("Enter rent ID: ");
			int rentID = input.nextInt();
	   
			if(rentID < 1){
				throw new Exception("Rent ID cannot be below zero.");
			}
			else{
				output = new PrintWriter("invoice.txt");
	    
				boolean rentCheck = false;
	    
				for(Rent r: rents){
					if(rentID == r.getRentID()){
						custID = r.getCustID();
						bikeID = r.getBikeID();
						date = r.getDate();
						duration = r.getDuration();
						rentCheck = true;
					}
				}
	    
				if(rentCheck != true){
					throw new Exception("Cannot find rental with ID " + rentID + ".");
				}
				else{
					for(Customer c: customers){
						if(custID == c.getCustID()){
							name = c.getFirstName() + " " + c.getLastName();
							address = c.getAddress();
							phone = c.getPhone();
						}
					}
	     
					for(Bike b: bikes){
						if(bikeID == b.getBikeID()){
							if(b instanceof CityBike){
								type = ((CityBike)b).getType();
								pricePerDay = ((CityBike)b).getPrice();
							}
							else if(b instanceof RoadBike){
								type = ((RoadBike)b).getType();
								pricePerDay = ((RoadBike)b).getPrice();
							}
							else if(b instanceof KidsBike){
								type = ((KidsBike)b).getType();
								pricePerDay = ((KidsBike)b).getPrice();
							}
						}
					}
	     
					//calculate total price for order
					double totalPrice = pricePerDay * duration;
	     
					StringBuffer line1 = new StringBuffer("Rent ID: ");
					line1.append(rentID);
					StringBuffer line2 = new StringBuffer("Rent date: ");
					line2.append(date);
					StringBuffer line4 = new StringBuffer("Bike: ");
					line4.append(bikeID);
					line4.append("  ");
					line4.append(type);
					StringBuffer line5 = new StringBuffer("Price per day: ");
					line5.append(pricePerDay);
					StringBuffer line3 = new StringBuffer("Rent duration: ");
					line3.append(duration);
					line3.append(" days");
					StringBuffer line6 = new StringBuffer("Total price: ");
					line6.append(totalPrice);
					StringBuffer line7 = new StringBuffer("To Customer: ");
					line7.append(name);
					line7.append(", ");
					line7.append(address);
					line7.append(". Phone: ");
					line7.append(phone);
	     
					output.println(line1);
					output.println(line2);
					output.println(line3);
					output.println(line4);
					output.println(line5);
					output.println(line6);
					output.println(line7);
				}
			}
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		finally{
			if(output != null){
				output.close();
			}
		}
	}
	 
	public void run(){
		Scanner input = null;
		try{
			boolean flag = true;
   
			while(flag){
				System.out.println("\n1. Display available bikes\n" +
									"2. Add customer\n" +
									"3. Display customers\n" +
									"4. Find a customer ID\n" +
									"5. Find a bike ID by type\n" +
									"6. Add a rent\n" +
									"7. Display all rentals\n" +
									"8. Search rentals by customer ID\n" +
									"9. Save rentals to file\n" +
									"10. Read rentals from file\n" +
									"11. Write printable invoice to file\n" +
									"12. Quit"); 
	
				input = new Scanner(System.in);
				int choice = 0;
   
				System.out.println("Enter your choice (1 - 12): ");
				choice = input.nextInt();
	
				switch(choice){
				case 1:
					listAvailableBikes();
					break;
				case 2:
					addCustomer();
					break;
				case 3:
					displayCustomers();
					break;
				case 4:
					input.nextLine();
					System.out.println("Search will return only customer ID. \nZero means no customer found.");
					System.out.println("\nFirst name: ");
					String fname = input.nextLine();
					System.out.println("Last name: ");
					String lname = input.nextLine();
					System.out.println("Customer ID: " + searchForCustomer(fname, lname));
					break;
				case 5:
					System.out.println("\nDesired type: " +
										"\n1. City bike" + 
										"\n2. Road bike" +
										"\n3. Kids Bike");
					int type = input.nextInt();
					System.out.println("First available bike ID: " + searchForBikeByType(type));
					break;
				case 6:
					addRent();
					break;
				case 7:
					displayRents();
					break;
				case 8:
					SearchRentalsByCustID();
					break;
				case 9:
					saveRentals();
					break;
				case 10:
					readRentals();
					break;
				case 11:
					writeInvoice();
					break;
				case 12:
					flag = false;
					break;
				default:
					System.out.println("Incorrect input. Please enter a number between 1 and 12.");
				}
			}
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		finally{
			if(input != null){
				input.close();
			}
		}
	}
}
