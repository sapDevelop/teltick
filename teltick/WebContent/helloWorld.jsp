<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- Id des Divs, wo mit Ajax die Antwort reingeschrieben werden soll (Nur die Ziffer) --%>
<c:out value="${ param.ajax_id}" />
<style type="text/css" >
	.eingabebereich_beispiel{
		padding-bottom: 15px;
	}
	.eingabe_bereich_text{
		padding: 5px 0px 15px 0px;
		font-weight: bold;
	}
	#fenster_<c:out value="${param.id}" />{
		font-size:14px;
	}
</style>
<form action="begruessung.jsp" method="post" name="form_<c:out value="${param.id}" />" id="form_<c:out value="${param.id}" />" onsubmit="return submitUmleiten(this, '<c:out value="${param.id}" />');" >
	<div class="eingabe_bereich_text">Bitte geben Sie Ihren Namen ein...</div>
	<div class="eingabebereich_beispiel">
		<label>Name:</label>
		<input type="text" name="name"  required="required"  />
	</div>
	<input type="submit" value="Senden" style="float: right;"  />
	<div style="clear:right" ></div>
</form>