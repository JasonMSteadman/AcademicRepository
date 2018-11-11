var websocket = new WebSocket("ws://localhost:8080/Speed/Server")	

websocket.onmessage = function processMessage(message){
	var jsonData = JSON.parse(message.data);
	if(jsonData != null){
		if(jsonData.hasOwnProperty("role")){
			
			document.getElementById("role").innerHTML = jsonData.role;
		}
		if(jsonData.hasOwnProperty("a1")){
			
			document.getElementById("a1").src = jsonData.a1;
			document.getElementById("a2").src = jsonData.a2;
			document.getElementById("a3").src = jsonData.a3;
			document.getElementById("a4").src = jsonData.a4;
			
			document.getElementById("b1").src = jsonData.b1;
			document.getElementById("b2").src = jsonData.b2;
			document.getElementById("b3").src = jsonData.b3;
			document.getElementById("b4").src = jsonData.b4;
			document.getElementById("deck").src = jsonData.deck;

			document.getElementById("hand").src = jsonData.hand;
			document.getElementById("winner").innerHTML = jsonData.w;
				
		}
	}
}

function sendMessage(gridLocation){
	websocket.send(gridLocation);
}

//Drag and drop handlers
//Events fired when the drag starts
document.addEventListener("dragstart", function(event) {
	event.dataTransfer.setData("Image", event.target.src);
	//document.getElementById("dragTest").innerHTML = "Started to drag the element.";
	event.target.style.opacity = "0.4";
});

//Events fired when dragging
document.addEventListener("drag", function(event) {
	document.getElementById("dragTest").style.color = "white";
});

//Events when dragging ends
document.addEventListener("dragend", function(event) {
	//document.getElementById("dragTest").innerHTML = "Finished dragging the element.";
	event.target.style.opacity = "1";
});

//Events when drag enters the target
document.addEventListener("dragenter", function(event) {
	if (event.target.className == "image") {
		event.target.style.border = "2px dotted yellow";
	}
});

//Prevents default handling of element
document.addEventListener("dragover", function(event) {
	event.preventDefault();
	//event.getElementById("dragTest").innerHTML = String(event.target.id) + "";
});

//Events when drag leaves target, i.e. resets
document.addEventListener("dragleave", function(event) {
	if (event.target.className == "image") {
		event.target.style.border = "";
	}
});

//Events on drop
document.addEventListener("drop", function(event) {
	event.preventDefault();
	if(event.target.className == "image") {
		//document.getElementById("dragTest").innerHTML = String(event.target.id);
		document.getElementById("dragTest").style.color = "";
		
		event.target.style.border = "";
		//var data = event.dataTransfer.getData("Image");
		//event.target.appedChild(document.getElementById(data));
		sendMessage(event.target.id);
		
	}
});
