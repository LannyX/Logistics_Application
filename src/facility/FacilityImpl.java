package facility;

import exception.DataValidationException;
import exception.NullParamException;
import item.ItemMgr;
import java.util.Collection;
import java.util.Map;

/** 
* @ClassName: FacilityImpl
* @Description: implementation of facility using strategy pattern
*/
public class FacilityImpl implements Facility{
	private String fcltName;
	private int fcltRate;
	private int fcltCost;
	private Map<String, Integer> neighbors;
	private Inventory inventory;
	private Schedule schedule;

	public FacilityImpl(String fcltName, int fcltRate, int fcltCost, 
                            Map<String, Integer> fcltNeighbors, Inventory fcltInventory)
                            throws NullParamException,DataValidationException{
            
            //Handle exceptions of input fcltName
            if (fcltName==null) throw new NullParamException("Null facility is not allowed.");
            if (fcltName.equals("")) throw new DataValidationException("Empty facility name is not allowed.");
            //Exception of fcltRate and fcltCost            
            if (fcltRate<0) throw new DataValidationException("Negative facility rate is unreasonable.");
            if (fcltCost<0) throw new DataValidationException("Negative facility cost is unreasonable.");
            //Exception of fcltNeighbors               
            for (String neighborName: fcltNeighbors.keySet()) {
                if (neighborName==null) throw new NullParamException("Null neighbor is not allowed.");
                if (neighborName.equals("")) 
                    throw new DataValidationException("Empty neighbor name is not allowed.");
                
                if (fcltNeighbors.get(neighborName)<=0) 
                    throw new DataValidationException("Non-positive distance between facilities is unreasonable.");
            }
            //Exception of inventory  
            for (String itemId: fcltInventory.getItems()) {
                if (!ItemMgr.getInstance().itemExist(itemId))
                    throw new DataValidationException("Inventory contains item not existing in catalog.");
                if (fcltInventory.getQtt(itemId)<0)
                    throw new DataValidationException("Negative item number in inventory is unreasonable.");
            }

	    this.fcltName = fcltName;
	    this.fcltRate = fcltRate;
	    this.fcltCost = fcltCost;
	    this.neighbors = fcltNeighbors;
	    this.inventory = fcltInventory;
	    this.schedule = new Schedule(fcltRate);
	}
	  
	@Override
	public String getfcltName() {
	    return fcltName;
	}

	@Override
	public int getfcltRate() {
	    return fcltRate;
	}

	@Override
	public int getfcltCost() {
	    return fcltCost;
        }

    @Override
	public void printFacility(){
            System.out.println("------------------------------------------------------------------------");
            System.out.println(fcltName);
            System.out.println("-----------");
            System.out.println("Rate per day: "+ fcltRate);
            System.out.println("Cost per day: $"+ fcltCost + "\n");
            printNeighbors();
            System.out.println();
            printInventory();
            System.out.println();
            printSchedule();
	}
        
    @Override
    public void printNeighbors() {
            System.out.println("Direct Links:");
            final float hourpD=8;
            final float milepH=50;
                    
            for (String neighbor:neighbors.keySet()){
                float travelTime= neighbors.get(neighbor) /hourpD /milepH;
                System.out.printf(neighbor+" (%1.1fd); ",travelTime);
            }
            System.out.println();
    }


        @Override
        public void printInventory(){
            inventory.printInv();
        }
        
        @Override
        //Return a new collection of all Neighbor names
        public Collection<String> getNeighbors(){
            Collection<String> fcltNeighbors=neighbors.keySet();
            return fcltNeighbors;
        }
        
        @Override
        //Return dist between a facility to one of its neighbor
        public int getNeighborDist(String neighborName) {
            return neighbors.get(neighborName);
        }
        @Override
        public void printSchedule() {
            schedule.printSchd();
        }

    @Override
    public boolean hasItem(String item) {
        return inventory.ownsItem(item) && (inventory.getQtt(item)!=0);
    }

    @Override
    public int getItemQtt(String item) {
        return inventory.getQtt(item);
    }

    @Override
    public int getProcEndDay(int startDay, int qtt) {
        return schedule.getEndDay(startDay, qtt);
    }

    @Override
    public void reduceItem(String item, int amount){
        inventory.reduceQtt(item, amount);
    }

    @Override
    public void bookSchd(int startDay, int qtt) {
        schedule.bookSchd(startDay, qtt);
    }
    
    

       
        

}