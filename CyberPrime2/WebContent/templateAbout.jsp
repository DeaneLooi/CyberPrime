<!DOCTYPE HTML>
<html>

<head>
  <title>About Us</title>
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
        <h2>About</h2>
      <h3>"Cyber Prime is home to one of the most secure suite of services which incorporate anonymity, collaboration and media-sharing in a solid package."</h3>
      <h4>Equal Freedom</h4>
	  <p>All users are equal. All users are free to do anything within our services.</p>
	  <p>Share media, collaborate, chat, or transfer files within the veil of privacy. The value of our service is what you make of it.</p>
	  <br/>
	  <p>
	  <h4>Cyber Prime</h4>
	  <p>We are collectively referred to as Cyber Prime. We are the team of four responsible for the creation, development and maintenance of the website.
	  General feedback about the website may be directed to <a href="mailto:">our email.</a></p>
	  <h4>Who is inside Cyber Prime?</h4>
	  <p>We are anonymous like everyone else should be on this site. Collectively, we are all student programmers who specialize in Java and computer security.<p>
	  <h4>Specifics?</h4>
      <p>
	  <li>Member #1 - Graphic designer. The one who populates all these pages with text, spawns ideas like hive larvae spawning zerglings, pushes pixels.</li>
	  <li>Member #2 - (Official but not Actual) Leader. A master strategist who prefers to make ripples in the complex web of life through his code and not his presence.</li>
	  <li>Member #3 - (Unofficial but Actual) De-facto leader. Critiques ideas with his superior background, the sleep-deprived commander of forces and kingpin.</li>
	  <li>Member #4 - Member. Hides greatness behind simplicity, akin to encapsulation in Java. A silent figure who works closely with #2.</li>
      </p>
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
