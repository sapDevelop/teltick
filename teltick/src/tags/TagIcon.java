package tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

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
		
		try {
			for(int i = 0; i<10; i++)
			{
				out.println("Hello World");
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return EVAL_PAGE;
	}

	

}
