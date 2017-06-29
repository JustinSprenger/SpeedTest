package de.justinsprenger.mobil.database;
import java.util.Date;
public class Time {
	Time(){
		
	}
	
	public String getTime(){
		Date t1 = new Date();
		String time = t1.getHours()+":"+t1.getMinutes()+":"+t1.getSeconds();
		return time;
	}
	
	public String getDate(){
		Date d1 = new Date();
		String date = d1.getDate()+"";
		return date;
	}
}
