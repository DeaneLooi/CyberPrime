<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chat</title>
</head>
<link rel="stylesheet" type="text/css" href="css/securedStyle.css" />

<%@ page import ="cyberprime.entities.Clients" %>
<%
session = request.getSession();
Clients client = (Clients) session.getAttribute("c");
%>
<script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/aes.js"></script>

<!-- https://code.google.com/p/crypto-js/ --> 
<!-- 

function postMessage(user)  {
	
	var input = document.getElementById('inputMessage').value; // save the object
	var encrypted = CryptoJS.AES.encrypt(input, "Secret Passphrase");
    var decryptedHex = CryptoJS.AES.decrypt(encrypted, "Secret Passphrase");
    var decrypted = decryptedHex.toString(CryptoJS.enc.Utf8);
	var userName = user+" said:";
	var timestamp = new Date();
	//var testEncryption = "Encrypted: " + encrypted + " || Decrypted(Hex): " + decryptedHex + " || Decrypted(UTF-8): " + decrypted;
    var conMessage = userName + " " + decrypted + " (" + timestamp + ")";
	var p = document.createElement('p');
	p.textContent = conMessage;
	var displayMessage = document.getElementById('displayMessage');
	displayMessage.appendChild(p);
	document.getElementById('inputMessage').value = "";
}

 -->
 <style>

body{
background-color:#DDD;
}
#postMessage {
    width:98px;
    height:28px;
    border: 1px solid #777777;
    background: #333;
    color: #DDD;
    padding: 4px;
    cursor: pointer;
}

textarea
{
    border:1px solid #999999;
    width:98%;
    margin:5px 0;
    padding:1%;
}

#displayMessage{
    width:100%;
    height:250px;
    border-style:solid;
    border-width:1px;
    border-color:gray;
    overflow-y:auto;
    background-color:white;
}

</style>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>

var url = 'https://samuelong-pc:443/CyberPrime2/Chat';

$(document).ready(function() {

$.ajaxSetup({ cache: false }); 

setInterval(function() {$("#displayMessage").load(url); }, 500);

$("#postMessage").click(function(){
	
	var msg = document.getElementById('inputMessage').value;
	
	$.ajaxSetup({ cache: false});

	$.post("https://samuelong-pc:443/CyberPrime2/Chat", { msg: msg});
	
	document.getElementById('inputMessage').value = "";
});

});
</script>


<body>
<div id="chat">
<h2>Chat</h2>
<div id="displayMessage" style="overflow-y:auto;">

</div>
<br/><br/>
<div id="post">
<textarea id="inputMessage" name="msg"rows="2" cols="100" style="resize:none" placeholder="Enter your message here."></textarea>

<input id="postMessage" type="submit" value="send">


</div>
</div>
</body>
</html>