package tags;

import java.util.Iterator;
import java.util.Vector;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.LoopTagSupport;

import org.apache.log4j.Logger;

import controller.BenutzerverwaltungController;
import logger.LogFactory;
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
	
	private static Logger log = LogFactory.getInstance(LoopTagSupport.class.getName());
	
	private static final long serialVersionUID = 1L;
	private int stat;
	private String suche;
	private Iterator<Ticket> itTicket;
	
	
	public String getSuche() {
		return suche;
	}

	public void setSuche(String suche) {
		this.suche = suche;
	}

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
				
		
		if(stat == 0){
				
			// Eigene Tickets
			
			Mitarbeiter m = (Mitarbeiter) pageContext.getSession().getAttribute("angemeldeterMitarbeiter");
						
			DaoTicket daoT = DaoTicketFactory.getInstance();
			itTicket = daoT.getTicketsFromMitarbeiter(m).iterator();
			
		} else if (stat == 1)
		{
			if(!suche.isEmpty())
			{
				log.info("Suchbegriff" + suche );
				DaoTicket daoT = DaoTicketFactory.getInstance();
				itTicket = daoT.getTicktSuche(suche).iterator();
			}
			
		} else  {
			
			// Alle Tickets
			
						DaoTicket daoT = DaoTicketFactory.getInstance();
						itTicket = daoT.getAlleTickets().iterator();
		}
		
		return super.doStartTag();
	}	

}
