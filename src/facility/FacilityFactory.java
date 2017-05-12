package facility;

import exception.DataValidationException;
import exception.NullParamException;
import java.util.Map;

/** 
* @ClassName: FacilityFactory 
* @Description: factory to build up facility using factory pattern
*/
public class FacilityFactory {
    public static Facility createFacility(String fcltName, int fcltRate, int fcltCost,
                                        Map<String, Integer> fcltNeighbors, Inventory fcltInventory)
                                        throws NullParamException,DataValidationException{
        return new FacilityImpl(fcltName, fcltRate, fcltCost, fcltNeighbors, fcltInventory);
    }
}
