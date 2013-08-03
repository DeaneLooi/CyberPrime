<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<title>Homepage</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<!-- modernizr enables HTML5 elements and feature detects -->
<script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
<!-- comment to commit -->
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>

$(document).ready(function(){
	

		$("#add").click(function() {

			var username = document.getElementById('username').value;

			$.ajaxSetup({
				cache : false
			});

			$.post("AddUsers", {
				username : username
			});

			document.getElementById('username').value = "";

		});
});

	function changePage(src) {
		document.getElementById("services").src = src;
	}

	/* function checkNotifications(){
	 $.ajax({
	 url:"CyberPrime2/cyberprime.servlets/FileTransfer.java",
	 type:"GET",
	 success: function(notification){
	 //check for notifications
	 },
	 error: function(data){
	 //error handling
	 }
	 });
	 } */
</script>

<script>
	var url = 'GetNotifications';

	$(document).ready(function() {

		$.ajaxSetup({
			cache : false
		});

		setInterval(function() {
			$("#notifications").load(url);
		}, 1000);

	});
</script>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/securedStyle.css"/>

</head>

<body>
<%@ page import ="cyberprime.entities.Clients" %>
<%
session = request.getSession();
Clients client = (Clients) session.getAttribute("c");
%>
	<div id="main">
		<header>
		<div id="logo">
			<div id="logo_text">
				<!-- class="logo_colour", allows you to change the colour of the text -->
				<h1>
					<a href="index.html">cyber<span class="logo_colour">_Prime</span></a>
				</h1>
				<h2>Secure, anonymous collaboration.</h2>
			</div>
		</div>
		<nav>
		<div id="menu_container">
			<ul class="sf-menu" id="nav">
			<li onclick="changePage('secured/firstPage.jsp');" id="links">Home</li>
			<li onclick="changePage('secured/video.jsp');" id="links">Web Browsing</li>
			<li onclick="changePage('secured/fileTransfer.jsp');" id="links">File Transfer</li>
			<li onclick="changePage('secured/chat.jsp');" id="links">Chat</li>
			<li onclick="changePage('secured/conference.jsp');" id="links">Video Conference</li>
			<li><form method="post" action="${pageContext.request.contextPath}/Logout"><input type="submit" id="logout" value="Logout"></form></li>
			</ul>
		</div>
		</nav> </header>
		<div id="site_content">
			<div id="sidebar_container">
				<div class="sidebar">
					<h3>
						Username is:
						<h4><%=client.getUserId() %></h4>
					</h3>
					</br>
					<h4>Anonymous Mode</h4>
					<div class="anonSwitch">
							<input type="checkbox" name="anonSwitch"
								class="anonSwitch-checkbox" id="anonswitch"> <label
								class="anonSwitch-label" for="anonswitch">
								<div class="anonSwitch-inner"></div>
								<div class="anonSwitch-switch"></div>
							</label>
						</div>
						</br>
					<div id="users">
						<h4><%=client.getUserId() %></h4>
					</div>
					<input type="text" name="username" id="username"
						placeholder="Username to add/remove" autocomplete="off"> 
						<input type="submit" id="add" class="btn" value="Add User"> 
						<input type="submit" id="remove" class="btn" value="Remove User">
				</div>
				<div class="sidebar">
					<h3>Notifications</h3>
					<div id="notifications">
					</div>
				</div>
			</div>
			<div class="serviceframe">
				<iframe id="services" src="secured/firstPage.jsp"></iframe>
			</div>
		</div>
		<footer>
		<p>
			Cyber Prime | Created May 2013 | Secure, anonymous collaboration.</a>
		</p>
		</footer>
	</div>
	<p>&nbsp;</p>
	<!-- javascript at the bottom for fast page loading -->
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.easing-sooper.js"></script>
	<script type="text/javascript" src="js/jquery.sooperfish.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('ul.sf-menu').sooperfish();
		});
	</script>
</body>
</html>
