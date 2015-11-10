<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer)--%>
<c:out value="${ param.ajax_id}" />

<form action="beispielFormular.jsp" name="form" method="post" onsubmit="return submitUmleiten(this, '<c:out value="${param.ajax_id}" />');" >
	<h3>Hallo <c:out value="${param.name}" /> !!!</h3>
	<input type="hidden" value="<c:out value="${param.ajax_id}" />" name="id" />
	<input type="submit" value="Zurück" style="float: right;"  />
	<div style="clear:right" ></div>
</form>
