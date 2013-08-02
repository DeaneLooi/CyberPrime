<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage</title>
<!-- comment to commit -->
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script> 
$(document).ready(function(){
  $("#notificationsTab").click(function(){
    $("#notifications").slideToggle("slow");
  });
  
  $('#userid').click(function(){
	 $('#users').slideToggle("slow"); 
  });
  
	$('.tabs').click(function(){
		$('.moving_bg').animate($(this).position(),'medium');
	});
	
	$("#add").click(function(){
		
		var username = document.getElementById('username').value;
		
		$.ajaxSetup({ cache: false});

		$.post("AddUsers", { username: username});
		
		document.getElementById('username').value = "";
		
		$("#notifications").slideToggle("slow");
	});


});



function removeUser(){

	$(this).closest('.userCont').animate({
		height: 0}, 500,function(){
			$(this).remove();
		});

	}


function changePage(src) {
   document.getElementById("content").src = src;
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

$.ajaxSetup({ cache: false }); 

setInterval(function() {$("#notifications").load(url); }, 1000);

});
</script>




<link rel="stylesheet" type="text/css" href="../css/newHome.css"/>
<link rel="stylesheet" type="text/css" href="css/newHome.css"/>

</head>
<body>
<%@ page import ="cyberprime.entities.Clients" %>
<%
session = request.getSession();
Clients client = (Clients) session.getAttribute("c");
%>
<div id="tabMenu">

			<div class="moving_bg">
			&nbsp;
			</div>	
			
<ul class="tabsContent">
<li class="tabs" onclick="changePage('secured/firstPage.jsp');">home</li>
<li class="tabs" onclick="changePage('secured/video.jsp');"><img src="images/newWeb.png" height="50px" width="50px">web browsing</li>
<li class="tabs" onclick="changePage('secured/fileTransfer.jsp');"><img src="images/newFile.png" height="50px" width="50px">file transfer</li>
<li class="tabs" onclick="changePage('secured/chat.jsp');"><img src="images/newChat.png" height="50px" width="50px">chat</li>
<li class="tabs" onclick="changePage('secured/conference.jsp');"><img src="images/newConference.png" height="50px" width="50px">video conference</li>
</ul>

</div>

 	
 	<div id="userMenu">
<ul class="menu">

<!--  Disable for auto login --> 

<li class="middlemenu" id="userid"><%=client.getUserId() %></li>


<li class="middlemenu anon">Anonymous Mode</li>
<li id="anon"class="anon"><div class="anonSwitch">
    <input type="checkbox" name="anonSwitch" class="anonSwitch-checkbox" id="anonswitch">
    <label class="anonSwitch-label" for="anonswitch">
        <div class="anonSwitch-inner"></div>
        <div class="anonSwitch-switch"></div>
    </label>
</div></li>
<li class="rightmenu"><form method="post" action="${pageContext.request.contextPath}/Logout"><input type="submit" id="logout" value="logout"></form></li>
<li class="rightmenu" id="notificationsTab">Notifications</li>
</ul>
	<div id="users">
	<input type="text" name="username" id="username" autocomplete="off"><input type="submit" id="add" value="add"/>
	<div id="usernames">
	</div>
	
	</div>
	
	<div id="background"></div>
	
	<div id="contentFrame">
		<iframe id="content"></iframe>
	</div>

			<div id = "notifications">
			

			</div>

</div>

	
	
</body>
</html>
