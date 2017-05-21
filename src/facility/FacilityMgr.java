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
    * @Title: FacilityMgr 
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
    
    
    public boolean fcltExist(String fclt) {
        return fclts.keySet().contains(fclt);
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

    
    public HashMap<String,Integer> getFcltsOwnItem (String item, String orderDest) {
        HashMap<String,Integer> fcltsOwnItem=new HashMap<String,Integer>();
        for (String fclt:fclts.keySet()) {
            if (!fclt.equals(orderDest) && fclts.get(fclt).hasItem(item)) {
                fcltsOwnItem.put(fclt, fclts.get(fclt).getItemQtt(item));
            }
        }
        return fcltsOwnItem;
    }
    
    
    public int getProcEndDayAtFclt(String fclt,int startDay, int qtt){
        return fclts.get(fclt).getProcEndDay(startDay, qtt);
    }
    
    public void reduceItemAtFclt(String fclt,String item,int amount){
        fclts.get(fclt).reduceItem(item, amount);
    }
    
    public void bookSchdAtFclt(String fclt,int startDay,int qtt){
        fclts.get(fclt).bookSchd(startDay, qtt);
    }
    
    public int getFcltRate(String fclt){
        return fclts.get(fclt).getfcltRate();
    }
    
    public int getFcltCost(String fclt){
        return fclts.get(fclt).getfcltCost();
    }
}
