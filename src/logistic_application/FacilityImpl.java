package logistic_application;


public class FacilityImpl implements Facility{
	  private String fcltName;
	  private int fcltRate;
	  private int fcltCost;

	  public FacilityImpl(String fcltName, int fcltRateint, int fcltCostint){
		  this.fcltName = fcltName;
		  this.fcltRate = fcltRateint;
		  this.fcltCost = fcltCostint;
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

}