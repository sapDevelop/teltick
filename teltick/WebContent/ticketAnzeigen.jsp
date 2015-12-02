<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer) --%>
<c:out value="${ param.ajax_id}" />

<div class="ueberschrift_content_neuesTicket">Titel:</div>
<div class="ueberschrift_content_neuesTicket"><c:out value="${Ticket.titel }"></c:out></div>
<div class="ueberschrift_content_neuesTicket">Beschreibung:</div>
<div class="ueberschrift_content_neuesTicket"><c:out value="${Ticket.beschreibung }"></c:out></div>
<div class="ueberschrift_content_neuesTicket">Zuweisung:</div>
<div class="ueberschrift_content_neuesTicket"><c:out value="${requestScope.Zuweisung}"></c:out></div>