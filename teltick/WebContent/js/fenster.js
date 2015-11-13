//Quelle: elouai.com
var ie=document.all;
var nn6=document.getElementById&&!document.all;
var verschieben=false;
var x,y;
var dobj;

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
	
    if (left_neu > 0 && (fenster_koord_rechts  <=  fenster_breite || (fenster_breite < pos_groesser_desktop_breite && fenster_koord_rechts < pos_groesser_desktop_breite) )){
    	dobj.style.left = left_neu + "px";
    }
    
    if (top_neu > 51 && (fenster_koord_unten  <=  fenster_hoehe || (fenster_hoehe < pos_groesser_desktop_hoehe && fenster_koord_unten < pos_groesser_desktop_hoehe) )){
    	dobj.style.top  = top_neu + "px";
    }
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

function fenster_schliessen(id_fenster){
	document.getElementById(id_fenster).style.display='none';
	geschlossene_fenster[geschlossene_fenster.length] = id_fenster;
}

function fenster_minimieren(id_fenster){
	document.getElementById(id_fenster).style.display='none';
}

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

function fenster_in_vodergrund_holen(id_fenster){
	var arr_fenster = document.getElementsByClassName("fenster");
	
	//alle Fenster auf Z-Index = '1' setzen
	for (var i = 0; i < arr_fenster.length; i++){
		arr_fenster[i].style.zIndex = '1';
	}
	
	document.getElementById(id_fenster).style.zIndex ='2';
}

document.onmousedown=selectmouse;
document.onmouseup=new Function("verschieben=false");