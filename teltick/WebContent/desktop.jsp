<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="teltick" uri="/WEB-INF/tags.tld"%>
<div class="startmenu_leiste_platzhalter">
	<div class="startmenue_platzhalter_links"></div>
	<div class="startmenue_platzhalter_leiste">
		<div class="startmenue_content_aufteiler">
			<span class="startknopf_platzhalter"></span> <span
				class="startleiste_geoeffnete_fenster" id="task_leiste"> </span> <span
				class="startleiste_abmelde_button"> <a
				href="./LogoutController" id="abmelde_button" title="Abmelden">
					Abmelden </a>

			</span> <span id="startmenue_uhr">00:00</span>
		</div>
	</div>
</div>
<div id="startknopf_button_leiste" aria-haspopup="true">
	<span title="Startmen&uuml; anzeigen">TelTick</span>
	<div class="menue">
		<div class="platzhalter_menu_leiste"></div>
		<div class="menu_content">
			<table>
				<tr>
					<th>Anwendung öffnen:</th>
				</tr>
				<tr>
					<th><teltick:icons ort="angemeldeterMitarbeiter" /></th>
				</tr>


			</table>
			
		</div>
	</div>
</div>
<div id="fenster_bereich_desktop"></div>