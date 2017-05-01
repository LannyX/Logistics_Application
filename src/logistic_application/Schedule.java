package logistic_application;

import java.util.Map;

public class Schedule {

	private Map<Integer, Integer> scheduleMap;
	
	public Schedule (Map<Integer, Integer> scheduleMap){
		this.scheduleMap = scheduleMap;
	}
	
	public int get(int day){
		return scheduleMap.get(day);
	}

	public int size(){
		return scheduleMap.size();
	}
	
	public void printSche(){
		System.out.println("Schedule: ");
		System.out.print("Day:          ");
		for (int i = 1; i<= scheduleMap.size(); i++){
			String day = Integer.toString(i);
			if (day.length() == 1)
				day = " " + day;
			System.out.print( day + " ");
		}
		
		System.out.println();
		System.out.print("Available:     ");
		for (int i =1; i <= scheduleMap.size(); i++){
			String available = Integer.toString(scheduleMap.get(i));
		//	
			System.out.print( available + " ");
		}
        System.out.println();
	}
}
