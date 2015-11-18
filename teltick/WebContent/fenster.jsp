<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%-- Überprüft, ob alle benötigten Werte übergeben wurden --%>
<c:choose>
	<c:when test="${ param.titel != null && param.id != null && param.inhalt != null }">
		<c:set var="titel" value="${param.titel}" />
		<c:set var="id" value="${param.id }" />
		<c:set var="inhalt" value="${param.inhalt}" />
		<c:set var="left" value ="${param.left }" />
		<c:set var="top" value="${param.top}" />
		<c:set var="z_index" value="${param.z_index}" />
		<c:set var="minWidth" value="${param.minWidth}" />
		<c:set var="minHeight" value="${param.minHeight}" />
	</c:when>
	<c:when test="${ requestScope.titel != null && requestScope.id != null && requestScope.inhalt != null }">
		<c:set var="titel" value="${requestScope.titel}" />
		<c:set var="id" value="${requestScope.id }" />
		<c:set var="inhalt" value="${requestScope.inhalt}" />
		<c:set var="left" value ="${requestScope.left }" />
		<c:set var="top" value="${requestScope.top}" />
		<c:set var="z_index" value="${requestScope.z_index}" />
		<c:set var="minWidth" value="${requestScope.minWidth}" />
		<c:set var="minHeight" value="${requestScope.minHeight}" />
	</c:when>
</c:choose>

<%-- Angaben für die Task-Leiste --%>
<c:out value="${id }" />,bilder/fenster_button.png,<c:out value="${titel}" />

<%-- Zeichnet das Fenster --%>
<div 
	class="fenster" 
	id="fenster_<c:out value="${id }" />"
	style="left:<c:out value="${left}" />; top:<c:out value="${top}" />;min-width: <c:out value="${minWidth}" />;min-height: <c:out value="${minHeight}" />; z-index:<c:out value="${z_index}" />;"
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
