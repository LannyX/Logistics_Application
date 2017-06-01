package order;

/**
 * Object to store record of a facility when processing order. 
 */
public class FcltRecord {

    public int orderIdx;
    public String item;    
    public String fcltName;
    public int itemProcessed;
    public int procEndDay;
    public float travelDay;
    public int arrivalDay;

    public FcltRecord(int orderIdx, String item, String fcltName, int itemProcessed, 
                                int procEndDay, float travelDay, int arrivalDay) {
        this.orderIdx=orderIdx;
        this.item=item;
        this.fcltName = fcltName;
        this.itemProcessed = itemProcessed;
        this.procEndDay = procEndDay;
        this.travelDay = travelDay;
        this.arrivalDay = arrivalDay;
    }
    
    

    
}
