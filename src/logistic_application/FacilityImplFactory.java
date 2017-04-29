package logistic_application;

import java.util.Map;

//should be a singleton?
public class FacilityImplFactory {
    public static Facility createFacility(String fcltName, int fcltRate, int fcltCost,
                                        Map<String, Integer> fcltNeighbors, Inventory fcltInventory ){
        return new FacilityImpl(fcltName, fcltRate, fcltCost, fcltNeighbors, fcltInventory);
    }
}
