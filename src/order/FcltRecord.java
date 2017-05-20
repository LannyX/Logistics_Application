/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

/**
 *
 * @author dell
 */
public class FcltRecord implements Comparable<FcltRecord>  {
    public String fcltName;
    public int itemProcessed;
    public int processEndDay;
    public float travelTime;
    public int arrivalDay;

    public FcltRecord(String fcltName, int itemProcessed, int processEndDay, float travelTime, int arrivalDay) {
        this.fcltName = fcltName;
        this.itemProcessed = itemProcessed;
        this.processEndDay = processEndDay;
        this.travelTime = travelTime;
        this.arrivalDay = arrivalDay;
    }
    
    
    public int compareTo(FcltRecord other) {
        return (arrivalDay-other.arrivalDay);
    }


    
}
