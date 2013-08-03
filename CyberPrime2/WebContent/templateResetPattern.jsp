<!DOCTYPE HTML>
<html>

<head>
  <title>Reset Pattern</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="css/style.css" />
  <!-- modernizr enables HTML5 elements and feature detects -->
  <script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
</head>

<body>
  <div id="main">
    <header>
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="template.jsp">cyber<span class="logo_colour">_Prime</span></a></h1>
          <h2>Secure, anonymous collaboration.</h2>
        </div>
      </div>
      <nav>
        <div id="menu_container">
          <ul class="sf-menu" id="nav">
            <li><a href="template.jsp">Home</a></li>
            <li><a href="templateFAQ.jsp">FAQ</a></li>
            <li><a href="templateRegister.jsp">Register</a></li>
			<li><a href="templateLogin.jsp">Login</a></li>
            <li><a href="templateFAQ.jsp#service">Services</a></li>
           <li><a href="templateAbout.jsp">About Us</a></li>
          </ul>
        </div>
      </nav>
    </header>
    <div id="site_content">
      <div id="sidebar_container">
        <div class="sidebar">
          <h3>Introduction</h3>
          <h4>Welcome!</h4>
          <h5>May 9th, 2013</h5>
          <p>Welcome to Cyber Prime. If it's your first time here, please register with us to use our services.
		  Otherwise, please login.</p>
        </div>
        <div class="sidebar">
          <h3>Logo</h3>
          <p><img src="images/cplogosmall.png"></p>
        </div>
      </div>
      <div class="content">
         <form method="post" action="${pageContext.request.contextPath}/ResetPattern" enctype="multipart/form-data">
		 <h3>Reset Pattern:</h3>
		${resetResult}
<!-- 		 <p>You are logging in with your IP address: 127.0.0.1</p>
		 <p>Wrong IP address? Please type in your correct IP Address here:<br/>
				<input type="text" name="ip_oct_1" class="calc-input" maxlength="3" onChange="valueChange('0');"> .
				<input type="text" name="ip_oct_2" class="calc-input" maxlength="3" onChange="valueChange('0');"> .
				<input type="text" name="ip_oct_3" class="calc-input" maxlength="3" onChange="valueChange('0');"> .
				<input type="text" name="ip_oct_4" class="calc-input" maxlength="3" onChange="valueChange('0');">
				CSS to style it so it becomes 3 characters wide, JS to make sure all are numbers 
         </p>
         -->
		 <!--Auto detect IP address-->
		<p>Upload image file:<br/><input type="file" name="datafile" size="40"></p>
		<p>Email address:<br/><input type="text" name="email" autocomplete="off"/></p>
         <div>
         <input type="submit" class="btn" value="Reset Pattern">
         </div>
		 </form>
  </div> 
      </div>
    </div>
    <footer>
      <p>Cyber Prime | Created May 2013 | Secure, anonymous collaboration.</a></p>
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
