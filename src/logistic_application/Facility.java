package logistic_application;

import java.util.Map;

public interface Facility {

	public String getfcltName();
	  	  
	public int getfcltRate();
	  
	public int getfcltCost();
	
        //should not return ref, need mod
	public Map<String, Integer> getNeighbors();
	
	public void printFacility();
        
        public void printNeighbors();
        
	//should not return ref, need mod
	public Inventory getInventory();
        
        public void printInventory();
	  	
	
}
