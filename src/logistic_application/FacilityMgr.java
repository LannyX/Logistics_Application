package logistic_application;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class FacilityMgr {

    //create ArrayList to put facility
    private HashMap<String, Facility> fclts;
    
    private static FacilityMgr fcltMgr;

    //load the file in c'tor   
    private FacilityMgr(){
        fclts = FacilityLoader.load();
    }

    public static FacilityMgr getInstance(){
        if (fcltMgr==null) fcltMgr = new FacilityMgr();
        return fcltMgr;
    }
	 
    public void printReport(){
        Collection<Facility> fcltObj=fclts.values();
        for (Facility facility: fcltObj){
            facility.printFacility();
        }
    }
    
    
    
    public Collection<String> getNeighbors(String fcltname) {
        return fclts.get(fcltname).getNeighbors();
    }
    
    public Collection<String> getFclts() {
        return fclts.keySet();
    }
    
    
    private ArrayList<String> getBestPath(String start,String end) {
        BestPath bestpathInst=new BestPath(fclts);
        return bestpathInst.findBestPath(start,end);
    }
    
    public int getDist(String start,String end) {
        BestPath bestPathInst=new BestPath(fclts);
        ArrayList<String> bestPath=bestPathInst.findBestPath(start,end);
        return bestPathInst.getLength(bestPath);
    }
    
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
        
        System.out.print("    ·   "+df.format(dist)+" mi / ("+hourpD+" hours per day * "+milepH+" mph) = ");
        System.out.printf("%1.2f days\n",time);
    }
    
    

}
