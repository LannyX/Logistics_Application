package logistic_application;


public class FacilityImplFactory {
	  public static Facility loadFacility(String fcltName, int fcltRateint, int fcltCostint){
		  return new FacilityImpl(fcltName, fcltRateint, fcltCostint);
	  }
}
