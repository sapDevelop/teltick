package tags;

import java.io.IOException;

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
	private int submitUmleitenNachFensterId = -1;
	private String dialogKennung;
	
	
			
	public String getDialogKennung() {
		return dialogKennung;
	}

	public void setDialogKennung(String dialogKennung) {
		this.dialogKennung = dialogKennung;
	}

	public int getSubmitUmleitenNachFensterId() {
		return submitUmleitenNachFensterId;
	}

	public void setSubmitUmleitenNachFensterId(int submitUmleitenNachFensterId) {
		this.submitUmleitenNachFensterId = submitUmleitenNachFensterId;
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
		
		
		String body = getBodyContent().getString();
		String meldungsbox =  VorlagenFensterFactory.getInstance().getBestaetigungsBox(body, titel, breite, hoehe, icon, pageContext.getSession(), false,methode, aktion, dialogKennung ,submitUmleitenNachFensterId);
		
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
