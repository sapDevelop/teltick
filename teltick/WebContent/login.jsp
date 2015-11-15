<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<div class="startmenu_leiste_platzhalter" >
		<div class="startmenue_platzhalter_links" >
		</div>
		<div class="startmenue_platzhalter_leiste">
			<div class="startmenue_content_aufteiler">
				<span class="startknopf_platzhalter" >TelTick</span>
				<span class="startleiste_geoeffnete_fenster" >
				</span>
				<span id="startmenue_uhr" >00:00</span>
			</div>
		</div>
	</div>
	<form action="./LoginController" method="post" id="login_form">
		<div class="login_ueberschrift">
			Login
		</div>
		<div class="login_formularzeile">
			<label for="username" >Benutzername:</label>
			<input type="text" name="username" class="eingabefeld" value="<c:if test="${param.username != null }"><c:out value="${param.username}" /></c:if>" />
		</div>
		<div class="login_formularzeile">
			<label for="password">Passwort:</label>
			<input type="password" name="password" class="eingabefeld" />
		</div>
		<div id="LoginFehler">
			<c:if test="${param.username != null }">
				Der Benutzername oder das Passwort waren falsch.
			</c:if>
		</div>
		<input type="submit" id="LoginButton" value="Einloggen" />
		<div class="clear_right"></div>
	</form>