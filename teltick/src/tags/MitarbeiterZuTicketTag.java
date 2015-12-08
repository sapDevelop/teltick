package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Ticketzuweisung;
import modell.factory.DaoMitarbeiterFactory;
import modell.factory.DaoTicketFactory;
import modell.interfaces.DaoTicket;

/**
 * Gibt den zugewiesenen Mitarbeiter für ein Ticket aus
 * @author Robin Hake
 *
 */


public class MitarbeiterZuTicketTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	private int ticketId;
	
	
	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int doStartTag() throws JspException {
		
		
		DaoTicket daoT = DaoTicketFactory.getInstance();
		Ticketzuweisung tz = daoT.getTicketzuweisung(ticketId);
		Mitarbeiter m = DaoMitarbeiterFactory.getInstance().getMitarbeiter(tz.getMitarbeiterId());
		
		
		
		JspWriter out=pageContext.getOut();//returns the instance of JspWriter  
	    try{  
	     out.print(m.getVorname() + " " + m.getName());//printing date and time using JspWriter  
	    }catch(Exception e){System.out.println(e);}  
	    return SKIP_BODY;//will not evaluate the body content of the tag  
		
		
	}

		
		
		
		
		
	

}
