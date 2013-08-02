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
<script>

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

</script>

<body>
<div id="chat">
<h2>Chat</h2>
<div id="displayMessage" style="overflow-y:auto;">

</div>
<br/><br/>
<div id="post">

<textarea id="inputMessage" rows="2" cols="100" style="resize:none" placeholder="Enter your message here.">
</textarea>
<input id="postMessage" type="button" value="Post" onclick="postMessage('<%=client.getUserId() %>');">


</div>
</div>
</body>
</html>