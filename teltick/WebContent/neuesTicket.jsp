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
	<c:if test="${fehlermeldung != null}">
		<teltick:meldungsbox hoehe="160px" icon="fehler" breite="500px"
			titel="Eingabe ungültig">
			<c:out value="${fehlermeldung}" />
		</teltick:meldungsbox>
	</c:if>

	<form action="NeuesTicketController" method="post"
		action="./NeuesTicketController">

		<div class="ueberschrift_content_neuesTicket">Titel:</div>

		<input type="text" id="titel" class="input_titel_neuesTicket"
			maxlength="70" />

		<div class="ueberschrift_content_neuesTicket">Beschreibung:</div>

		<textarea id="beschreibung" class="textarea_neuesTicket_beschreibung"
			cols="35" rows="4"></textarea>

		<div class="ueberschrift_content_neuesTicket">
			Zugewiesen: ${m.mitarbeiterId} <select class="select_zuweisung">


				<teltick:forEachMitarbeiter var="m" mitAdmin="true">
					<teltick:forEachRecht var="r" mitarbeiterId="${m.mitarbeiterId}">
						<c:if test="${r.fensterId == 1}">
							<option value="<c:out value="${ m.vorname } ${ m.name }"/>"
								<c:if test="${angemeldeterMitarbeiter.vorname == m.vorname}">selected</c:if>><c:if
									test="${angemeldeterMitarbeiter.vorname == m.vorname}">Mich:</c:if>
								<c:out value="${ m.vorname } ${ m.name }" /></option>
						</c:if>
					</teltick:forEachRecht>
				</teltick:forEachMitarbeiter>

			</select>
		</div>
		<div class="ueberschrift_content_neuesTicket">
			Priorität: <select class="select_prio">
				<option value="null" selected></option>
				<option value="hoch">hoch</option>
				<option value="normal">normal</option>
				<option value="gering">gering</option>
			</select>
		</div>

		<input type="submit" value="Ticket anlegen"
			class="button_ticket_anlegen" title="Neues Ticket anlegen" />

	</form>
</m:hatZugriffrechteFenster>