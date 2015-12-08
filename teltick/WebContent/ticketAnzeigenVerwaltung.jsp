<%@page import="modell.entitaeten.interfaces.Mitarbeiter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="WEB-INF/tagsMRechte.tld" prefix="m" %>
<%@ taglib uri="WEB-INF/tags.tld" prefix="teltick" %>


<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer) --%>
<c:out value="${ param.ajax_id}" />

<m:hatZugriffrechteFenster fensterId="2" >

	

	<form action="" method="post" onreset="fenster_schliessen('fenster_<c:out value="${ param.id }" />');" name="form_<c:out value="${param.id}" />" id="form_<c:out value="${param.id}" />" onsubmit="return submitUmleiten(this, '<c:out value="${param.id}" />');" >
		<input type="hidden" value="<c:out value="${param.id}" />" name="id" />
						
		<div class="uebersicht_ticket_content_aufteiler">
			<div class="uebersicht_ticket_content_bereich_ticketauflistung" >
				<div id="uebersicht_ticket_liste" >
				
					<div id="uebersicht_ticket_liste_tabelle">
					
					
						<div class="div_tabelle_zeile" >
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf" >
								ID
							</div>
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf" >
								Titel
							</div>
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf" >
								Besitzer
							</div>
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf" >
								Erstelldatum
							</div>
						</div>
						<!-- Gibt die Tickets aus -->
						<teltick:TicketTag stat="${param.ticketauswahl}" var="t">
							<input type="radio" name="benutzer_radio" class="benutzer_radio" id="benutzer_radio_<c:out value="${ m.mitarbeiterId }" />_<c:out value="${ param.id }" />" value="<c:out value="${ t.ticketId  }" />" 
								<c:if test="${param.benutzer_radio != null && param.benutzer_radio ==  t.ticketId }">checked="checked"</c:if>
							/>
							<label class="div_tabelle_zeile" for="benutzer_radio_<c:out value="${ t.ticketId  }" />_<c:out value="${ param.id }" />" onclick="button_enable('<c:out value="${ param.id }" />');" >
								<div class="div_tabelle_zelle" >
									#<c:out value="${ t.ticketId }" />
								</div>
								<div class="div_tabelle_zelle" >
									<c:out value="${ t.titel }" />
								</div>
								<div class="div_tabelle_zelle" >
								<teltick:MitarbeiterZuTicketTag ticketId="${ t.ticketId }"></teltick:MitarbeiterZuTicketTag>	
								</div>
								<div class="div_tabelle_zelle" >
									<c:out value="${ t.uhrzeit}" /> - <c:out value="${ t.datum}" />
								</div>
																
							</label>
						</teltick:TicketTag>
						
						
					</div>				
				</div>
			</div>
			<div class="ticket_verwaltung_content_bereich_buttons" >
				<div class="admin_benutzeruebersicht_platzierung" >
				<div class="button_benutzerverwaltung">
				<input type="radio"	value="Eigene Tickets" name="submit"  title="Neuen Benutzer hinzufügen" onclick="submit_button=this.value;" checked="checked"/><a>Eigene Tickets</a>
				<input type="radio"	value="Alle Tickets" name="submit"  title="Neuen Benutzer hinzufügen" onclick="submit_button=this.value;" /><a>Alle Tickets</a>	
				</div>
					<input type="submit" value="Anzeigen" name="submit"  class="button_benutzerverwaltung" title="Neuen Benutzer hinzufügen" onclick="submit_button=this.value;"  />
					<input type="submit" value="Bearbeiten" name="submit"  class="button_benutzerverwaltung" title="Markierten Benutzer ändern" disabled="disabled"  id="button_benutzerverwaltung_aendern_<c:out value="${ param.id }" />"  onclick="submit_button=this.value;" />
					
				</div>
			</div>
		</div>
		<input type="reset" value="Schließen" class="button_schliessen_benutzerverwaltung button_benutzerverwaltung" title="Schließen" />
		<input type="submit" value="Aktualisieren" class="button_schliessen_benutzerverwaltung button_benutzerverwaltung" title="Aktualisieren" onclick="submit_button=this.value;" />
		<div class="clear_right" ></div>
	</form>
</m:hatZugriffrechteFenster>