<!DOCTYPE html>
<html>
    <head>
        <!-- bring in things that appear all across the site -->
		<?php 
        include  'commonElements.php';
		LinkCSS('header.css');
		LinkCSS('parallax.css')
        ?>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>
		Archery Camp
		</title>
		
		<style>
		
			/*set some backgrounds*/
			.welcome{
				background-image: url('Images/IMG_3979.jpg');
			}

			.activities{
				 background-image: url('Images/IMG_4171.jpg');
			}
			
			.extra{
				 background-image: url('Images/IMG_4150.jpg');
			}
			.safety{
				 background-image: url('Images/IMG_3993.jpg');
			}
		</style>
    </head>

    <body style = "background-color:lightgray;">
	
	<!-- shortened form of the code which makes the menu bar for all pages -->
	<?php
	echo $countdownAnimation;
	echo $NavBar; ?>
	<br>
	<!-- the first 'welcome' portion of the page -->
	<div class="parallax welcome">
		<!--<div id = "construction"> <h1>Website under construction</h1></div>-->
		<h1>
            welcome to Midwest Regional Archery Camp
        </h1>
		<div class = "centered">
		<P>
			At MWRAC, we teach outdoor skills.
		</p>
		</div>
	</div>
	
	<!-- section dedicated to sefety, concerns, etc. -->
	<div class = "parallax activities">
		<div class="centered">
		<h1>Activities</h1>
		<ul style="list-style-type: none;">
			<li>Archery</li>
			<li>BB-guns</li>
			<li>Sling Shots</li>
			<li>Fire Building</li>
			<li>'S' talking</li>
			<li>Supper Secret</li>
		<ul>
		</div>
	</div>
	
	<div class = "parallax extra">
		<div class = "centered">
			<h1>test</h1>
			<p>foo</p>
		</div>
	</div>
	<div class = "parallax safety">
		<div class = "centered">
			<h1>Safety</h1>
			<p>How not to kill yourself or your friends</p>
		</div>
	</div>
	<!-- disgusting notes and stuff-->
	<!-- 
	photo wish list
	photos of each staf croup power posing 
	kitchen staff looking out window
	arghery range gang
	bb guns
	etc.
	-->
	<!--
	
		dream hoast offers servers. this opesn up a lot of options
		@mwrarcherycamp.com email addresses
		we could even offer more stations, with the offer to sign up before hand like highschool classes
	
		lets have a summery/directory for the major topics/concerns
		mission statement
		what we do/location (when, how long)
		
	
		cost/sign up procedure
		about page
		photo tour of camp
		photo tour of each range

		video
		trailer, tours

		lets have some interviews postceded by a summary or quote 
		introduce yourself(name, occupation) > what you do at camp > how you do it > why you do it
		eg: meet our shef(cooks)
		</p>
		-->
    </body>
    </html>
