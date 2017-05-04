package logistic_application;

import java.util.Map;

/** 
* @ClassName: FacilityImplFactory 
* @Description: factory to build up facility using factory pattern
*/
public class FacilityImplFactory {
    public static Facility createFacility(String fcltName, int fcltRate, int fcltCost,
                                        Map<String, Integer> fcltNeighbors, Inventory fcltInventory){
        return new FacilityImpl(fcltName, fcltRate, fcltCost, fcltNeighbors, fcltInventory);
    }
}
