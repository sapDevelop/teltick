<%@page import="modell.entitaeten.interfaces.Mitarbeiter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="WEB-INF/tagsMRechte.tld" prefix="m"%>
<%@ taglib uri="WEB-INF/tags.tld" prefix="teltick"%>


<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer) --%>
<c:out value="${ param.ajax_id}" />

<m:hatZugriffrechteFenster fensterId="2">

	<form action="" method="post"
		onreset="fenster_schliessen('fenster_<c:out value="${ param.id }" />');"
		name="form_<c:out value="${param.id}" />"
		id="form_<c:out value="${param.id}" />"
		onsubmit="return submitUmleiten(this, '<c:out value="${param.id}" />');
		">
		<input type="hidden" value="<c:out value="${param.id}" />" name="id" />

<div class="admin_benutzeruebersicht_content_aufteiler">
			<div class="admin_benutzeruebersicht_content_bereich_benutzerauflistung" >
				<div class="ueberschrift_content_fenster">Eigene Tickets:</div>
				<div id="admin_benutzeruebersicht_liste" >
				
					<div id="admin_benutzeruebersicht_liste_tabelle">
					
					
						<div class="div_tabelle_zeile" >
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf" >
								Ticket ID
							</div>
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf" >
								Titel
							</div>
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf" >
								Priorität
							</div>
						</div>
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