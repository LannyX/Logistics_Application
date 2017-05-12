package facility;

import bestpath.BestPath;
import exception.DataValidationException;
import exception.NullParamException;
import java.util.Collection;
import java.util.HashMap;

/** 
* @ClassName: FacilityMgr
* @Description: define higher-lever interface to use facility in facade pattern
*/
public class FacilityMgr {

    private HashMap<String, Facility> fclts;
    private static FacilityMgr fcltMgr;


    /** 
    * @Title: FacilityMgr(c'tor) 
    * @Description: load Facilities information
    */
    private FacilityMgr(){
        fclts = FacilityLoader.load();
    }

    public static FacilityMgr getInstance(){
        if (fcltMgr==null) fcltMgr = new FacilityMgr();
        return fcltMgr;
    }
    
    /** 
    * @Title: printReport
    * @Description: print out Facility Status Output
    */
    public void printReport(){
        Collection<Facility> fcltObj=fclts.values();
        for (Facility facility: fcltObj){
            facility.printFacility();
        }
    }

    /** 
    * @Title: getNeighbors
    * @Description: get a collection of one facility's neighbor names
    */
    public Collection<String> getNeighbors(String fcltname) throws 
                                        NullParamException,DataValidationException {
        if (fcltname==null) {
            throw new NullParamException("Null facility is not allowed.");
        }
        if (!getFclts().contains(fcltname)) {
            throw new DataValidationException("Facility does not exist.");
        }
        return fclts.get(fcltname).getNeighbors();
    }
    
    /** 
    * @Title: getFclts
    * @Description: get a collection of all facility names
    */
    public Collection<String> getFclts() {
        return fclts.keySet();
    }

    /** 
    * @Title: getDist
    * @Description: get a shortest distance between two facilities
    */
    public int getDist(String start,String end) throws 
                                    NullParamException,DataValidationException{
        if (start==null||end==null) {
            throw new NullParamException("Null facility is not allowed.");
        }
        if (!(getFclts().contains(start) && getFclts().contains(end))) {
            throw new DataValidationException("Facility does not exist.");
        }
        BestPath bestpathInst=new BestPath(fclts);
        bestpathInst.findBestPath(start,end);
        return bestpathInst.getlowPathLength();
    }

    /** 
    * @Title: printBestPath
    * @Description: print out the BestPath between two facilities
    */    
    public void printBestPath(String start,String end) throws 
                                NullParamException,DataValidationException {
        if (start==null||end==null) {
            throw new NullParamException("Null facility is not allowed.");
        }
        if (!(getFclts().contains(start) && getFclts().contains(end))) {
            throw new DataValidationException("Facility does not exist.");
        }
        
        BestPath bestpathInst=new BestPath(fclts);
        bestpathInst.findBestPath(start,end);
        //In phase 1, temporarily set the hourpD, milepH to be constants
        final float hourpD=8;
        final float milepH=50;
        bestpathInst.printlowPath(hourpD, milepH);
    }

}
