package order;

import exception.DataValidationException;
import exception.NullParamException;
import facility.FacilityMgr;
import item.ItemMgr;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Order processor. It collaborates with order manager to process 
 * all orders and comes up with solutions.
 */
public class OrderProcessor {
    
    private ArrayList<FcltRecord> solutions;
    private static OrderProcessor orderProc;
    
    private OrderProcessor() {
        solutions=new ArrayList<FcltRecord>();
    }
    
    public static OrderProcessor getInstance(){
        if (orderProc==null) orderProc = new OrderProcessor();
        return orderProc;
    }
    
    
    public void procAllOrder()throws NullParamException,DataValidationException{
        for (int orderIdx=1;orderIdx<=OrderMgr.getInstance().getOrderCount();orderIdx++){
            OrderDTO order=OrderMgr.getInstance().getOrder(orderIdx);
            procOneOrder(order,orderIdx);
        }
    }
    
    
    private void procOneOrder(OrderDTO order,int orderIdx)
                    throws NullParamException,DataValidationException{

        final float hourpD=8;
        final float milepH=50;
        
        RecordComparator comparator=new RecordComparator();
        
        for(OrderItem itemInf: order.items){
            //Verify Item is a “real” item from the item Catalog
            String item=itemInf.getItem();
            if (!ItemMgr.getInstance().itemExist(item)) {
                System.out.println(item+" does not exist.");
                continue;
            }
            
            int requiredQtt=itemInf.getQtt();
            //While Qtt remaining
            while (requiredQtt>0) {
                //Identify all facilities with desired item
                HashMap<String,Integer> fcltsOwnItem=FacilityMgr.getInstance().getFcltsOwnItem(item, order.dest);
                if (!fcltsOwnItem.isEmpty()) {
                    ArrayList<FcltRecord> records=new ArrayList<>();
                    //For each identified facility
                    for (String fclt: fcltsOwnItem.keySet()) {

                        //Determine processing Qtt
                        int processed;
                        int fcltOwnQtt=fcltsOwnItem.get(fclt);
                        if (fcltOwnQtt<=requiredQtt) processed=fcltOwnQtt;
                        else processed=requiredQtt;

                        //Calculate the shortest path (in days) from fclt to dest
                        int dist=FacilityMgr.getInstance().getDist(fclt, order.dest);
                        float travelDay=dist/hourpD/milepH;
                        //Determine processing end day
                        int procEndDay=FacilityMgr.getInstance().getProcEndDayAtFclt(fclt, order.time, processed);
                        //Generate the “Arrival Day”
                        int arrivalDay=(int) Math.ceil(travelDay+procEndDay);
                        //Save this information as a Facility Record
                        FcltRecord fcltRcd=new FcltRecord(orderIdx,item,fclt,processed,procEndDay,travelDay,arrivalDay);
                        records.add(fcltRcd);
                    }
                    
                    //Select the facility with the earliest (lowest) Arrival Day
                    records.sort(comparator);
                    FcltRecord topRcd=records.get(0);
                    //Reduce the inventory of the selected site by the number of items in the Facility Record
                    FacilityMgr.getInstance().reduceItemAtFclt(topRcd.fcltName,item,topRcd.itemProcessed);
                    //Reduce the Order Item quantity needed by the number of items processed in the Facility Record
                    requiredQtt-=topRcd.itemProcessed;
                    //Update the schedule of the selected site (book the time represented in the Site Record)
                    FacilityMgr.getInstance().bookSchdAtFclt(topRcd.fcltName,order.time,topRcd.itemProcessed);
                    //Add this Facility Record to the order Item solution list
                    solutions.add(topRcd);
                    
                }
                else {
                    //backorder reminder
                    System.out.printf("The requiring item %s is beyond storage quantity.\n",item);
                    System.out.printf("Excess quantity: %d\n",requiredQtt);
                    break;
                }
            }
        }
    }
    
