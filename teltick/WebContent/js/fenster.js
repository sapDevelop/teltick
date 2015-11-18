//Quelle: elouai.com
var ie=document.all;
var nn6=document.getElementById&&!document.all;
var verschieben=false;
var x,y;
var dobj;

var html_ajax_neues_fenster;

var geschlossene_fenster = [];
var fenster_maximiert = '';

//Verschiebt das Fenster
function movemouse(e)
{
  if (verschieben)
  {
	  var fenster_breite = window.innerWidth
	  	|| document.documentElement.clientWidth
	  	|| document.body.clientWidth; 
	  
	  var fenster_hoehe = window.innerHeight
	  	|| document.documentElement.clientHeight
	  	|| document.body.clientHeight; 
	  
	var left_neu = nn6 ? tx + e.clientX - x : tx + event.clientX - x;
	var top_neu = nn6 ? ty + e.clientY - y : ty + event.clientY - y;
	var pos_groesser_desktop_breite = parseInt(parseInt(dobj.style.left)+parseInt(dobj.style.minWidth));
	var fenster_koord_rechts = left_neu+parseInt(dobj.style.minWidth);
	
	var pos_groesser_desktop_hoehe = parseInt(parseInt(dobj.style.top)+parseInt(dobj.style.minHeight));
	var fenster_koord_unten = top_neu+parseInt(dobj.style.minHeight);
	
    if (left_neu > 0 && (fenster_koord_rechts  <  fenster_breite || (fenster_breite < pos_groesser_desktop_breite && fenster_koord_rechts < pos_groesser_desktop_breite) )){
    	dobj.style.left = left_neu + "px";
    }
    
    if (top_neu > 51 && (fenster_koord_unten  <  fenster_hoehe || (fenster_hoehe < pos_groesser_desktop_hoehe && fenster_koord_unten < pos_groesser_desktop_hoehe) )){
    	dobj.style.top  = top_neu + "px";
    }
    
    dobj.style.opacity = '0.7';
    return false;
  }
}


//Wenn die Maus gedrückt ist wird der Verschiebevorgang gestartet
function selectmouse(e)
{
  var fobj       = nn6 ? e.target : event.srcElement;
  var topelement = nn6 ? "HTML" : "BODY";
  while (fobj.tagName != topelement && fobj.className != "fenster_kopf")
  {
    fobj = nn6 ? fobj.parentNode : fobj.parentElement;
  }
  if (fobj.className=="fenster_kopf")
  {
    verschieben = true;
    dobj = nn6 ? fobj.parentNode : fobj.parentElement;
    tx = parseInt(dobj.style.left+0);
    ty = parseInt(dobj.style.top+0);
    x = nn6 ? e.clientX : event.clientX;
    y = nn6 ? e.clientY : event.clientY;
    
    //bringt das Fenster in den Vordergrund   
    fenster_in_vodergrund_holen(dobj.id);
    
    //Lässt das Fenster der Maus folgen
    document.onmousemove=movemouse;
    return false;
  }
}

//Schließt das Fenster mit der übergebenen ID
function fenster_schliessen(id_fenster){
	document.getElementById(id_fenster).style.display='none';
	var id = id_fenster.replace('fenster_', '');
	var id_taskleiste = 'icon_task_leiste_' + id;
	document.getElementById(id_taskleiste).style.display='none';
	geschlossene_fenster[geschlossene_fenster.length] = id_fenster;
}

//Minimiert das Fenster mit der übergebenen Id
function fenster_minimieren(id_fenster){
	document.getElementById(id_fenster).style.display='none';
	task_leiste_alte_markierung_loeschen();
}

//Maximiert das Fenster mit der ibergebenen Id
function fenster_maximieren(id_fenster){
	if (fenster_maximiert != id_fenster){		
		document.getElementById(id_fenster).style.left='0px';
		document.getElementById(id_fenster).style.top='0px';
		document.getElementById(id_fenster).style.width='100%';
		document.getElementById(id_fenster).style.height='100%';
		fenster_maximiert=id_fenster;
	}else{
		fenster_maximiert = '';
		document.getElementById(id_fenster).style.width='auto';
		document.getElementById(id_fenster).style.height='auto';
		document.getElementById(id_fenster).style.left='100px';
		document.getElementById(id_fenster).style.top='100px';
	}
}

