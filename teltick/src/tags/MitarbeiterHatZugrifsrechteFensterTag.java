package tags;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import logger.LogFactory;
import modell.entitaeten.factory.FensterFactory;
import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Mitarbeiter;


public class MitarbeiterHatZugrifsrechteFensterTag  extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	private int fensterId;
	private Mitarbeiter benutzer;
	
	private static Logger log = LogFactory.getInstance(MitarbeiterHatZugrifsrechteFensterTag.class.getName());

	@Override
	public int doEndTag() throws JspException {
		
		HttpSession session = pageContext.getSession();
		Mitarbeiter m = benutzer == null ?  (Mitarbeiter) session.getAttribute("angemeldeterMitarbeiter") : benutzer;
		
		JspWriter out = getBodyContent().getEnclosingWriter();
		
		Fenster f = FensterFactory.getInstance();
		f.setId(fensterId);
		if (m != null && m.zugriffsRechtFenster(f) ){
			
			try {
				out.print(getBodyContent().getString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		return EVAL_PAGE;
	}
	
	//public 

	@Override
	public int doStartTag() throws JspException {
				
		return EVAL_BODY_BUFFERED; 
	}

	public int getFensterId() {
		return fensterId;
	}

	public void setFensterId(int fensterId) {
		this.fensterId = fensterId;
	}

	public Mitarbeiter getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Mitarbeiter benutzer) {
		log.info("Benutzer gewählt: " + benutzer.getLoginName());
		this.benutzer = benutzer;
	}
	
	
}
