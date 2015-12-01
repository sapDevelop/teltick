package modell.factory;

import modell.implementierung.ImpRowMappingTicket;
import modell.interfaces.RowMappingTicket;

public class RowMappingTicketSingletonFactory {
	
	private static RowMappingTicket obj = null;
	public static RowMappingTicket getInstance(){
		if (obj == null) obj = new ImpRowMappingTicket();
		return obj;
	}

}
