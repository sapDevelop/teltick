<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="WEB-INF/tagsMRechte.tld" prefix="m"%>
<%@ taglib uri="WEB-INF/tags.tld" prefix="teltick"%>
<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer) --%>
<c:out value="${ param.ajax_id}" />


<m:hatZugriffrechteFenster fensterId="2" sonderRechte="${requestScope.RechtTicketvorschau}">

	<%-- Zeigt eine Fehlermeldung an, wenn die Eingaben ungültig waren --%>
	<c:if test="${fehlermeldung != null}" >
		<teltick:meldungsbox hoehe="160px" icon="fehler" breite="500px" titel="Eingabe ungültig">
			<c:out value="${fehlermeldung}" />
		</teltick:meldungsbox>
	</c:if>


	<c:if test="${Ticket.ticketId != -1}">
		<div class="ueberschrift_content_TicketAnzeigen_Neu">
			<h3>
				Neues Ticket wurde angelegt: #
				<c:out value="${Ticket.ticketId }"></c:out>
			</h3>
		</div>
	</c:if>
	
	<div class="ueberschrift_contentTicketAnzeigen">Titel:</div>
	<div class="inhalt_contentTicketAnzeigen">
		<c:out value="${Ticket.titel }"></c:out>
		<c:out value="${fensterId }"></c:out>
	</div>
	<div class="ueberschrift_contentTicketAnzeigen">Beschreibung:</div>
	<div
		class="inhalt_contentTicketAnzeigen inhalt_contentTicketAnzeigen_beschreibung">
		<c:out value="${Ticket.beschreibung }"></c:out>
	</div>
	
		<div class="ueberschrift_contentTicketAnzeigen contentTicketAnzeigen_inlineBlock">Zugewiesen an:&nbsp;&nbsp;</div>
		<div class="inhalt_contentTicketAnzeigen contentTicketAnzeigen_inlineBlock">
			<c:out value="${requestScope.Zuweisung}"></c:out>
		</div>
	
	<div class="button_ticket_anzeigen">
		<input type="submit" value="Neues Ticket"
					class="" title="Neues Ticket" onclick="oeffne_fenster(1); fenster_schliessen('fenster_<c:out value="${ param.ajax_id}" />');"/>
			
		<input type="submit" value="Fenster schließen"
					class="" title="Fenster schließen" onclick="fenster_schliessen('fenster_<c:out value="${ param.ajax_id}" />');"/>
	</div>
	<div class="clear_right" ></div>

</m:hatZugriffrechteFenster>