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

	<form action="NeusTicketController" method="post">

		<div class="neuesTicketKopf">
			<h2>
				Ticket:
				<c:out value="#${neuesTicketId}" />
			</h2>
		</div>
		<table>
			<tr>
				<td VALIGN="TOP">Titel:</td>
				<td><input type="text" id="titel" class="neuesTicketTitel"
					maxlength="70" /></td>
			</tr>
			<tr>
				<td VALIGN="TOP">Beschreibung:</td>
				<td><textarea id="beschreibung" class="neuesTicketBeschreibung"
						cols="35" rows="4"></textarea></td>

			</tr>
			<tr>
				<td VALIGN="TOP">Zugewiesen:</td>
				<td><select>
						<option value="<c:out value="${angemeldeterMitarbeiter.vorname} ${angemeldeterMitarbeiter.name}" />">>Mich< (<c:out value="${angemeldeterMitarbeiter.vorname} ${angemeldeterMitarbeiter.name}" />)</option>
						<teltick:forEachMitarbeiter var="m" mitAdmin="false">
							<option value="<c:out value="${ m.vorname } ${ m.name }" />"><c:out
									value="${ m.vorname } ${ m.name }" /></option>

						</teltick:forEachMitarbeiter>



				</select></td>
			</tr>
		</table>




	</form>
</m:hatZugriffrechteFenster>