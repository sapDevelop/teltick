package tags;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import logger.LogFactory;

import org.apache.log4j.Logger;

import controller.BenutzerverwaltungController;

public class EintragInVector extends BodyTagSupport {
	
	private static Logger log = LogFactory.getInstance(EintragInVector.class.getName());

	private static final long serialVersionUID = 1L;
	private String  eintrag;
	private String vector;

	public String getVector() {
		return vector;
	}

	public void setVector(String vector) {
		this.vector = vector;
	}

	public String getEintrag() {
		return eintrag;
	}

	public void setEintrag(String eintrag) {
		this.eintrag = eintrag;
	}

	@Override
	public int doEndTag() throws JspException {
		
		JspWriter out = getBodyContent().getEnclosingWriter();
		boolean gefunden = false;
		
		Vector<String> v = null;
		try{
			if (pageContext.getRequest().getAttribute(vector) != null){
				v = (Vector<String>)pageContext.getRequest().getAttribute(vector);
			}else if (pageContext.getSession().getAttribute(vector) != null){
				v= (Vector<String>)pageContext.getSession().getAttribute(vector);
			}
		}catch(ClassCastException e){
			log.error("Der Werte der Vector-Varibable konnte nicht zum Vector<String> gecastet werden.");
		}
		
		if (v != null){
			for (int i = 0; i < v.size() && !gefunden ; i++){
				if (v.get(i).equals(eintrag)) {
					gefunden = true;
					try {
						out.print(getBodyContent().getString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					log.info("Das Feld \"" + eintrag +"\" wird rot markiert.");
				}
			}
		}
		
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}


	
}
