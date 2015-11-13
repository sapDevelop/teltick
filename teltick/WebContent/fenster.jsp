<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
	Benötigte Parameter (Get):
		titel -> Fenster-Titel
		id -> Fenster-ID
		inhalt -> zu importierende Seite
		action_link -> Link für den Formular-Action
		Left
		Top	
--%>

<script type="text/javascript" src="js/submitUmleiten.js" ></script>

<%-- Überprüft, ob alle benötigten Werte übergeben wurden --%>
<c:choose>
	<c:when test="${ param.titel == null || param.id == null || param.inhalt == null }">
		<c:set var="titel" value="Leeres Fenster" />
		<c:set var="id" value="0" />
		<c:set var="inhalt" value="helloWorld.jsp" />
		<c:set var="left" value ="100px" />
		<c:set var="top" value="100px" />
		<c:set var="z_index" value="1" />
		<c:set var="minWidth" value="435px" />
		<c:set var="minHeight" value="152px" />
	</c:when>
	<c:otherwise>
		<c:set var="titel" value="${param.titel}" />
		<c:set var="id" value="${param.id }" />
		<c:set var="inhalt" value="${param.inhalt}" />
		<c:set var="left" value ="${param.left }" />
		<c:set var="top" value="${param.top}" />
		<c:set var="z_index" value="${param.z_index}" />
		<c:set var="minWidth" value="${param.minWidth}" />
		<c:set var="minHeight" value="${param.minHeight}" />
	</c:otherwise>
</c:choose>


<%-- Zeichnet das Fenster --%>
<div 
	class="fenster" 
	id="fenster_<c:out value="${id }" />"
	style="left:<c:out value="${left}" />; top:<c:out value="${top}" />;min-width: <c:out value="${param.minWidth}" />;min-height: <c:out value="${param.minHeight}" />; z-index:<c:out value="${z_index}" />;"
>
	<div class="fenster_kopf" >
		<c:out value="${titel}" />
		<span class="bereich_fenster_buttons" >
			<span onclick="fenster_minimieren('fenster_<c:out value="${ id }" />');" class="fensterbutton fensterbutton_minimieren" title="Minimieren" ></span>
			<!-- <span onclick="fenster_maximieren('fenster_<c:out value="${ id }" />');" class="fensterbutton fensterbutton_maximieren" title="Maximieren" ></span>-->
			<span onclick="fenster_schliessen('fenster_<c:out value="${ id }" />');" class="fensterbutton fensterbutton_schliessen" title="Schließen" ></span>
		</span>
		<div class="clear_right" ></div>
	</div>
	<div class="content" id="fenster_content_<c:out value="${id }" />">
		<jsp:include page="${inhalt}" >
			<jsp:param value="${id}" name="id"/>
		</jsp:include>
	</div>
</div>
