package basis.implementierung;

import java.sql.Timestamp;
import java.util.Calendar;

import basis.interfaces.Datum;

public class ImpDatum implements Datum{
	
	public Timestamp getDatum(){
		
		// 1) create a java calendar instance
		Calendar calendar = Calendar.getInstance();
					 
		// 2) get a java.util.Date from the calendar instance.
		//this date will represent the current instant, or "now".
		java.util.Date now = calendar.getTime();
		
		// 3) Timestamp with the current time
		Timestamp time = new Timestamp(now.getTime());
			
		return time;
	}
	
	
	
	

}
