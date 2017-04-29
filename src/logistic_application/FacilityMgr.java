package logistic_application;

import java.util.ArrayList;
import java.util.Map;

public class FacilityMgr {

	 //create ArrayList to put facility
	 private ArrayList<Facility>  FacilityList = new ArrayList<Facility>();
	 
	 public void GetInstance(){
		 Facility_loader GetIns = new Facility_loader(this);
	 }
	 
	 public void AddFacility(Facility facility){
		 FacilityList.add(facility);
	 }
	 
	 public void PrintTheReport(){
		 for (Facility facility: FacilityList){
			 facility.PrintFacility();
		 }
	 }
	 
}
