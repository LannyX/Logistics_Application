package logistic_application;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class FacilityMgr {

    //HashMap stores name-facility pairs
    private HashMap<String, Facility> fclts;
    
    
    private static FacilityMgr fcltMgr;

    private FacilityMgr(){
        //Load facility data 
        fclts = FacilityLoader.load();
    }

    public static FacilityMgr getInstance(){
        if (fcltMgr==null) fcltMgr = new FacilityMgr();
        return fcltMgr;
    }
    
    //Method to print out Facility Status Output
    public void printReport(){
        Collection<Facility> fcltObj=fclts.values();
        for (Facility facility: fcltObj){
            facility.printFacility();
        }
    }
    
    //Method to get a collection of one facility's neighbors' names
    public Collection<String> getNeighbors(String fcltname) {
        return fclts.get(fcltname).getNeighbors();
    }
    
    //Method to get a collection of all facilities' names
    public Collection<String> getFclts() {
        return fclts.keySet();
    }
    
    //Auxiliary method to get an array of facilities along a BestPath
    private ArrayList<String> getBestPath(String start,String end) {
        BestPath bestpathInst=new BestPath(fclts);
        return bestpathInst.findBestPath(start,end);
    }
    
    //Method to get a shortest distance between two facilities
    public int getDist(String start,String end) {
        ArrayList<String> bestPath=getBestPath(start,end);
        int length=0;
        int i=0;
        while (i<(bestPath.size()-1)) {
            length+=fclts.get(bestPath.get(i)).getNeighborDist(bestPath.get(i+1));
            i++; 
        }
        return length;
    }
    
    //Method to print out the BestPath between two facilities    
    public void printBestPath(String start,String end){
        ArrayList<String> bestPath=getBestPath(start,end);
        int dist=getDist(start,end);
        //hourpD, milepH should be input paras
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
    
    

}
