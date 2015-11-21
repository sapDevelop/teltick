package tags;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import basis.factory.VorlagenFensterFactory;

public class BestaetigungsboxTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String icon, titel;
	private String breite, hoehe;
	private String aktion, methode;
	private boolean submitUmleiten = false;
	private String dialogKennung;
	

	public String getDialogKennung() {
		return dialogKennung;
	}

	public void setDialogKennung(String dialogKennung) {
		this.dialogKennung = dialogKennung;
	}

	public boolean isSubmitUmleiten() {
		return submitUmleiten;
	}

	public void setSubmitUmleiten(boolean submitUmleiten) {
		this.submitUmleiten = submitUmleiten;
	}

	public String getAktion() {
		return aktion;
	}

	public void setAktion(String aktion) {
		this.aktion = aktion;
	}

	public String getMethode() {
		return methode;
	}

	public void setMethode(String methode) {
		this.methode = methode;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	public String getHoehe() {
		return hoehe;
	}

	public void setHoehe(String hoehe) {
		this.hoehe = hoehe;
	}

	public String getBreite() {
		return breite;
	}

	public void setBreite(String breite) {
		this.breite = breite;
	}

	@Override
	public int doEndTag() throws JspException {
				
		//Bestimmt die Id des Div, wo das Ergebnis der Berabeitung angezeigt werden soll,
		//falls der Submit umgeleitet werden soll
		int id = -1;
		if (submitUmleiten){
			if ( pageContext.getRequest().getParameter("id") != null){
				id = Integer.valueOf(pageContext.getRequest().getParameter("id"));
			}else if(pageContext.getRequest().getAttribute("id") != null){
				id = (int) pageContext.getRequest().getAttribute("id");
			}
			else if(pageContext.getSession().getAttribute("id") != null){
				id = (int) pageContext.getSession().getAttribute("id");
			}
		}
		
		int idFensterUmleitung = !submitUmleiten ? -1 : Integer.valueOf(pageContext.getRequest().getParameter("id"));
			
		String body = getBodyContent().getString();
		String meldungsbox =  VorlagenFensterFactory.getInstance().getBestaetigungsBox(body, titel, breite, hoehe, icon, pageContext.getSession(), false,methode, aktion, dialogKennung ,pageContext.getRequest(),idFensterUmleitung);
		
		JspWriter out = getBodyContent().getEnclosingWriter();
		try {
			out.print(meldungsbox);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}
}
