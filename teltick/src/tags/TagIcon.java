package tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import modell.entitaeten.interfaces.Fenster;
import modell.entitaeten.interfaces.Mitarbeiter;
import modell.entitaeten.interfaces.Recht;

public class TagIcon extends TagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ort;
	
	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	@Override
	public int doStartTag() throws JspException {
		
		JspWriter out = pageContext.getOut();
		
		Mitarbeiter m = (Mitarbeiter) pageContext.getSession().getAttribute("angemeldeterMitarbeiter");
		
		for (int i = 0; i < m.getRechte().size(); i++)
					{
			
				Recht r = m.getRechte().get(i);
				
				Fenster f = r.getZugehoerigesFenster();		
				
				try {
					String tagClassKombination = "";
					String tagClassSchrift = "";
					switch (ort){
						case "startmenue":
							tagClassSchrift = "icon_text_kombination_-_text_startmenu";
						break;
						case "desktop":
							tagClassSchrift = "icon_text_kombination_-_text_desktop";
							tagClassKombination = "desktop_icons";
						break;
					}
					String ausgabe = ""
							+ "<span class=\"icon_text_kombination " + tagClassKombination + "\" onclick=\"oeffne_fenster(" + f.getId() + ");\" title=\""+ f.getTitel() + "\" >"
							+ "		<div><img src=\"" + f.getIcon() + "\" alt=\"" + f.getTitel() + "\"  class=\"icon_text_kombination_-_icon\"/></div>"
							+ "		<div class=\"icon_text_kombination_-_text "+ tagClassSchrift +"\">" + f.getTitel() + "</div>"
							+ "</span>";
					
					out.println(ausgabe);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if (ort.equals("desktop")){
			try {
				out.println("<div class=\"clear_left\"></div>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return EVAL_PAGE;
	}

	

}
