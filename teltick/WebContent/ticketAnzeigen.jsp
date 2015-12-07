<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer) --%>
<c:out value="${ param.ajax_id}" />




<c:if test="${Ticket.ticketId != 0}">
	<div class="ueberschrift_content_TicketAnzeigen_Neu">
		<h2>
			Neues Ticket wurde angelegt: #
			<c:out value="${Ticket.ticketId }"></c:out>
		</h2>
	</div>
</c:if>


<div class="ueberschrift_contentTicketAnzeigen">Titel:</div>
<div class="inhalt_contentTicketAnzeigen">
	<c:out value="${Ticket.titel }"></c:out>
	<c:out value="${fensterId }"></c:out>
</div>
<div class="ueberschrift_contentTicketAnzeigen">Beschreibung:</div>
<div class="inhalt_contentTicketAnzeigen inhalt_contentTicketAnzeigen_beschreibung">
	<c:out value="${Ticket.beschreibung }"></c:out>
</div>
<div  class="ueberschrift_contentTicketAnzeigen">
<div class="ueberschrift_contentTicketAnzeigen" style="float:left; margin-top: 8px;">Zugewiesen an:&nbsp;&nbsp;</div>
<div class="inhalt_contentTicketAnzeigen" style="float:left; width 300px" >
	<c:out value="${requestScope.Zuweisung}"></c:out>
</div>
</div>
<div class="ueberschrift_contentTicketAnzeigen" style=" margin-bottom: 5px;">		
<input type="submit" value="Fenster schließen"
			class="button_ticket_anlegen" title="Fenster schließen" onclick="fenster_schliessen('fenster_<c:out value="${ param.ajax_id}" />');"/>

<input type="submit" value="Neues Ticket"
			class="button_ticket_anlegen" title="Neues Ticket" onclick="oeffne_fenster(1); fenster_schliessen('fenster_<c:out value="${ param.ajax_id}" />');"/>
</div>	
