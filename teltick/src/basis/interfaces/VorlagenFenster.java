package basis.interfaces;

import javax.servlet.http.HttpSession;

public interface VorlagenFenster {
	public String getMeldungsbox(String content, String titel, String breite, String hoehe, String icon, HttpSession session, boolean betriebenAufServlet);
	public String getBestaetigungsBox(String content, String titel, String breite, String hoehe, String icon, HttpSession session, boolean betriebenAufServlet, String methode, String aktion,String dialogKennung, int submitUmleitenNachFensterId);
	public String getBestaetigungsBox(String content, String titel, String breite, String hoehe, String icon, HttpSession session, boolean betriebenAufServlet, String methode, String aktion, String dialogKennung);
}
