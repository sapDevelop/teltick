<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer) --%>
<c:out value="${ param.ajax_id}" />

<form action="" method="post" name="form_<c:out value="${param.id}" />" id="form_<c:out value="${param.id}" />" onsubmit="return submitUmleiten(this, '<c:out value="${param.id}" />');" >
	<div class="admin_benutzeruebersicht_content_aufteiler">
		<div class="admin_benutzeruebersicht_content_bereich_benutzerauflistung" >
			<div class="ueberschrift_content_fenster">Benutzer:</div>
			<div id="admin_benutzeruebersicht_liste" >
			</div>
		</div>
		<div class="admin_benutzeruebersicht_content_bereich_buttons" >
			<div class="admin_benutzeruebersicht_platzierung" >
				<input type="submit" value="Neuer Benutzer" name="button_benutzerverwaltung"  class="button_benutzerverwaltung" />
				<input type="submit" value="Benutzer ändern" name="button_benutzerverwaltung"  class="button_benutzerverwaltung" />
				<input type="submit" value="Benutzer löschen" name="button_benutzerverwaltung"  class="button_benutzerverwaltung" />
			</div>
		</div>
	</div>
</form>