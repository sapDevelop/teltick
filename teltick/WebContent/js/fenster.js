//Quelle: elouai.com
var ie=document.all;
var nn6=document.getElementById&&!document.all;
var verschieben=false;
var x,y;
var dobj;

var geschlossene_fenster = [];
var fenster_maximiert = '';

function movemouse(e)
{
  if (verschieben)
  {
	  var fenster_breite = window.innerWidth
	  	|| document.documentElement.clientWidth
	  	|| document.body.clientWidth;  
	  
	var left_neu = nn6 ? tx + e.clientX - x : tx + event.clientX - x;
	var top_neu = nn6 ? ty + e.clientY - y : ty + event.clientY - y;
	var pos_groesser_desktop = parseInt(parseInt(dobj.style.left)+parseInt(dobj.style.minWidth));
	var fenster_koord_rechts = left_neu+parseInt(dobj.style.minWidth);
	
    if (left_neu > 0 && (fenster_koord_rechts  <=  fenster_breite || (fenster_breite < pos_groesser_desktop && fenster_koord_rechts <pos_groesser_desktop) )){
    	dobj.style.left = left_neu + "px";
    }
    
    if (top_neu > 51) dobj.style.top  = top_neu + "px";
    return false;
  }
}



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

document.onmousedown=selectmouse;
document.onmouseup=new Function("verschieben=false");