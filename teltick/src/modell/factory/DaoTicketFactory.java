package modell.factory;


import modell.implementierung.ImpDaoTicket;
import modell.interfaces.DaoTicket;

public class DaoTicketFactory {
	public static DaoTicket getInstance(){
		return new ImpDaoTicket();
	}
}
