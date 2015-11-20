<%@page import="modell.entitaeten.interfaces.Fenster"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="WEB-INF/tagsMRechte.tld" prefix="m" %>
<%@ taglib uri="WEB-INF/tags.tld" prefix="teltick" %>

<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer) --%>
<c:out value="${ param.ajax_id}" />
<m:hatZugriffrechteFenster fensterId="0" >
	<form action="BenutzerverwaltungController" method="post"  name="form_<c:out value="${param.id}" />" id="form_<c:out value="${param.id}" />" onsubmit="return submitUmleiten(this, '<c:out value="${param.id}" />');" >
		<input type="hidden" value="<c:out value="${param.id}" />" name="id" />
		<div class="standard_fenster_content">
			<c:choose>
				<c:when test="${requestScope.vorgang == 'neuerBenutzer' }">
					<b>Neuen Benutzer anlegen</b>
				</c:when>
				<c:otherwise>
					<b>Benutzer bearbeiten</b>
				</c:otherwise>
			</c:choose>
			<div class="formular_bereich_neuer_benutzer" >
				<div class="neuerbenutzer_formularzeile">
					<label for="login_name_<c:out value="${param.id}" />" >Login-Name:</label>
					<input type="text" name="login_name_<c:out value="${param.id}" />" />
				</div>
				<div class="neuerbenutzer_formularzeile">
					<label for="vorname_<c:out value="${param.id}" />" >Vorname:</label>
					<input type="text" name="vorname_<c:out value="${param.id}" />"  class="neuerbenutzer_namenfelder" />
				
					<label for="name_<c:out value="${param.id}" />" class="neuerbenutzer_label_name" >Name:</label>
					<input type="text" name="name_<c:out value="${param.id}" />" class="neuerbenutzer_namenfelder" />
				</div>
				<div class="neuerbenutzer_formularzeile">
					<label for="email_<c:out value="${param.id}" />" >Email:</label>
					<input type="email" name="email_<c:out value="${param.id}" />" />
				</div>
				<div class="neuerbenutzer_formularzeile">
					<label for="passwort_<c:out value="${param.id}" />" >Passwort:</label>
					<input type="password" name="passwort_<c:out value="${param.id}" />" />
				</div>
				<div class="neuerbenutzer_ueberschrift_bereich_rechte">Rechte:</div>
				<div class="neuerbenutzer_box_rechte" >
				
					<div id="admin_benutzeruebersicht_liste_tabelle" >
						<div class="div_tabelle_zeile" >
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf ueberschrift_checkbox" >
								
							</div>
							<div class="div_tabelle_zelle div_tabelle_zelle_kopf" >
								Bezeichnung
							</div>
						</div>
					
						<c:forEach var="rechtFenster" items="${listeRechte}">
							<div class="div_tabelle_zeile" >
								<input type="checkbox" value="<c:out value="${rechtFenster.id}" />" name="rechte_<c:out value="${param.id}" />" id="rechte_<c:out value="${rechtFenster.id}" />" class="checkbox_rechte" 
									<c:if test="${editUser != null}" >
										<m:hatZugriffrechteFenster fensterId="${rechtFenster.id}"  benutzer="${editUser}" >
											checked="checked"
										</m:hatZugriffrechteFenster>
									</c:if>
								 />
								<label for="rechte_<c:out value="${rechtFenster.id}" />" class="label_rechte" ><c:out value="${rechtFenster.titel}" /></label>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="button_box_rechts">
					<c:choose>
						<c:when test="${requestScope.vorgang == 'neuerBenutzer' }">
							<input type="submit" value="Anlegen" class="" title="Anlegen" onclick="submit_button=this.value;" />
						</c:when>
						<c:otherwise>
							<input type="submit" value="Übernehmen" class="" title="Übernehmen" onclick="submit_button=this.value;" />
						</c:otherwise>
					</c:choose>
					<input type="submit" value="Abbrechen" class="" title="Abbrechen" onclick="submit_button=this.value;" />
				</div>
				<div class="clear_right" ></div>
			</div>
		</div>
	</form>
</m:hatZugriffrechteFenster>
