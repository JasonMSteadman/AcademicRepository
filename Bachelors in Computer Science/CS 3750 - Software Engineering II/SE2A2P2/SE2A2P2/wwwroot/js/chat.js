"use strict";

var connection = new signalR.HubConnectionBuilder().withUrl("/chatHub").build();

connection.on("ReceiveMessage", function (j1, j2, j3, jm) {
    /*
    var msg = message.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
    var encodedMsg = user + " says " + msg;
    var li = document.createElement("li");
    li.textContent = encodedMsg;
    document.getElementById("messagesList").appendChild(li); */
    drawChart(j1, j2, j3, jm);
});

connection.start().catch(function (err) {
    return console.error(err.toString());
});

document.getElementById("sendButton").addEventListener("click", function (event) {
    /*var user = document.getElementById("userInput").value;
    var message = document.getElementById("messageInput").value;    */
    connection.invoke("SendMessage", pick).catch(function (err) {
        return console.error(err.toString()); 
    });
    event.preventDefault();
});