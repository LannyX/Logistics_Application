package logistic_application;

import java.util.Map;

public class FacilityImpl implements Facility{
	  private String fcltName;
	  private int fcltRate;
	  private int fcltCost;
	  private Map<String, Integer> neighbors;

	  public FacilityImpl(String fcltName, int fcltRateint, int fcltCostint, Map<String, Integer> fcltneighbors){
		  this.fcltName = fcltName;
		  this.fcltRate = fcltRateint;
		  this.fcltCost = fcltCostint;
		  this.neighbors = fcltneighbors;
	  }

		@Override
		public String getfcltName() {
			// TODO Auto-generated method stub
			return fcltName;
		}

		@Override
		public int getfcltRate() {
			// TODO Auto-generated method stub
			return fcltRate;
		}

		@Override
		public int getfcltCost() {
			// TODO Auto-generated method stub
			return fcltCost;
		}

		@Override
		public Map<String, Integer> getneighbors() {
			// TODO Auto-generated method stub
			return neighbors;
		}

}