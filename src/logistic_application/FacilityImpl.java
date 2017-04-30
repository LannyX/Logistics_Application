package logistic_application;

import java.util.Collection;
import java.util.Map;

public class FacilityImpl implements Facility{
	private String fcltName;
	private int fcltRate;
	private int fcltCost;
	private Map<String, Integer> neighbors;
	private Inventory inventory;

	public FacilityImpl(String fcltName, int fcltRate, int fcltCost, 
                            Map<String, Integer> fcltNeighbors, Inventory fcltInventory){
	    this.fcltName = fcltName;
	    this.fcltRate = fcltRate;
	    this.fcltCost = fcltCost;
	    this.neighbors = fcltNeighbors;
	    this.inventory = fcltInventory;
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
            System.out.println("Schedule: ");
	}
        
    @Override
    public void printNeighbors() {
            System.out.println("Direct Links:");
            //hourpD, milepH should be input paras
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
        //Return a new collection of Neighbor names
        public Collection<String> getNeighbors(){
            Collection<String> fcltNeighbors=neighbors.keySet();
            return fcltNeighbors;
        }
        
        @Override
        //Return dist between a facility to one of its neighbor
        public int getNeighborDist(String neighborName) {
            return neighbors.get(neighborName);
        }


}