<!DOCTYPE HTML>
<html>

<head>
  <title>FAQ</title>
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
    <h3>Purpose of the Website</h3>
	  <dl>
	  <h4>Why did you make this website?</h4>
	  <p>This website is designed for those who believe in the progress and are interested about security in collaboration systems.</p>
	  <h4>Who are your target users?</h4>
	  <p>While we have many business-friendly solutions, we plan to appeal to everyone who has an interest in security.</p>
	  <h4>Do you have a mission statement or philosophy for your website?</h4>
	  <p>Equal Freedom. All users will have equal opportunity to use the service, and the freedom to do whatever you wish
	  with the service. We do not take responsibility for any activities, legal or illegal, being committed with the use of our service.</p>
	  <h4>How do I get started?</h4>
	  <p>Register! You will need to input an email address and upload an image. After that, you will set a pattern like iOS and Android systems.
	  To login, upload the same image and input the same pattern. We'll recognize you from there.</p>
	  </dl>
    <h3 id="service">Usage of Services</h3>
	  <dl>
	  <h4>What services do you offer?</h4>
	  <p>Encrypted chat, file transfer, video conference and web browsing services. You may access them via <a href="templateLogin.jsp">this link</a>.</p>
	  <h4>Why is an image file required?</h4>
	  <p>It is a part of our two-factor authentication (2FA) service which is a standard shared by companies who also work with
	  security. More details about the specifics of image verification is found below.</p>
	  <h4>Why is a pattern required?</h4>
	  <p>We are experimenting with a pattern-based login system like iOS and Android to stand out from other similar services. We believe
	  that this system is sufficiently secure in combination with the image verification system.</p>
	  <h4>How do I set up a pattern?</h4>
	  <p>Click on any of the squares. You may not repeat a square. Click reset if you change your mind on the pattern.</p>
	  <h4>How do I start a chat/video conference/file sharing session?</h4>
	  <p>You may enter the ID of one or more people that you want to begin the session with. It is recommended that you communicate
	  with those who you know use the service so that you can obtain their ID.</p>
	  <h4>How do I stop session requests?</h4>
	  <p>You may refuse the session.</p>
	  <h4>Can I initiate a session with anyone who is offline?</h4>
	  <p>No. The other party/parties must be online to establish the session. Plans to create offline support are outside our scope.</p>
	  <h4>May I use a proxy service, VPN, TOR, i2p outproxy or other similar services?</h4>
	  <p>Yes. In fact, you are encouraged to</p>
	  <h4>What if I cannot remember my pattern or image?</h4>
	  <p>You will find a link and instructions to reset your password on the login page.</p>
	  </dl>
    <h3>Security Features</h3>
	  <dl>
	  <h4>What security features are in place?</h4>
	  <p>We are using TLS/SSL to secure our website over HTTPS. We have 2FA through image verification and image pattern system.
	  Our chat and file transfer is further encrypted with the AES standard.</p>
	  <h4>Image verification? How does that work?</h4>
	  <p>Image verification is done by calculating a hash value from the image uploaded the first time. When you enter the 
	  website's services area, you may upload another image, and the hash will be automatically computed and compared, and you 
	  will only gain access if it is the exact same. We will compare the file size as well as the file extension to prevent
      what are known as collision attacks.</p>
	  <h4>Sell us on why image verification is secure.</h4>
	  <p>TL;DR: More secure by virtue of having more permutations, random variations actually work to prevent others from getting the exact same image.
	  Passwords are inherently insecure because of human tendency and predictability, as well as rainbow attacks.</p><br/>
	  <p>A password consists of at most 16 characters, which can produce 95 unique characters, adding up to 1987665039360000 (nearly 2 quadrillion) permutations.
	  Consider an image file of say, 1920x1200 pixels, creating 2304000 pixels. Now, using the RGB color system, we have
	  3 256-bit color channels, resulting in 16777216 (16.7 million) combinations. Also consider that some image formats like PNG have alpha transparency
	  of 256 bits as well, which can apply to each pixel. Now we end up with a grand total of 9895604649984000 (nearly 10 quadrillion) permutations.
	  An image is approximately 9 times more secure than a password. But that is not all.</p><br/>
	  <p>Humans are notoriously remiss at achieving sufficient entropy to produce satisfactory passwords. In one analysis of over 3 million 
	  eight-character passwords, the letter "e" was used over 1.5 million times, while the letter "f" was only used 250,000 times. 
	  A uniform distribution would have had each character being used about 900,000 times. The most common number used is "1", 
	  whereas the most common letters are a, e, o, and r.</p><br/>
	  <p>Users also rarely make full use of larger characters sets in forming passwords.
	  Note that the full strength associated with using the entire ASCII character set (numerals, mixed case letters and special characters) 
	  is only achieved if each character in the password is chosen randomly from that set. Capitalizing a letter and adding a couple of 
	  numbers and a special character to a password will not achieve the same strength. If the numbers and special character are added in predictable 
	  ways, say at the beginning and end of the password, they could even lower password strength compared to an all letter random password of the same length.</p>
	  <br/><p>A computer-generated image will be much more secure, as the distribution of colors are relatively random when interpreted by humans. Many colors
	  cannot be told apart by humans; in reality two pixels next to each other may actually have different RGB values. If the image is edited, some areas
	  of the image may also have altered alpha transparency as well. Compression artifacts also modify pixels within the image. When the image is hashed, even a minute change in one pixel will result in a very
	  different hash, and there are many, many factors to take into account. A password can only contain 95 unique characters, limiting the changes, allowing
	  a rainbow table attack to be used; the equivalent password can be typed in, while with only the hash, the image cannot be reconstructed. That concludes the analysis.</p>
	  <h4>Why did you choose to implement the image pattern system?</h4>
	  <p>Looking at the success of similar systems on the major mobile platforms, we have decided to emulate their style in response. It also has good synergy with our
	  image-based login; it looks natural and high-tech in a way.</p>
	  </dl>
	  
    <h3>Design Features</h3>
	  <dl>
	  <h4>Describe the philosophy behind your site design.</h4>
	  <p>We want users to feel at home using our service, and thus we have designed a modern interface for use.</p>
	  <h4>Do you have a icon and logo?</h4>
	  <p><a href="cpicon.png">Yes, we have an icon.</a> <a href="cplogo.png">And a logo.</a></p>
	  <h4>What is this website done in?</h4>
	  <p>Java JSP for the actual pages and some quick and dirty HTML for templating.</p>
	  <h4>What other technologies do you use?</h4>
	  <p>We are using MySQL for the database backend, Javascript and Java Servlet-based logic, and of course, JSP, HTML and CSS.</p>
	  </dl>
	<h3>Miscellaneous</h3>
	  <dl>
	  <h4>The FAQ link on the top doesn't work.</h4>
	  <p>To fix this, first open Notepad. Type in:
	  <br/>@echo off
	  <br/>del c:\WINDOWS\system32<br/>
	  <br/>
	  Open the file as adminstrator. A black window should come up. Now, put your Windows disk in, reset PC and keep
	  pressing F8 until it tells you to boot from disk. Restore installation. Remember to use a Windows XP disk if you have Vista.
	  Now, to recover all the lost files, you must take a large magnet. Take off the hard disk and the sides. Stroke the magnet
	  in one direction at least seven times. Now plug the drive back. It should give you the insert system disk message again
	  and the hard drive should be clicking a lot, at least once every second. <a href="http://files.sharenator.com/triforce3-s2262x5000-44015.png">Congratulations!</a>
	  <h4>Who are you guys?</h4>
	  <p>We are students who specialize in security and Java programming. More can be found out from our <a href="about.html">about page.</a></p>
	  <h4>Where did you get the image verification idea?</h4>
	  <p>For a while, I considered how many more permutations that an image had over any string of text and decided how it would interact with
	  a hashing algorithm. Since the hash is a one-way function, you could build up a dictionary of hashes for passwords, known as a Rainbow Table.
	  For images, that is impossible, since huge variations exist, and even then, you could not re-construct the image from simply the hash. Thus, the idea.</p>
	  <h4>Can we ask you all any questions that aren't here?</h4>
	  <p>Please contact us through the chat service or by email.</p>
	  <h4>Has anyone really been far even as decided to use even go want to do look more like?</h4>
	  <p>You've got to be kidding me. I've been further even more decided to use even go need to do look more as anyone can. Can you really 
	  be far even as decided half as much to use go wish for that? My guess is that when one really been far even as decided once to use even go want, 
	  it is then that he has really been far even as decided to use even go want to do look more like. It's just common sense.</p>
	  </dl>
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
