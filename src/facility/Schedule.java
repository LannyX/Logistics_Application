package facility;

import java.util.HashMap;
import java.util.Map;


/** 
* @ClassName: Schedule
* @Description: a class to keep track of facility processing availability
*/
public class Schedule {

	private Map<Integer, Integer> schedule;
	
	/** 
    * @Title: Schedule  
    * @Description: create a schedule(Map) according to fcltRate
    */
	public Schedule (int fcltRate){
            Map <Integer, Integer> schedule = new HashMap<Integer, Integer>();
            for (int i = 1; i <= 100; i++ ){
                schedule.put(i, fcltRate);
            }
            this.schedule = schedule;
	}
	
	
	/** 
    * @Title: printSchd
    * @Description: print out the schedule report
    */
	public void printSchd(){
        //In phase 1, temporarily limit the output shcdule to day 20
		System.out.println("Schedule: ");
		System.out.print("Day:         ");
		for (int i = 1; i<= 20; i++){
			String day = Integer.toString(i);
			System.out.printf("%3s",day);
		}
		System.out.println();
		System.out.print("Available:   ");
		for (int i =1; i <= 20; i++){
			String available = Integer.toString(schedule.get(i));
			System.out.printf("%3s",available);
		}
        System.out.println();
	}
        
        //calculate end day for processing (a part of) order
        public int getEndDay (int startDay, int qtt) {
            while (true) {
                //System.out.printf("startDay: Day %d\n",startDay);
                int left=schedule.get(startDay);
                if (qtt<=left) return startDay;
                else {
                    qtt-=left;
                    startDay++;
                }
            }
        }
        
        //book days        
        public void bookSchd (int startDay, int qtt) {
            while (true) {
                int left=schedule.get(startDay);
                if (qtt<=left) {
                    schedule.put(startDay,left-qtt);
                    break;
                }
                else {
                    schedule.put(startDay,0);
                    qtt-=left;
                    startDay++;
                }
            }
        }
}
