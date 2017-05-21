package facility;

import java.util.Collection;

/** 
* @ClassName: Facility
* @Description: facility interface
*/
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
    
    
    
    public boolean hasItem(String item);
    
    public int getItemQtt(String item);
    
    public int getProcEndDay (int startDay, int qtt);
    
    public void reduceItem(String item,int amount);
    
    public void bookSchd(int startDay,int qtt);

}
