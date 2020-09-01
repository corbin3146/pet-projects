<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.container {
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
</head>
<body>
<table><tr>
<td><img id = "frame1" src="archer.png" style="height:20vh; width:20vw"><td>
<td><img id = "frame2" src="blank.png" style="height:20vh; width:20vw"><td>
<td><div  class= "countdown"><img id = "frame3"src="blank.png" style="height:20vh; width:20vw"><div class='centered'> <p id='clockText' style="color:black">days till AC</p></div></div><td>
<td><img id = "frame4"src="blank.png" style="height:20vh; width:20vw"><td>
<td><img id = "frame5"src="target.png" style="height:20vh; width:20vw"><td>
</tr></table>

<div  class= "container">

<img src="archer.png" style="height:20vh; width:20vw">

<div class="centered"><p>Centered<p></div>

</div>
<script>
var x = 0;
var y = setInterval(function(){
	if(x == 0){
	document.getElementById("frame5").src = "target.png";
	document.getElementById("frame1").src = "archer.png";
	}
	if(x == 1){
	document.getElementById("frame2").src = "arrow1.png";
	}
	if(x == 2){
	document.getElementById("frame2").src = "blank.png";
	document.getElementById("frame3").src = "arrow2.png";
	document.getElementById("frame1").src = "archerLooking.png";
	}
	if(x == 3){
	document.getElementById("frame3").src = "blank.png";
	document.getElementById("frame4").src = "arrow2.png";
	}
	if(x == 4){
	document.getElementById("frame4").src = "blank.png";
	document.getElementById("frame5").src = "targetHit.png";
	}
	if(x==5){
	x= -1;
	}
	x ++;
},1000)
</script>
</body>
</html>
