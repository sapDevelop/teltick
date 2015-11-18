<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Teltick</title>
		<link rel="stylesheet"  href="style/style.css" type='text/css' media="all" />
		<link rel="stylesheet"  href="style/screen.css" type='text/css' media="screen" />
		<script type="text/javascript" src="js/fenster.js" ></script>
		<script type="text/javascript" src="js/submitUmleiten.js" ></script>
		<script type="text/javascript" src="js/admin_benutzer_uebersicht.js" ></script>
		<jsp:useBean id="angemeldeterMitarbeiter" class="modell.entitaeten.implementierung.ImpMitarbeiter" scope="session" />
	</head>
	<body onload="uhr_starten();" >
		<c:choose>
			<%-- Wenn der Mitarbeiter angemeldet ist wird der Desktop angezeigt --%>
			
			<c:when test="${ angemeldeterMitarbeiter.angemeldet == true }">
				<jsp:include page="desktop.jsp" />
			</c:when>
			<%-- Wenn der Mitarbeiter noch nicht angemeldet ist das Login-Formular --%>
			<c:otherwise>
				<jsp:include page="login.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
	</body>
</html>