package de.justinsprenger.mobil.database;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Dateandtime {
	public Date d = new Date();
		
	public static String getTime(){
		long yourmilliseconds = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");    
		Date resultdate = new Date(yourmilliseconds);
		String time=String.valueOf(sdf.format(resultdate));
		
		return time;
	}
	
	public static String getDate(){
		long yourmilliseconds = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("dd,MM,yyyy");    
		Date resultdate = new Date(yourmilliseconds);
		String date=String.valueOf(sdf.format(resultdate));
		
		return date;
	}
	
}
