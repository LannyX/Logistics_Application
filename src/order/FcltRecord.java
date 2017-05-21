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
public class FcltRecord {

    public int orderIdx;
    public String item;    
    public String fcltName;
    public int itemProcessed;
    public int procEndDay;
    public float travelDay;
    public int arrivalDay;

    public FcltRecord(int orderIdx, String item, String fcltName, int itemProcessed, int procEndDay, float travelDay, int arrivalDay) {
        this.orderIdx=orderIdx;
        this.item=item;
        this.fcltName = fcltName;
        this.itemProcessed = itemProcessed;
        this.procEndDay = procEndDay;
        this.travelDay = travelDay;
        this.arrivalDay = arrivalDay;
    }
    
    

    
}
