<%@page import="modell.entitaeten.interfaces.Mitarbeiter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="WEB-INF/tagsMRechte.tld" prefix="m" %>
<%@ taglib uri="WEB-INF/tags.tld" prefix="teltick" %>


<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer) --%>
<c:out value="${ param.ajax_id}" />

<m:hatZugriffrechteFenster fensterId="0" >

	<%-- Zeigt die Sicherheitsfrage an, ob der Benutzer wirklich gelöscht werden soll --%>
	<c:if test="${scherheitsfrage_loeschen != null}" >
		<teltick:bestaetigungsbox aktion="BenutzerverwaltungController" hoehe="150px" methode="post" icon="frage" breite="300px" dialogKennung="sicherheitsfrage_benutzer_loeschen"  titel="Benutzer löschen?" submitUmleiten="true" >
			Wollen Sie den Benutzer wirklich löschen?
		</teltick:bestaetigungsbox>
	</c:if>
	
	<%-- Zeigt eine Fehlermeldung an, wenn die Eingaben ungültig waren --%>
	<c:if test="${fehlermeldung != null}" >
		<teltick:meldungsbox hoehe="160px" icon="fehler" breite="500px" titel="Eingabe ungültig">
			<c:out value="${fehlermeldung}" />
		</teltick:meldungsbox>
	</c:if>

	<form action="BenutzerverwaltungController" method="post" onreset="fenster_schliessen('fenster_<c:out value="${ param.id }" />');" name="form_<c:out value="${param.id}" />" id="form_<c:out value="${param.id}" />" onsubmit="return submitUmleiten(this, '<c:out value="${param.id}" />');" >
		<input type="hidden" value="<c:out value="${param.id}" />" name="id" />
						
		<div class="admin_benutzeruebersicht_content_aufteiler">
			<div class="admin_benutzeruebersicht_content_bereich_benutzerauflistung" >
				<div class="ueberschrift_content_fenster">Benutzer:</div>
				<div id="admin_benutzeruebersicht_liste" >
				
					<div id="admin_benutzeruebersicht_liste_tabelle">
					
					
						<div class="div_tabelle_zeile" >
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf" >
								Login-Name
							</div>
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf" >
								Vorname
							</div>
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf" >
								Name
							</div>
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf" >
								Email
							</div>
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf" >
								Rechte
							</div>
						</div>
						
						<!-- Gibt die Mitarbeiter des Systems aus -->
						<teltick:forEachMitarbeiter var="m" mitAdmin="false" >
							<input type="radio" name="benutzer_radio" class="benutzer_radio" id="benutzer_radio_<c:out value="${ m.mitarbeiterId }" />_<c:out value="${ param.id }" />" value="<c:out value="${ m.mitarbeiterId }" />" 
								<c:if test="${param.benutzer_radio != null && param.benutzer_radio ==  m.mitarbeiterId}">checked="checked"</c:if>/>
							<label class="div_tabelle_zeile" for="benutzer_radio_<c:out value="${ m.mitarbeiterId }" />_<c:out value="${ param.id }" />" onclick="button_enable('<c:out value="${ param.id }" />');" >
								<div class="div_tabelle_zelle" >
									<c:out value="${ m.loginName }" />
								</div>
								<div class="div_tabelle_zelle" >
									<c:out value="${ m.vorname }" />
								</div>
								<div class="div_tabelle_zelle" >
									<c:out value="${ m.name }" />
								</div>
								<div class="div_tabelle_zelle" >
									<c:out value="${ m.email }" />
								</div>
								<div class="div_tabelle_zelle" >
									<c:if test="${ fn:length(m.rechte) == 0 }" >-keine-</c:if>
									<c:forEach var="recht" items="${m.rechte }">
										<c:out value="${ recht.zugehoerigesFenster.titel }" />;
									</c:forEach>
								</div>
							</label>
						</teltick:forEachMitarbeiter>
						
						
					</div>				
				</div>
			</div>
			<div class="admin_benutzeruebersicht_content_bereich_buttons" >
				<div class="admin_benutzeruebersicht_platzierung" >
					<input type="submit" value="Neuer Benutzer" name="submit"  class="button_benutzerverwaltung" title="Neuen Benutzer hinzufügen" onclick="submit_button=this.value;"  />
					<input type="submit" value="Bearbeiten" name="submit"  class="button_benutzerverwaltung" title="Markierten Benutzer ändern" disabled="disabled"  id="button_benutzerverwaltung_aendern_<c:out value="${ param.id }" />"  onclick="submit_button=this.value;" />
					<input type="submit" value="Entfernen" name="submit"  class="button_benutzerverwaltung" title="Markierten Benutzer löschen" disabled="disabled" id="button_benutzerverwaltung_loeschen_<c:out value="${ param.id }" />"  onclick="submit_button=this.value;" />
				</div>
			</div>
		</div>
		<input type="reset" value="Schließen" class="button_schliessen_benutzerverwaltung button_benutzerverwaltung" title="Schließen" />
		<input type="submit" value="Aktualisieren" class="button_schliessen_benutzerverwaltung button_benutzerverwaltung" title="Aktualisieren" onclick="submit_button=this.value;" />
		<div class="clear_right" ></div>
	</form>
</m:hatZugriffrechteFenster>