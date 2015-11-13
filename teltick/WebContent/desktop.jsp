<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div class="startmenu_leiste_platzhalter" >
		<div class="startmenue_platzhalter_links" >
		</div>
		<div class="startmenue_platzhalter_leiste" >
		</div>
	</div>
	<jsp:include page="fenster.jsp" >
		<jsp:param name="titel" value="Beispiel Submit-Umleitung mit Ajax ©Benedikt Brüntrup" />
		<jsp:param name="id" value="0" />
		<jsp:param name="inhalt" value="beispielFormular.jsp" />
		<jsp:param name="left" value ="60px" />
		<jsp:param name="top" value="60px" />
		<jsp:param name="minWidth" value="435px" />
		<jsp:param name="z_index" value="1" />
	</jsp:include>