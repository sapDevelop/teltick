package tags;

import java.util.Iterator;
import java.util.Vector;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.LoopTagSupport;

import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Ticket;
import modell.factory.DaoMitarbeiterFactory;
import modell.factory.DaoTicketFactory;
import modell.interfaces.DaoMitarbeiter;
import modell.interfaces.DaoTicket;

/**
 * For-Each-Schleife, die Ticket ausgibt
 * @author Robin Hake
 *
 */


public class TicketTag extends LoopTagSupport {
	
	
	
	private static final long serialVersionUID = 1L;
	private int stat;
	private Iterator<Ticket> itTicket;
	
	


	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	@Override
	protected boolean hasNext() throws JspTagException {
		
		return itTicket.hasNext();
	}

	@Override
	protected Object next() throws JspTagException {
		
		return itTicket.next();
	}

	@Override
	protected void prepare() throws JspTagException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int doStartTag() throws JspException {
		
				
//		DaoMitarbeiter dao = DaoMitarbeiterFactory.getInstance();
//		itMitarbeiter = dao.getAlleMitarbeiter(mitAdmin).iterator();
		
		
		if(stat == 0){
				
			// value == 0 -> Alle Mitarbeiter
				
			DaoTicket daoT = DaoTicketFactory.getInstance();
			itTicket = daoT.getAlleTickets().iterator();
			
		} else if (stat == 1)
		{
			
		} else if (stat == 2) {
			
		}
		
		return super.doStartTag();
	}	

}
