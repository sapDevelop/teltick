<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="WEB-INF/tagsMRechte.tld" prefix="m" %>
<jsp:useBean id="angemeldeterMitarbeiter" class="modell.entitaeten.implementierung.ImpMitarbeiter" scope="session" />

<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer) --%>
<c:out value="${ param.ajax_id}" />

<m:hatZugriffrechteFenster fensterId="0" >
	<form action="" method="post" onreset="fenster_schliessen('fenster_<c:out value="${ param.id }" />');" name="form_<c:out value="${param.id}" />" id="form_<c:out value="${param.id}" />" onsubmit="return submitUmleiten(this, '<c:out value="${param.id}" />');" >
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
						
						<input type="radio" name="benutzer_radio" class="benutzer_radio" id="benutzer_radio_1" />
						<label class="div_tabelle_zeile" for="benutzer_radio_1" >
							<div class="div_tabelle_zelle" >
								Tester
							</div>
							<div class="div_tabelle_zelle" >
								Otto
							</div>
							<div class="div_tabelle_zelle" >
								Lotto
							</div>
							<div class="div_tabelle_zelle" >
								o.t@tester.de
							</div>
							<div class="div_tabelle_zelle" >
								Benutzerverwaltung
							</div>
						</label>
						
					</div>
					
				</div>
			</div>
			<div class="admin_benutzeruebersicht_content_bereich_buttons" >
				<div class="admin_benutzeruebersicht_platzierung" >
					<input type="submit" value="Neuer Benutzer" name="button_benutzerverwaltung"  class="button_benutzerverwaltung" title="Neuen Benutzer hinzufügen" />
					<input type="submit" value="Benutzer ändern" name="button_benutzerverwaltung"  class="button_benutzerverwaltung" title="Markierten Benutzer ändern" />
					<input type="submit" value="Benutzer löschen" name="button_benutzerverwaltung"  class="button_benutzerverwaltung" title="Markierten Benutzer löschen" />
				</div>
			</div>
		</div>
		<input type="reset" value="Schließen" class="button_schliessen_gross_fenster button_benutzerverwaltung" title="Schließen" />
		<div class="clear_right" ></div>
	</form>
</m:hatZugriffrechteFenster>