    private void printOneSolution(int orderIdx) throws NullParamException, DataValidationException{
        System.out.println("\n  Processing Solution:");
        DecimalFormat df=new DecimalFormat("#,###.00"); 
        float totalCost=0;
        
        String lastItem="";
        boolean isFirst=true;
        int itemQtt=0;
        float itemCost=0;

        int arriveDayMin=0, arriveDayMax=0;
        int printIdx=1;
        
        
        for(FcltRecord record:solutions){
            if (orderIdx==record.orderIdx) {
                
                String item=record.item;  
                float cost=getRecordCost(record);
		String costS = df.format(cost);
                
                if (!item.equals(lastItem)) {
                    if (!isFirst) {
                        totalCost+=itemCost;
                        
                        String itemCostS=df.format(itemCost); 
                        if (arriveDayMin==arriveDayMax){
                            System.out.printf("          TOTAL                 %-14d$%-23s[%d]\n\n",
                                                itemQtt,itemCostS,arriveDayMin); 
                        }
                        else {
                            System.out.printf("          TOTAL                 %-14d$%-23s[%d - %d]\n\n",
                                                itemQtt,itemCostS,arriveDayMin,arriveDayMax);    
                        }
                    }
                    isFirst=false;
                    printIdx=1;
                    itemQtt=record.itemProcessed; 
                    itemCost=cost;
                    arriveDayMin=record.arrivalDay;
                    arriveDayMax=record.arrivalDay;
                    lastItem=item;
                    
                    System.out.println("  "+item+":");
                    System.out.println("          Facility              Quantity"
                            + "      Cost                    Arrival Day");
                    System.out.printf("      %d)  %-22s%-14d$%-23s%d\n",
                            printIdx,record.fcltName,record.itemProcessed,costS,record.arrivalDay);

                }
                else {
                    printIdx++;
                    System.out.printf("      %d)  %-22s%-14d$%-23s%d\n",
                            printIdx,record.fcltName,record.itemProcessed,costS,record.arrivalDay);
                    lastItem=item;
                    
                    itemQtt+=record.itemProcessed;
                    itemCost+=cost;
                    
                    if (record.arrivalDay<arriveDayMin) arriveDayMin=record.arrivalDay;
                    if (record.arrivalDay>arriveDayMax) arriveDayMax=record.arrivalDay;
                    
                }
            }
        }
        totalCost+=itemCost;
                        
        String itemCostS=df.format(itemCost); 
        if (arriveDayMin==arriveDayMax){
            System.out.printf("          TOTAL                 %-14d$%-23s[%d]\n\n",
                            itemQtt,itemCostS,arriveDayMin); 
        }
        else {
            System.out.printf("          TOTAL                 %-14d$%-23s[%d - %d]\n\n",
                            itemQtt,itemCostS,arriveDayMin,arriveDayMax);    
        }
        
        String totalCostS=df.format(totalCost);
        System.out.printf("  Total Cost:        $%s\n",totalCostS);
    }
    
    private float getRecordCost(FcltRecord record) throws NullParamException, DataValidationException{
        String fclt=record.fcltName;
        String item=record.item;
        
        float itemCost=(float) (record.itemProcessed)*(ItemMgr.getInstance().getItemPrice(item));
        float fcltProcCost=(float) (record.itemProcessed)
                            /(FacilityMgr.getInstance().getFcltRate(fclt))
                            *(FacilityMgr.getInstance().getFcltCost(fclt));
        
        final float costpD=500;        
        float travelCost=(float) Math.ceil(record.travelDay)*costpD;
        
        //System.out.printf("      itemCost: %f;      fcltProcCost: %f;      travelCost: %f\n",itemCost,fcltProcCost,travelCost);
        return (itemCost+fcltProcCost+travelCost);
    }
    
    
    public void printAllOrderSolution() throws NullParamException, DataValidationException{
        procAllOrder();
        for (int orderIdx=1;orderIdx<=OrderMgr.getInstance().getOrderCount();orderIdx++){
            OrderMgr.getInstance().printOneOrder(orderIdx-1);
            printOneSolution(orderIdx);
        }
        System.out.println("----------------------------------------------------"
                              + "---------------------------\n");
    }
    
}
