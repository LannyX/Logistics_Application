package logistic_application;

import java.util.Collection;

public interface Facility {

	public String getfcltName();
	  	  
	public int getfcltRate();
	  
	public int getfcltCost();
	
	public void printFacility();
	
	public void printSchedule();
	
        public void printNeighbors();
        
        public void printInventory();
        
        public Collection<String> getNeighbors();
        
        public int getNeighborDist(String neighborName);

}
