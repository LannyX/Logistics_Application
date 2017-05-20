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
    private ArrayList<Solution> solutions;
    
    
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
        
        for(String item: order.items.keySet()){
            if (!ItemMgr.getInstance().itemExist(item)) {
                System.out.println(item+" does not exist.");
                continue;
            }
            int requiredQtt=order.items.get(item);
            HashMap<String,Integer> fcltsOwnItem=FacilityMgr.getInstance().getFcltsOwnItem(item, order.dest);
            if (!fcltsOwnItem.isEmpty()) {
                for (String fclt: fcltsOwnItem.keySet()) {
                    
                    int fcltQtt=fcltsOwnItem.get(fclt);
                    int processed;
                    if (fcltQtt<=requiredQtt) processed=fcltQtt;
                    else processed=requiredQtt;
                    
                    //determine travel day
                    int dist=FacilityMgr.getInstance().getDist(fclt, order.dest);
                    float travelTime=dist/hourpD/milepH;
                    //determine processing end day
                    int endDay=FacilityMgr.getInstance().getProcEndDayAtFclt(fclt, order.time, processed);
                    
                    int arrivalDay=(int) Math.ceil(travelTime+endDay);
                    
                    FcltRecord fcltRC=new FcltRecord(fclt,processed,endDay,travelTime,arrivalDay);

                    
                    
                }
            }
            else {
                //generate back-order
            }

            
            
            
        }
            
    }


    
    //方法3：processtop 更改fclt信息
    //      1. 调用fcltMgr，更改 fclt inventory（item number）；
    //      2. 同上，更改schedule；
    //      3. 生成或添加到solution list
    
}
