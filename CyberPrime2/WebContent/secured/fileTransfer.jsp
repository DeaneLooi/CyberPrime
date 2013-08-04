
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
a:link {
	text-decoration: none;
	color: #3366cc;
	border: 0px;
}

a:active {
	text-decoration: underline;
	color: #3366cc;
	border: 0px;
}

a:visited {
	text-decoration: none;
	color: #3366cc;
	border: 0px;
}

a:hover {
	text-decoration: underline;
	color: #ff5a00;
	border: 0px;
}

img {
	padding: 0px;
	margin: 0px;
	border: none;
}

body {
	width: 775px;
	height: 570px;
	background-color: grey;
	text-align: center;
	margin: 0 auto;
}

.center {
	padding: 5% 5% 5% 5%;
	border-color: black;
}

.content {
	margin: 20px;
	line-height: 20px;
}
</style>

<!-- jsProgressBarHandler prerequisites : prototype.js -->
<script type="text/javascript" src="../js/prototype/prototype.js"></script>

<!-- jsProgressBarHandler core -->
<script type="text/javascript"
	src="../js/bramus/jsProgressBarHandler.js"></script>

<script type="text/javascript">
	function doProgress() {
		var button = window.document.getElementById("submitButton");
		button.disabled = true;
		var max = 100;
		var prog = 0;
		var counter = 0;
		getProgress();
		doProgressLoop(prog, max, counter);
	}

	function doProgressLoop(prog, max, counter) {
		var x = $('#element4').innerHTML;
		var y = parseInt(x);
		if (!isNaN(y)) {
			prog = y;
		}
		progressBar.update({
			maximum : max,
			progress : prog
		});
		counter = counter + 1;
		progressBar.byId('#element4').innerHTML = counter;
		if (prog < 100) {
			setTimeout("getProgress()", 500);
			setTimeout("doProgressLoop(" + prog + "," + max + "," + counter
					+ ")", 1000);
		}
	}

	function getProgress() {
		progressBar
				.get({
					url : 'http://localhost/CyberPrime2/FileTransfer', // http://localhost:8080/file-upload/progress
					load : function(data) {
						progressBar.byId('#element4').innerHTML = data;
					},
					error : function(data) {
						progressBar.byId('#element4').innerHTML = "Error retrieving progress";
					}
				});
	}
</script>

<title>File Upload</title>
</head>
<body>
	<%@ page import="cyberprime.entities.Clients"%>
	<%
		session = request.getSession();
		Clients client = (Clients) session.getAttribute("c");
	%>
	<div class="main">
		<center class="center">
			<h3>Please upload your files that you want to transfer to:</h3>
			<!-- action should change back to servletUpload -->
			<form action="${pageContext.request.contextPath}/FileTransfer"
				method="post" enctype="multipart/form-data">
				<strong>Now please type in the person's ID that you want to
					transfer your file to:</strong> <input type="text" name="Id"
					class="calc-input" maxlength="25" onChange="valueChange('0');">
				${ftResult} </br> </br> </br> </br> </br> </br> <input type="file" name="file" /> <br /> </br> <input
					type="submit" value="Upload File" id="submitButton"
					onclick="submit();myJsProgressBarHandler.setPercentage('element4',doProgress());" />
				<br />
			</form>
		</center>
	</div>

	<span style="color: #006600; font-weight: bold;">Upload Progress
		Bar</span>
	<br />
	<span class="progressBar" id="element4">0%</span>
	<span class="extra"><a href="#"
		onclick="myJsProgressBarHandler.setPercentage('element4','0');return false;"><img
			src="../images/icons/empty.gif" alt="" title=""
			onmouseout="$('Text4').innerHTML ='&laquo; Select Options'"
			onmouseover="$('Text4').innerHTML ='Empty Bar'" /></a></span>

</body>
</html>
