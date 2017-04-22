package logistic_application;

import java.util.Map;

public class FacilityImplFactory {
	  public static Facility loadFacility(String fcltName, int fcltRateint, int fcltCostint, Map<String, Integer> fcltneighbors ){
		  return new FacilityImpl(fcltName, fcltRateint, fcltCostint, fcltneighbors);
	  }
}
