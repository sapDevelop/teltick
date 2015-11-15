<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div class="startmenu_leiste_platzhalter" >
		<div class="startmenue_platzhalter_links" >
		</div>
		<div class="startmenue_platzhalter_leiste">
			<div class="startmenue_content_aufteiler">
				<span class="startknopf_platzhalter" ></span>
				<span class="startleiste_geoeffnete_fenster" >
					{Icons}
				</span>
				<span id="startmenue_uhr" >00:00</span>
			</div>
		</div>
	</div>
	<div id="startknopf_button_leiste">
		<span title="Startmen&uuml; anzeigen" >TelTick</span>
		<div class="menue">
			<div class="platzhalter_menu_leiste"></div>
			<div class="menu_content"></div>
		</div>
	</div>
	
	
	<jsp:include page="fenster.jsp" >
		<jsp:param name="titel" value="Beispiel Submit-Umleitung mit Ajax ©Benedikt Brüntrup" />
		<jsp:param name="id" value="0" />
		<jsp:param name="inhalt" value="beispielFormular.jsp" />
		<jsp:param name="left" value ="60px" />
		<jsp:param name="top" value="60px" />
		<jsp:param name="minWidth" value="435px" />
		<jsp:param name="minHeight" value="152px" />
		<jsp:param name="z_index" value="1" />
	</jsp:include>
	<jsp:include page="fenster.jsp" >
		<jsp:param name="titel" value="Beispiel Submit-Umleitung mit Ajax ©Benedikt Brüntrup" />
		<jsp:param name="id" value="1" />
		<jsp:param name="inhalt" value="beispielFormular.jsp" />
		<jsp:param name="left" value ="120px" />
		<jsp:param name="top" value="120px" />
		<jsp:param name="minWidth" value="435px" />
		<jsp:param name="minHeight" value="152px" />
		<jsp:param name="z_index" value="1" />
	</jsp:include>