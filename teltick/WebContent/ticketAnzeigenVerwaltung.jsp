<%@ page import="modell.entitaeten.interfaces.Mitarbeiter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="WEB-INF/tagsMRechte.tld" prefix="m"%>
<%@ taglib uri="WEB-INF/tags.tld" prefix="teltick"%>

<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer) --%>
<c:out value="${ param.ajax_id}" />

<m:hatZugriffrechteFenster fensterId="2">

	<form action="TicketVerwaltungController" method="post"
		onreset="fenster_schliessen('fenster_<c:out value="${ param.id }" />');"
		name="form_<c:out value="${param.id}" />"
		id="form_<c:out value="${param.id}" />"
		onsubmit="return submitUmleiten(this, '<c:out value="${param.id}" />');">
		<input type="hidden" value="<c:out value="${param.id}" />" name="id" />

		<div class="uebersicht_ticket_content_aufteiler">
			<div class="uebersicht_ticket_content_bereich_ticketauflistung">
				<div id="uebersicht_ticket_liste">

					<div id="uebersicht_ticket_liste_tabelle">

						<div class="div_tabelle_zeile">
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf">ID</div>
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf">
								Titel</div>
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf">
								Besitzer</div>
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf">
								Erstelldatum</div>
						</div>
						<!-- Gibt die Tickets aus -->
						<teltick:TicketTag stat="${requestScope.ticketauswahl}" var="t">
							<input type="radio" name="ticket_radio" class="ticket_radio"
								id="ticket_radio_<c:out value="${ t.ticketId }" />_<c:out value="${ param.id }" />"
								value="<c:out value="${ t.ticketId }" />" />
							<label class="div_tabelle_zeile"
								for="ticket_radio_<c:out value="${ t.ticketId }" />_<c:out value="${ param.id }" />"
								onclick="button_enable_ticketanzeigen('<c:out value="${ param.id }" />');">
								<div class="div_tabelle_zelle">
									#
									<c:out value="${ t.ticketId }" />
								</div>
								<div class="div_tabelle_zelle">
									<c:out value="${ t.titel }" />
								</div>
								<div class="div_tabelle_zelle">
									<teltick:MitarbeiterZuTicketTag ticketId="${ t.ticketId }"></teltick:MitarbeiterZuTicketTag>
								</div>
								<div class="div_tabelle_zelle">
									<c:out value="${ t.uhrzeit}" />
									-
									<c:out value="${ t.datum}" />
								</div>

							</label>
						</teltick:TicketTag>


					</div>
				</div>
			</div>
			<div class="ticket_verwaltung_content_bereich_buttons">
				<div class="admin_ticketanzeigen_platzierung">
					<div class="buttonaufteilung_ticketanzeigen">
						<div class="BereichRadioAuswahlAendern">
							<input type="radio" value="Eigene Tickets"
								name="AuswahlAenderung"
								id="Eigene_Tickets_<c:out value="${param.ajax_id}"/>"
								onclick="document.getElementById('submit_<c:out value="${ param.id }" />').click(); "
								<c:if test="${requestScope.radioButton.equals('Eigene Tickets')||empty requestScope.radioButton }">checked</c:if> />
							<label for="Eigene_Tickets_<c:out value="${param.ajax_id}"/>">Eigene Tickets</label> 
						</div>
						<div class="BereichRadioAuswahlAendern">
							<input type="radio" value="Alle Tickets" name="AuswahlAenderung"
								id="Alle_Tickets_<c:out value="${param.ajax_id}"/>"
								onclick="document.getElementById('submit_<c:out value="${ param.id }" />').click();"
								<c:if test="${requestScope.radioButton.equals('Alle Tickets')}">checked</c:if> />
							<label for="Alle_Tickets_<c:out value="${param.ajax_id}"/>">Alle Tickets</label>
						</div>
						<div class="BereichRadioAuswahlAendern">
							<input type="radio" value="Ticket suchen" name="AuswahlAenderung"
								id="Ticket_suchen_<c:out value="${param.ajax_id}"/>"
								onclick="document.getElementById('submit_<c:out value="${ param.id }" />').click();"
								<c:if test="${requestScope.radioButton.equals('Ticket suchen')}">checked</c:if> />
							<label for="Ticket_suchen_<c:out value="${param.ajax_id}"/>">Ticket suchen</label>
							<input type="text" >
							<input type="button" value="Ticket Suchen">
						</div>
					</div>
					<hr>
					<div class="buttonaufteilung_ticketanzeigen">
						<input type="submit" value="Anzeigen" name="submit"
							class="button_ticketanzeigen" disabled="disabled"
							onclick="submit_button=this.value;"
							id="button_ticketanzeigen_<c:out value="${ param.id }" />" /> <input
							type="submit" value="Bearbeiten" name="submit"
							class="button_ticketanzeigen" disabled="disabled"
							onclick="submit_button=this.value;" />

					</div>
				</div>
			</div>
		</div>
		<input type="reset" value="Schließen"
			class="button_schliessen_benutzerverwaltung button_benutzerverwaltung"
			title="Schließen" /> <input type="submit"
			id="submit_<c:out value="${ param.id }" />" value="AuswahlAenderung"
			name="submit" class="button_nonvisible button_ticketanzeigen"
			title="Aktualisieren" onclick="submit_button=this.value;" />

		<div class="clear_right"></div>
	</form>
</m:hatZugriffrechteFenster>