<%@page import="modell.entitaeten.interfaces.Mitarbeiter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="WEB-INF/tagsMRechte.tld" prefix="m"%>
<%@ taglib uri="WEB-INF/tags.tld" prefix="teltick"%>


<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer) --%>
<c:out value="${ param.ajax_id}" />

<m:hatZugriffrechteFenster fensterId="1">

	<%-- Zeigt eine Fehlermeldung an, wenn die Eingaben ungültig waren --%>
	<c:if test="${fehlermeldung != null}" >
		<teltick:meldungsbox hoehe="160px" icon="fehler" breite="500px" titel="Eingabe ungültig">
			<c:out value="${fehlermeldung}" />
		</teltick:meldungsbox>
	</c:if>

	<form action="NeuesTicketController" method="post"
		action="./NeuesTicketController" name="form_<c:out value="${param.id}" />" id="form_<c:out value="${param.id}" />" onsubmit="return submitUmleiten(this, '<c:out value="${param.id}" />');" >
		<input type="hidden" value="<c:out value="${param.id}" />" name="id" />

		<div class="ueberschrift_content_neuesTicket">Titel:</div>

		<input type="text" name="titel" class="input_titel_neuesTicket"
			maxlength="73" value="<c:if test="${Ticket != null}" ><c:out value="${Ticket.titel}" /> </c:if>"
			<teltick:wennEintragInVector vector="felderFehler" eintrag="titel">style="border-color:red;"</teltick:wennEintragInVector>
			 />

		<div class="ueberschrift_content_neuesTicket">Beschreibung:</div>

		<textarea name="beschreibung" class="textarea_neuesTicket_beschreibung"
			 maxlength="800"
			<teltick:wennEintragInVector vector="felderFehler" eintrag="beschreibung">style="border-color:red;"</teltick:wennEintragInVector>
			><c:if test="${Ticket != null}" ><c:out value="${Ticket.beschreibung}" /></c:if></textarea>

		<div class="bereich_comboboxen_neuesTicket">
			<span class="label_comboboxen_neuesTicket">Zugewiesen:</span>
			${m.mitarbeiterId} <select name="zugewiesen" class="select_zuweisung">


				<teltick:forEachMitarbeiter var="m" mitAdmin="true">
					<teltick:forEachRecht var="r" mitarbeiterId="${m.mitarbeiterId}">
						<c:if test="${r.zugehoerigesFenster.id == 1}">
							<option value="<c:out value="${ m.mitarbeiterId}"/>"
								<c:if test="${angemeldeterMitarbeiter.vorname == m.vorname }">selected</c:if>><c:if
									test="${angemeldeterMitarbeiter.vorname == m.vorname}">Mich:</c:if>
								<c:out value="${ m.vorname } ${ m.name }" /></option>
						</c:if>
					</teltick:forEachRecht>
				</teltick:forEachMitarbeiter>

			</select>
		</div>
		<div class="bereich_comboboxen_neuesTicket">
			<span class="label_comboboxen_neuesTicket">Priorität:</span>
			<select name="prio" class="select_prio">
				<option value="null" selected></option>
				<option value="hoch">hoch</option>
				<option value="normal">normal</option>
				<option value="gering">gering</option>
			</select>
		</div>
		<div class="button_ticket_anlegen">
			<input type="submit" value="Ticket anlegen"
				 title="Neues Ticket anlegen"/>
		</div>
		<div class="clear_right" ></div>
	</form>
</m:hatZugriffrechteFenster>