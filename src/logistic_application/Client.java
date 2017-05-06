/* This is Quarter Programming Project of SE450:
 *        "Logistics Application"
 * Author:  Kuangyi Zhang, Lanyixuan Xu.
 * Phase 1: Facilities, Transport Network & Item catalog.
 */

package logistic_application;

import java.util.ArrayList;
import java.util.Scanner;


/** 
* @ClassName: Client
* @Description: main driver to run the program
*/
public class Client {
    
	public static void main(String[] args) throws ItemNotExistException {
        
		OrderMgr.getInstance().printReport();
		
		Scanner sc=new Scanner(System.in);
        Boolean flag=true;
        while (flag) {
            System.out.println("Please select Output you want to test: (1/2/3)");
            System.out.println("Output 1: Facility Status Output for all 18 facilities");  
            System.out.println("Output 2: Item Catalog Content Output");
            System.out.println("Output 3: Example Shortest Path Output for 10 site-pairs");
            String select=sc.nextLine();
            if (!select.equals("1") && !select.equals("2") && !select.equals("3")) {
                System.out.println("Please enter 1/2/3.");
                continue;
            }
            switch (Integer.parseInt(select)) {
                //Output 1
                case 1:
                    System.out.println("Output 1: \n");
                    FacilityMgr.getInstance().printReport();
                    System.out.println("------------------------------------"
                                + "------------------------------------");
                    break;
                //Output 2
                case 2:
                    System.out.println("Output 2: \n");
                    ItemMgr.getInstance().printItemReport();
                    System.out.println();
                    System.out.println("------------------------------------"
                                + "------------------------------------");
                    break;
                //Output 3
                case 3:
                    System.out.println("Output 3: \n");
                    ArrayList<String> fclts=new ArrayList<String>(
                                FacilityMgr.getInstance().getFclts());
                    int[] start={1,13,9,2,6,0,4,11,8,10};
                    int[] end={5,17,16,3,4,11,14,9,5,16};
                    for (int i=0; i<10; i++) {
                        char index=(char) (i+97);
                        System.out.print(index+")  ");
                        FacilityMgr.getInstance().printBestPath(
                                    fclts.get(start[i]), fclts.get(end[i]));
                    }
                    System.out.println("------------------------------------"
                                + "------------------------------------");
                    break;
            }
            System.out.println("Do you want test anything else? (y/n)");
            select=sc.nextLine();
            if (select.toLowerCase().equals("n")) flag=false;
        }
    }

}
