package logistic_application;

import java.util.ArrayList;




//just call this class client for now, we can always change name later.
public class Client {

    
    
	public static void main(String[] args) {
                //Output1:
                System.out.println("Output 1: \n");
                FacilityMgr.getInstance().printReport();
                System.out.println("------------------------------------------------------------------------\n");
                
                 //Output2:
                System.out.println("Output 2: \n");                
                ItemMgr.getInstance().printItemReport();
                System.out.println();
                System.out.println("------------------------------------------------------------------------\n");
                
                //Output3:
                System.out.println("Output 3: \n");
                ArrayList<String> fclts=new ArrayList<String>(FacilityMgr.getInstance().getFclts());
                int[] start={1,13,9,2,6,0,4,11,8,10};
                int[] end={5,17,16,3,4,11,14,9,5,16};
                for (int i=0; i<10; i++) {
                    char index=(char) (i+97);
                    System.out.print(index+")  ");
                    FacilityMgr.getInstance().printBestPath(fclts.get(start[i]), fclts.get(end[i]));
                }
                
//                i=0;
//                for(String fclt:fclts) {
//                    i++;
//                    System.out.printf("%2d: %-30s",i,fclt);
//                    if (i%3==0) System.out.println();
//                }
                
                
                //FacilityMgr.getInstance().printBestPath("Denver, CO", "Miami, FL");
	}

}
