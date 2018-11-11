<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="html-stylesheet/Site.css">
		<title>Speed</title>
		<style>
			body {
    			padding-top: 50px;
    			padding-bottom: 20px;
    			height: 800px;
    			background-color: #cccccc;
    			background-repeat: no-repeat;
    			background-image: radial-gradient(green, mediumseagreen);
			}
		</style>	
	</head>
		
	<body>
	
		<div class="col-9">
		
			<!-- Player 1 Hands -->
			<div class="col-15" >
				<img class="pileImage" src ="Images/red_back.png" draggable="true">
				<img src ="Images/red_back.png" class="pileImage" id="o" draggable="false">
				<p></p>
			</div>	<!-- End of Player 1 Hands -->
			
			<!-- Top Row -->
			<div class="col-1">			
				<p class="col-13">
					<img src ="Images/red_back.png" class="image" id="b1" draggable="false" >
				</p>
				
				<p class="col-13">
					<img src ="Images/red_back.png" class="image" id="b2" draggable="false">
				</p>
				
				<p class="col-13">
					<img src ="Images/red_back.png" class="image" id="b3" draggable="false">
				</p>
				
				<p class="col-13">
					<img src ="Images/red_back.png" class="image" id="b4" draggable="false">
				</p>
			</div> <!-- End of Top Row -->
			
			<div class="col-2">
				<button type="button" onclick="sendMessage('stuck')">Stuck</button>
				<p id="winner"></p>
				<p id="dragTest"></p>
			</div>
			
			<!-- Bottom Row -->
			<div class="col-1">			
				<p class="col-13">
					<img src ="Images/red_back.png" class="image" id="a1" draggable="false" >
				</p>
				
				<p class="col-13">
					<img src ="Images/red_back.png" class="image" id="a2" draggable="false">
				</p>
				
				<p class="col-13">
					<img src ="Images/red_back.png" class="image" id="a3" draggable="false" >
				</p>
				
				<p class="col-13">
					<img src ="Images/red_back.png" class="image" id="a4" draggable="false" >
				</p>
			</div>	<!-- End of Bottom Row -->
				
			<!-- Player 2 Hands -->
			<div class="col-15" >
				<img class="pileImage" src ="Images/red_back.png" id="hand" draggable="true">
				<img src ="Images/red_back.png" class="pileImage" id="deck" draggable="false" onclick="sendMessage('p')">
				<p id="role"></p>
			</div>	<!-- End of Player 2 Hands -->
			
		</div>

<script type="text/javascript" src="JavaScript/gameAndEvenHandlers.js"></script>
	</body>
</html>