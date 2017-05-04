package logistic_application;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/** 
* @ClassName: FacilityMgr
* @Description: define higher-lever interface to use facilites in facade pattern
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
    * @Description: get a collection of one facility's neighbors' names
    */
    public Collection<String> getNeighbors(String fcltname) throws ItemNotExistException {
        if (!getFclts().contains(fcltname)) {
            throw new ItemNotExistException("Facility does not exist.");
        }
        return fclts.get(fcltname).getNeighbors();
    }
    
    /** 
    * @Title: getFclts
    * @Description: get a collection of all facilities' names
    */
    public Collection<String> getFclts() {
        return fclts.keySet();
    }

    /** 
    * @Title: getDist
    * @Description: get a shortest distance between two facilities
    */
    public int getDist(String start,String end) throws ItemNotExistException {
        if (!(getFclts().contains(start) && getFclts().contains(end))) {
            throw new ItemNotExistException("Facility does not exist.");
        }
        ArrayList<String> bestPath=getBestPath(start,end);
        int length=0;
        int i=0;
        while (i<(bestPath.size()-1)) {
            length+=fclts.get(bestPath.get(i)).getNeighborDist(bestPath.get(i+1));
            i++; 
        }
        return length;
    }

    /** 
    * @Title: printBestPath
    * @Description: print out the BestPath between two facilities
    */    
    public void printBestPath(String start,String end) throws ItemNotExistException {
        if (!(getFclts().contains(start) && getFclts().contains(end))) {
            throw new ItemNotExistException("Facility does not exist.");
        }
        ArrayList<String> bestPath=getBestPath(start,end);
        int dist=getDist(start,end);
        //In phase 1, temporarily set the hourpD, milepH to be constants
        final float hourpD=8;
        final float milepH=50;
        
        float time=dist/hourpD/milepH;
        
        System.out.print(start+" to "+end+":"+"\n");
        System.out.print("    ·   ");
        
        int i=0;
        while (true) {
            if (i==(bestPath.size()-1)) {
                System.out.print(bestPath.get(i));
                break;
            }
            if (i==5) {
                System.out.print(bestPath.get(i)+"\n"+"         → ");
                i++;
                continue;
            }
            System.out.print(bestPath.get(i)+" → ");
            i++;
        }
        DecimalFormat df=new DecimalFormat("#,###"); 
        System.out.print(" = "+df.format(dist)+" mi"+"\n");
        
        System.out.print("    ·   "+df.format(dist)+" mi / ("+hourpD
                         +" hours per day * "+milepH+" mph) = ");
        System.out.printf("%1.2f days\n",time);
    }

    //Auxiliary method to get an array of facilities along a BestPath
    private ArrayList<String> getBestPath(String start,String end){
        BestPath bestpathInst=new BestPath(fclts);
        return bestpathInst.findBestPath(start,end);
    }
}
