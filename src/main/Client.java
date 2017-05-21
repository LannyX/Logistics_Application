/* This is Quarter Programming Project of SE450:
 *        "Logistics Application"
 * Author:  Kuangyi Zhang, Lanyixuan Xu.
 * Phase 1: Facilities, Transport Network & Item catalog.
 */

package main;

import order.OrderMgr;
import facility.FacilityMgr;
import item.ItemMgr;
import exception.DataValidationException;
import exception.NullParamException;
import java.util.ArrayList;


/** 
* @ClassName: Client
* @Description: main driver to run the program
*/
public class Client {
    
	public static void main(String[] args) throws NullParamException, DataValidationException {
        
            OrderMgr.getInstance().printOrderSolution();
		
//            System.out.println("Output 1: Facility Status Output for all 18 facilities"); 
//            FacilityMgr.getInstance().printReport();
//            System.out.println("------------------------------------"
//                                + "------------------------------------");
//            
//            System.out.println("Output 2: Item Catalog Content Output");
//            ItemMgr.getInstance().printItemReport();
//            System.out.println();
//            System.out.println("------------------------------------"
//                                + "------------------------------------");
//            
//            
//            System.out.println("Output 3: Example Shortest Path Output for 10 site-pairs");
//            ArrayList<String> fclts=new ArrayList<String>(
//                                FacilityMgr.getInstance().getFclts());
//            int[] start={1,13,9,2,6,0,4,11,8,10};
//            int[] end={5,17,16,3,4,11,14,9,5,16};
//            for (int i=0; i<10; i++) {
//                char index=(char) (i+97);
//                System.out.print(index+")  ");
//                FacilityMgr.getInstance().printBestPath(
//                                    fclts.get(start[i]), fclts.get(end[i]));
//            }
//            System.out.println("------------------------------------"
//                                + "------------------------------------");

        
    }

}
