
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">

body {
	width:775px;
	height:570px;
	background-color:grey;
}
.center {
	padding: 5% 5% 5% 5%;
	border-color: black;
}
</style>

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
					type="submit" value="Upload File" id="submitButton" onclick="form1.submit();doProgress();"/> <br/> 
			</form>
		</center>
	</div>
</body>
</html>
