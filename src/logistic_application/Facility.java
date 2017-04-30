package logistic_application;

import java.util.Map;

public interface Facility {

	public String getfcltName();
	  	  
	public int getfcltRate();
	  
	public int getfcltCost();
	
	public Map<String, Integer> getNeighbors();
	
	public void printFacility();
        
    public void printNeighbors();

	public Inventory getInventory();
        
    public void printInventory();
	  	
	
}
