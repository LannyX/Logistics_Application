/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import exception.DataValidationException;
import exception.NullParamException;
import facility.FacilityMgr;
import item.ItemMgr;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderProcessor {
    
    //存放单个order的solution
    private ArrayList<FcltRecord> solutions;
    
    
    //方法1：处理多个order：
    //      1. 调用orderMgr，获得每个orderDTO；
    //      2. 调用方法2，求解；
    //      3. 生成solution的集合
   
    
    //方法2：处理单个order。
    //      1.调用fcltMgr，读取fclt的item信息生成fclt集合(hashmap)、从fclt到指定地点的shortestpath->travel time，fclt的process time，fclt的ArrivalDay
    //      2. 生成fclt record；
    //      3. 排序，对最优解调用processTop；
    private void oneOrderProc (OrderDTO order)
                    throws NullParamException,DataValidationException{
        //Temporarily set the hourpD, milepH to be constants
        final float hourpD=8;
        final float milepH=50;
        
        RecordComparator comparator=new RecordComparator();
        
        for(String item: order.items.keySet()){
            if (!ItemMgr.getInstance().itemExist(item)) {
                System.out.println(item+" does not exist.");
                continue;
            }
            
            int requiredQtt=order.items.get(item);
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
                        float travelTime=dist/hourpD/milepH;
                        //Determine processing end day
                        int endDay=FacilityMgr.getInstance().getProcEndDayAtFclt(fclt, order.time, processed);
                        //Generate the “Arrival Day”
                        int arrivalDay=(int) Math.ceil(travelTime+endDay);
                        //Save this information as a Facility Record
                        FcltRecord fcltRcd=new FcltRecord(item,fclt,processed,endDay,travelTime,arrivalDay);
                        records.add(fcltRcd);
                    }
                    
                    //Select the facility with the earliest (lowest) Arrival Day
                    records.sort(comparator);
                    FcltRecord selectedRcd=records.get(0);
                    //进行方法3部分的处理，是否另外定义或者直接写？-TBC
                    
                    
                }
                else {
                    //generate back-order
                }

            }
            
            
        }
            
    }


    
    //方法3：processtop 更改fclt信息
    //      1. 调用fcltMgr，更改 fclt inventory（item number）；
    //      2. 同上，更改schedule；
    //      3. 生成或添加到solution list
    
}
