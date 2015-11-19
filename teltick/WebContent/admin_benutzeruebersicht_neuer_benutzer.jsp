<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="WEB-INF/tagsMRechte.tld" prefix="m" %>
<jsp:useBean id="angemeldeterMitarbeiter" class="modell.entitaeten.implementierung.ImpMitarbeiter" scope="session" />

<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer) --%>
<c:out value="${ param.ajax_id}" />
<m:hatZugriffrechteFenster fensterId="0" >
	<form action="" method="post"  name="form_<c:out value="${param.id}" />" id="form_<c:out value="${param.id}" />" onsubmit="return submitUmleiten(this, '<c:out value="${param.id}" />');" >
		<input type="hidden" value="<c:out value="${param.id}" />" name="id" />
		<div class="standard_fenster_content">
			<b>Neuen Benutzer anlegen</b>
			<div class="formular_bereich_neuer_benutzer" >
				<div class="login_formularzeile">
					<label for="login_name_<c:out value="${param.id}" />" >Login-Name:</label>
					<input type="text" name="login_name_<c:out value="${param.id}" />" />
				</div>
			</div>
		</div>
	</form>
</m:hatZugriffrechteFenster>
