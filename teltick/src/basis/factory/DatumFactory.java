package basis.factory;

import basis.implementierung.ImpDatum;
import basis.implementierung.ImpFeldFehlermeldung;
import basis.interfaces.Datum;
import basis.interfaces.FeldFehlermeldung;

public class DatumFactory {

	private static Datum time;
	
	public static Datum getInstance(){
		
		if(time != null){
			
			return time;
		}else
		{
			time = new ImpDatum();
			return time;
		}
		
		
	}
	
	
}
