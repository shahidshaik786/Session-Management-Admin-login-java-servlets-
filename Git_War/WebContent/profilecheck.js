function deleteCookie() {
	document.cookie = "mycompany_Cookie= ; expires = Thu, 01 Jan 1970 00:00:00 GMT"
}

function profilecheck()
{	
	var xmlHttp;
	if(window.XMLHttpRequest)
	{
		xmlHttp = new XMLHttpRequest();
	}
	else
	{
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(xmlHttp)
		{
			xmlHttp.open("POST","./check",true);
			xmlHttp.onreadystatechange = call;
			xmlHttp.send();
		}
	else{
		alert("no xmlHttp created");
	}
	function call()
	{
		if(xmlHttp.readyState==4)
		{
			if(xmlHttp.status==200)
			{
				var json = eval('(' + xmlHttp.responseText +')');
				if(json.state == "move"){
					window.location.href = "./logout.html";
					break;
				}
				else{
					window.location.href = "./profile.html";
					break;
				}
			}
		}
	}
}
