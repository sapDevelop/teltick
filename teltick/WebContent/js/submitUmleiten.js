var html= '';
var submit_button;

//Leitet den Submit auf Ajax um
function submitUmleiten(sender, id)
{
	if ( window.XMLHttpRequest)
	{
		html = new XMLHttpRequest();
	}
	else if ( window.ActiveXObject )
	{
		html = new ActiveXObject("Microsoft.XMLHTTP");
	}
		
	if ( html != "" )
	{
		var url = sender.method == 'get' ? sender.action + '?' : sender.action;
		
		var werte = 'ajax_id=' + encodeURIComponent(id);
		
		//Denn gewählten Submitbutton übermitteln
		werte += '&submit=' + encodeURIComponent(submit_button);
		
				
		//Leitet alle Inputfelder über Ajax um
		for (var i = 0; i < sender.elements.length; i++){
			if(sender.elements[i].type != 'submit')  werte += '&' + sender.elements[i].name + '=' + encodeURIComponent(sender.elements[i].value);
		}
		
		var url = sender.method == 'get' & werte != '' ? sender.action + '?' + werte : sender.action;
		
		
		//Ajax-Anfrage senden
		html.open(sender.method, url  , true);
		html.onreadystatechange = anfrageResult;
		
		if (sender.method == 'post'){
			html.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			html.send(werte);
		}else{
			html.send(null);
		}
	};
	
	return false;
}


function anfrageResult(){
	
	if ( html.readyState == 4 && html.responseText.trim() != "")	{
		var text = html.responseText.trim();
		
		var array_text = [];
		var richtige_methode = false;
		methoden = ['\r\n', '\n', "'\r'"];
		for (var i = 0; i < 3 && !richtige_methode; i++){
			array_text = text.split(methoden[i]);
			richtige_methode = array_text.length > 1;
		}
		
		var result = '';
		for(var i = 1; i < array_text.length; i++) result += array_text[i];
		
		var id_content = 'fenster_content_' + array_text[0];
		document.getElementById(id_content).innerHTML = result;
	}
}
