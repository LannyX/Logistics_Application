package logistic_application;

import java.util.HashMap;
import java.util.Map;


/** 
* @ClassName: Schedule
* @Description: a class to keep track of facility processing availability
*/
public class Schedule {

	private Map<Integer, Integer> schedule;
	
	/** 
    * @Title: Schedule (c'tor) 
    * @Description: create a schedule(Map) according to fcltRate
    */
	public Schedule (int fcltRate){
            Map <Integer, Integer> schedule = new HashMap<Integer, Integer>();
            for (int i = 1; i <= 50; i++ ){
                schedule.put(i, fcltRate);
            }
            this.schedule = schedule;
	}
	
	/** 
    * @Title: get 
    * @Description: to get processing capacity of one day
    */
	public int get(int day){
        //to be modified in phase 2
        return schedule.get(day);
	}

	/** 
    * @Title: update
    * @Description: update schedule according to startDay and to-be-processed items
    */
	public void update(int startDay, int items){
            //to be modified in phase 2
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
}