//Holt das als Parameter übergebene Fenster in den Vordergrund
function fenster_in_vodergrund_holen(id_fenster){
	var arr_fenster = document.getElementsByClassName("fenster");
	
	//alle Fenster auf Z-Index = '1' setzen
	for (var i = 0; i < arr_fenster.length; i++){
		arr_fenster[i].style.zIndex = '1';
	}
	
	document.getElementById(id_fenster).style.zIndex ='2';
	
	//Fenster in der Taskleiste markieren
	task_leiste_alte_markierung_loeschen();
	var id = id_fenster.replace('fenster_', '');
	var id_taskleiste = 'icon_task_leiste_' + id;
	document.getElementById(id_taskleiste).className = 'icon_task_leiste icon_task_leiste_markiert';
}

//Zeigt in der Startleiste die aktuelle Uhrzeit an
function zeigeAktuelleUhrzeitStartleiste() {
    var now = new Date();
    hours = now.getHours();
    minutes = now.getMinutes();
    seconds = now.getSeconds();
 
    thetime = (hours < 10) ? "0" + hours + ":" : hours + ":";
    thetime += (minutes < 10) ? "0" + minutes  : minutes;
 
    document.getElementById("startmenue_uhr").innerHTML = thetime;
    document.getElementById("startmenue_uhr").title= 'Uhrzeit: ' + thetime;
}

//Startet die Uhr
function uhr_starten(){
	zeigeAktuelleUhrzeitStartleiste()
	window.setInterval("zeigeAktuelleUhrzeitStartleiste()", 1000);
}

//öffnet ein neues 
function oeffne_fenster(id_fenster){
		
	if ( window.XMLHttpRequest)
	{
		html_ajax_neues_fenster = new XMLHttpRequest();
	}
	else if ( window.ActiveXObject )
	{
		html_ajax_neues_fenster = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	if (html_ajax_neues_fenster != ''){
		var url = './NeuesFenster';
		var werte = 'id=' + id_fenster;
		html_ajax_neues_fenster.open('post', url  , true);
		html_ajax_neues_fenster.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		html_ajax_neues_fenster.onreadystatechange = resultAnfrageNeuesFenster;
		html_ajax_neues_fenster.send(werte);
	}
}

function resultAnfrageNeuesFenster(){
	if ( html_ajax_neues_fenster.readyState == 4 && html_ajax_neues_fenster.responseText != ""){
		
		var text = html_ajax_neues_fenster.responseText.trim();
				
		//legt den Rückgabewert in einen Array ab
		var array_text = [];
		var richtige_methode = false;
		methoden = ['\r\n', '\n', "'\r'"];
		for (var i = 0; i < 3 && !richtige_methode; i++){
			array_text = text.split(methoden[i]);
			richtige_methode = array_text.length > 1;
		}
		
		//gibt alle Einträge (bis auf den ersten Index) des Array aus 
		var result = '';
		for(var i = 1; i < array_text.length; i++) result += array_text[i];
		
		
		//In der Taskleiste die Auswahlmarkierung der anderen Fenster entfernen
		task_leiste_alte_markierung_loeschen();
		
		
		//Holt aus der ersten Zeile angaben für die Taskleiste
		if ( array_text.length > 0 ){		
			var task_leite_zeile = array_text[0];
			var werte = task_leite_zeile.split(',');
			document.getElementById('task_leiste').innerHTML += '<img class="icon_task_leiste icon_task_leiste_markiert" src="' + werte[1] + '" id="icon_task_leiste_' + werte[0] + '" title=" '+ werte[2] +'" onclick="task_leiste_icon_clicked(this)" />'
		}
			
		document.getElementById('fenster_bereich_desktop').innerHTML += result;
	}
}

function verschiebevorgangBeenden(){
	verschieben=false;
	if ( dobj ) dobj.style.opacity = '';
}

function task_leiste_alte_markierung_loeschen(){
	var icons_taskleiste = document.getElementsByClassName('icon_task_leiste icon_task_leiste_markiert');
	for ( var i = 0; i < icons_taskleiste.length; i++){
		icons_taskleiste[i].className = 'icon_task_leiste';
	}
}

function task_leiste_icon_clicked(sender){
	task_leiste_alte_markierung_loeschen();
	sender.className = 'icon_task_leiste icon_task_leiste_markiert';
	
	var fenster_id = sender.id;
	fenster_id = fenster_id.replace('icon_task_leiste_', 'fenster_');
	document.getElementById(fenster_id).style.display='block';
	fenster_in_vodergrund_holen(fenster_id);
}

document.onmousedown=selectmouse;
document.onmouseup=verschiebevorgangBeenden;