package logistic_application;

import java.util.ArrayList;
import java.util.Map;

public class FacilityMgr {

    //create ArrayList to put facility
    private ArrayList<Facility> fcltList;
    
    private static FacilityMgr fcltMgr;

    //load the file in c'tor   
    private FacilityMgr(){
        fcltList = FacilityLoader.load();
    }
            
    public static FacilityMgr getInstance(){
        if (fcltMgr==null) fcltMgr = new FacilityMgr();
        return fcltMgr;
    }
	 
    public void printReport(){
        for (Facility facility: fcltList){
            facility.printFacility();
        }
    }
    
    
    

}
