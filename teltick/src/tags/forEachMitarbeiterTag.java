package tags;

import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.LoopTagSupport;

import modell.entitaeten.interfaces.Mitarbeiter;
import modell.factory.DaoMitarbeiterFactory;
import modell.interfaces.DaoMitarbeiter;


/**
 * For-Each-Schleife, die alle Mitarbeiter ausgibt
 * @author benedikt
 *
 */
public class forEachMitarbeiterTag extends LoopTagSupport{

	private static final long serialVersionUID = 1L;
	private boolean mitAdmin = true;
	private Iterator<Mitarbeiter> itMitarbeiter;
	

	public boolean isMitAdmin() {
		return mitAdmin;
	}

	public void setMitAdmin(boolean mitAdmin) {
		this.mitAdmin = mitAdmin;
	}

	@Override
	protected boolean hasNext() throws JspTagException {
		return itMitarbeiter.hasNext();
	}

	@Override
	protected Object next() throws JspTagException {
		// TODO Auto-generated method stub
		return itMitarbeiter.next();
	}

	@Override
	protected void prepare() throws JspTagException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int doStartTag() throws JspException {
				
		DaoMitarbeiter dao = DaoMitarbeiterFactory.getInstance();
		itMitarbeiter = dao.getAlleMitarbeiter(mitAdmin).iterator();
		
		return super.doStartTag();
	}	
	
	
	
}
