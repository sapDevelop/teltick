<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="WEB-INF/tagsMRechte.tld" prefix="m"%>
<%@ taglib uri="WEB-INF/tags.tld" prefix="teltick"%>
<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer) --%>
<c:out value="${ param.ajax_id}" />


<m:hatZugriffrechteFenster fensterId="2" sonderRechte="${requestScope.RechtTicketvorschau}">
<form action="TicketVerwaltungController" method="post" onreset="fenster_schliessen('fenster_<c:out value="${ param.id }" />');" name="form_<c:out value="${param.id}" />" id="form_<c:out value="${param.id}" />" onsubmit="return submitUmleiten(this, '<c:out value="${param.id}" />');" >
<input type="hidden" value="<c:out value="${param.id}" />" name="id" />
	<%-- Zeigt eine Fehlermeldung an, wenn die Eingaben ungültig waren --%>
	<c:if test="${fehlermeldung != null}" >
		<teltick:meldungsbox hoehe="160px" icon="fehler" breite="500px" titel="Eingabe ungültig">
			<c:out value="${fehlermeldung}" />
		</teltick:meldungsbox>
	</c:if>


	<c:if test="${Ticket.ticketId != -1}">
	<c:choose>
	<c:when test="${requestScope.RechtTicketvorschau}">
	<div class="ueberschrift_content_TicketAnzeigen_Neu">
			<h3>
				Neues Ticket wurde angelegt: #<c:out value="${Ticket.ticketId }"></c:out>
			</h3>
		</div>
	</c:when>
	<c:otherwise>
	<div class="ueberschrift_content_TicketAnzeigen_Neu">
			<h3>
				Ticket: #
				<c:out value="${Ticket.ticketId }"></c:out>
			</h3>
		</div>
	</c:otherwise>
	
	</c:choose>
		
	</c:if>
	
	<div class="ueberschrift_contentTicketAnzeigen_titel">Titel:</div>
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
			<c:out value="${Ticket.ticketzuweisung.mitarbeiter.vorname}"></c:out> <c:out value="${Ticket.ticketzuweisung.mitarbeiter.name}"></c:out>
		</div>
	
	<div class="button_ticket_anzeigen">
	<c:choose>
	<c:when test="${requestScope.RechtTicketvorschau}">
	
	<input type="submit" value="Neues Ticket"
					class="" title="Neues Ticket" onclick="oeffne_fenster(1); fenster_schliessen('fenster_<c:out value="${ param.ajax_id}" />');"/>
			
	</c:when>
	
	<c:otherwise>
	<input type="submit" value="Zur&uuml;ck" class="" title="Zur&uuml;ck" onclick="document.getElementById('zurueckHepler_<c:out value="${ param.ajax_id}" />').click();" /> 
					<input type="hidden" value="zurueck" name="submit" id="zurueckHepler_<c:out value="${ param.ajax_id}" />"  onclick="submit_button=this.value;">
	
	
	</c:otherwise>
	
	</c:choose>
		
		<input type="button" value="Fenster schließen"
					class="" title="Fenster schließen" onclick="fenster_schliessen('fenster_<c:out value="${ param.ajax_id}" />');"/>
	</div>
	<div class="clear_right" ></div>
	
		</form>

</m:hatZugriffrechteFenster>