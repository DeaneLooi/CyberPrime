<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
body{
background-color:gray;
width:775px;
}
#otherUsers
{
border:2px solid;
height:400px;
}
#yourself {
border:2px solid;
height:150px;
width:400px;
}
.navi {
	
	display:inline-block;
	background-color:white;
	border-radius:15px;
	font-family:"Lucida Calligraphy" Verdana;
}
}
.naviwords:hover {
	color:white;
}
.navi:hover{
	background-color:rgba(0,0,255,0.6);
	border-radius:15px;
	box-shadow:2px 2px 5px #3366CC;
}
.naviwords{
	text-decoration:none;
	padding: 5px;
	color:black;
}
ul {
position:relative;
left:400px;
bottom:100px;
}
</style>
</head>
<body>
<div id="otherUsers">
<img src="../images/videoConferenceOthers.PNG" alt="Conference" height="400" width="800">
</div>
<div id="yourself">
<img src="../images/videoConferenceYourself.PNG" alt="Conference" height="150" width="400">
</div>
<ul>
					<li class="navi"><a class=naviwords href="">End Call</a>
	
					</li>
					<li class="navi">
						<form method="post" action="#">
						<input type="text" name="url" placeholder="Add user's ID here.">
						<input type="submit" class=naviwords value="Add">
						</form>
					</li>
					
</ul>

</body>
</html>