package tags;

import java.util.Iterator;
import java.util.Vector;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.LoopTagSupport;

import modell.entitaeten.interfaces.Recht;
import modell.factory.DaoMitarbeiterFactory;
import modell.interfaces.DaoMitarbeiter;

public class forEachRechtTag extends LoopTagSupport {

	private static final long serialVersionUID = 1L;
	
	/**
	 * For-Each-Schleife, die alle Rechte für einen Mitarbeiter durchläuft
	 * @author Robin Hake
	 *
	 */
	
	private int mitarbeiterId;
	private Vector<Recht> r;
	private Iterator<Recht> ri;
	
	public void setMitarbeiterId (int mitarbeiterId) {
		this.mitarbeiterId = mitarbeiterId;
	}
	
	@Override
	protected boolean hasNext() throws JspTagException {
		// TODO Auto-generated method stub
		return ri.hasNext();
	}

	@Override
	protected Object next() throws JspTagException {
		// TODO Auto-generated method stub
		return ri.next();
	}

	@Override
	protected void prepare() throws JspTagException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int doStartTag() throws JspException {
				
		
		DaoMitarbeiter dao = DaoMitarbeiterFactory.getInstance();
		r = dao.getMitarbeiterRechte(mitarbeiterId);
		ri = r.iterator();
		
		return super.doStartTag();
	}	
	

}
