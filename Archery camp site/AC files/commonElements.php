<?php 
	function LinkCSS($CSS) {
		echo "<LINK rel=stylesheet type=\"text/css\" href=\"CSS/" . $CSS . "\">";
	}
	
	$NavBar = '
		<ul class = "navList">
            <li class = navLi><a href="default.php">Home</a></li>
            <li class = navLi><a href="about.html">About</a></li>
            <li class = navLi><a href="gallery.html">Gallery</a></li>
            <li class = navLi><a href="forms.html">Forms</a></li>
            <li class = navLi><a href="contactUS.html">ContactUS</a></li>
        </ul>'
		
	;
	
	$countdownAnimation = '<style>
	/*center count down text in frame*/
			.countdown{
				position: relative;
				text-align: center;
			}
			.centered {
			  position: absolute;
			  top: 50%;
			  left: 50%;
			  transform: translate(-50%, -50%);
			}
	</style>
	<!-- this is the animation that the count down clock lives inside of-->
	<div>
		<table><tr>
			<td><img id = "frame1" src="Images/archer.png" style="height:20vh; width:20vw"><td>
			<td><img id = "frame2" src="Images/blank.png" style="height:20vh; width:20vw"><td>
			<td><div  class= "countdown">
				<img id = "frame3"src="Images/blank.png" style="height:20vh; width:20vw">
				<div class=\'centered\'> 
					<p id=\'clockText\' style="color:black">days till AC</p>
				</div>
			</div><td>
			<td><img id = "frame4"src="Images/blank.png" style="height:20vh; width:20vw"><td>
			<td><img id = "frame5"src="Images/target.png" style="height:20vh; width:20vw"><td>
		</tr></table>
	</div>
	
	
	<script>
// Set the date we are counting down to
var countDownDate = new Date("Sep 25, 2020 17:30:00").getTime();

// Update the count down every 1 second
var x = setInterval(function() {//generic function

  // Get today date and time
  var now = new Date().getTime();
    
  // Find the distance between now and the count down date
  var distance = countDownDate - now;
    
  // Time calculations for days, hours, minutes and seconds
  
  var days = Math.floor(distance / (1000 * 60 * 60 * 24));//1000 per sec, 60 sep per min, 60 min per hour, 24 hours/ day
  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));//first part gets rid of days, leaving remainder to be formatted into hours
  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60)); //similar to above
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);
    
  // Output the result in an element with id="demo"
  document.getElementById("clockText").innerHTML = days + "d " + hours + "h "
  + minutes + "m " + seconds + "s <br>untill Archery Camp";
    
  // If the count down is over, write some text 
  if (distance < 0) {
    clearInterval(x);
    document.getElementById("clockText").innerHTML = "EXPIRED";
  }
}, 1000);//1000 = 1 sec
</script>
<script>
var x = 0;
var y = setInterval(function(){
	if(x == 0){
	document.getElementById("frame5").src = "Images/target.png";
	document.getElementById("frame1").src = "Images/archer.png";
	}
	if(x == 1){
	document.getElementById("frame2").src = "Images/arrow1.png";
	}
	if(x == 2){
	document.getElementById("frame2").src = "Images/blank.png";
	document.getElementById("frame3").src = "Images/arrow2.png";
	document.getElementById("frame1").src = "Images/archerLooking.png";
	}
	if(x == 3){
	document.getElementById("frame3").src = "Images/blank.png";
	document.getElementById("frame4").src = "Images/arrow2.png";
	}
	if(x == 4){
	document.getElementById("frame4").src = "Images/blank.png";
	document.getElementById("frame5").src = "Images/targetHit.png";
	}
	if(x==5){
	x= -1;
	}
	x ++;
},1000)
</script>';
?>