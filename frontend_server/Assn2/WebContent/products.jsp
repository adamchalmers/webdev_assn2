<!doctype html>
<html lang="us">
	<head>
		<title>Ecommerce Shopping</title>
		<meta charset="utf-8">
		<link  rel="stylesheet" href="jquery-ui.css">
		<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
		
		<script src="jquery.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/init.js"></script>
		<script src="jquery-ui.js"></script>
		 <script src="portlets.js"></script>
	 
		<noscript>
			
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
			<link rel="stylesheet" href="css/style-noscript.css" />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
	</head>
	<body>

		<!-- Wrapper-->
			<div id="wrapper">
				
				<!-- Nav -->
					<nav id="nav">
						<a href="#me" class="icon fa-home active"><span>Home</span></a>
						<a href="#work" class="icon fa-folder"><span>Catalogue</span></a>
						<a href="#contact" class="icon fa-envelope"><span>Order</span></a>
						
					</nav>

				<!-- Main -->
					<div id="main">
						
						<!-- Me -->
							<article id="me" class="panel">
								<header>
									<div style="margin:40px">


<div class="6u">
<div id="tabs">
	<ul>
		<li><a href="#tabs-1">Login</a></li>
		<li><a href="#tabs-2">Register</a></li>
		
	</ul>
	<div id="tabs-1">
	<form action="#" method="post">
	<div class="6u">
												<input type="text" name="name" placeholder="Username" />
											</div></br>
											
											<div class="6u">
												<input type="password" name="pass" placeholder="Password" />
											</div></br>
											<button id="login">Login</button>
	</form>
	</div>
	
	<div id="tabs-2">
	<form action="#" method="post">
	<div class="6u">
												<input type="text" name="name" placeholder="Username" />
											</div></br>
											
											
											<div class="6u">
												<input type="password" name="pass" placeholder="Password" />
											</div></br>
											<button id="register">Register</button>
	</form>
	</div>
	
</div>
</div>
</div>
								</header>
								<a href="#work" class="jumplink pic">
									<span class="arrow icon fa-chevron-right"><span>See my work</span></span>
									<img src="images/me.jpg" alt="" />
								</a>
							</article>

						<!-- Work --> 
							<article id="work" class="panel">
								<header>
									<h2>Product Catalogue</h2>
								</header>
								<p>
									<form action="#" method="post">
									<div class="row">
									<div class="6u">
									<input type="text" name="product"></div>
									<div class="4u">
									<button id="search">Search</button></div>
									</div>
									</form>
								</p>
								<section>
									
										  <div class="portlet">
											<div class="portlet-header">Feeds</div>
											<div class="portlet-content"><img src="images/pic01.jpg" alt=""></br><button id="button">Add to Cart</button></div>
									</div>
										
										  <div class="portlet">
											<div class="portlet-header">Feeds</div>
											<div class="portlet-content"><img src="images/pic02.jpg" alt=""></br><button id="button2">Add to Cart</button></div>
										</div>
										
										  <div class="portlet">
											<div class="portlet-header">Feeds</div>
											<div class="portlet-content"><img src="images/pic03.jpg" alt=""></br><button id="button3">Add to Cart</button></div>
										</div>
									
									
										  <div class="portlet">
											<div class="portlet-header">Feeds</div>
											<div class="portlet-content"><img src="images/pic04.jpg" alt=""></br><button id="button4">Add to Cart</button></div>
										</div>
										
										  <div class="portlet">
											<div class="portlet-header">Feeds</div>
											<div class="portlet-content"><img src="images/pic05.jpg" alt=""></br><button id="button5">Add to Cart</button></div>
										</div>
										
										  <div class="portlet">
											<div class="portlet-header">Feeds</div>
											<div class="portlet-content"><img src="images/pic06.jpg" alt=""></br><button id="button6">Add to Cart</button></div>
										</div>
								
									
										
										  <div class="portlet">
											<div class="portlet-header">Feeds</div>
											<div class="portlet-content"><img src="images/pic07.jpg" alt=""></br><button id="button7">Add to Cart</button></div>
										
										</div>
										  <div class="portlet">
											<div class="portlet-header">Feeds</div>
											<div class="portlet-content"><img src="images/pic08.jpg" alt=""></br><button id="button8">Add to Cart</button></div>
										
									</div>
										 
										
									
								</section>
							</article>

						<!-- order-->
							<article id="contact" class="panel">
								<header>
									<h2>Place Order</h2>
								</header>
								<form action="#" method="post">
									<div>
									 
										
										<div class="row">
											<div class="12u">
												<textarea name="products" placeholder="Products" rows="8"></textarea>
											</div>
										</div>
										<div class="row">
											<div class="12u">
												<input type="submit" value="Checkout" />
											</div>
										</div>
									</div>
								</form>
							</article>

					</div>
		
				<!-- Footer -->
					<div id="footer">
						<ul class="copyright">
							<li>&copy; 2015</li>
					</div>
		
			</div>
 <script>
			$( "#tabs" ).tabs();
			$( "#search" ).button();
			$( "#button" ).button();
			$( "#login" ).button();
			$( "#register" ).button();
			$( "#button2" ).button();
			$( "#button3" ).button();
			$( "#button4" ).button();
			$( "#button5" ).button();
			$( "#button6" ).button();
			$( "#button7" ).button();
			$( "#button8" ).button();
			</script>
	</body>
</html>
