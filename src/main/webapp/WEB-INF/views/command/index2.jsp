<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/resources/js/jquery.terminal/jquery-1.7.1.min.js"></script>
<script src="/resources/js/jquery.terminal/jquery.mousewheel-min.js"></script>
<script src="/resources/js/jquery.terminal/jquery.terminal.min.js"></script>
<script src="/resources/js/jquery.terminal/unix_formatting.js"></script>
<link href="/resources/js/jquery.terminal/jquery.terminal.min.css" rel="stylesheet"/>
<script>
function connect() {
	var data = {
		host: "192.168.1.10",
		port: 22,
		username: "root",
		password: "admin123"
	};
	
	$.post('/command/connect', data, function(data) {
		$('#connect').hide();
		terminal(data);
	});
}

function terminal(val) {
	$('body').terminal(function(command, term) {
    	term.pause();
        $.post('/command/write', {command: command}).then(function(data) {
        	term.echo('').resume();
        	term.set_prompt($.terminal.from_ansi(data));
        });
    }, {
        greetings: 'Web CLI Emulator',
        onBlur: function() {
            return false;
        },
        prompt: val
    });
}

$(function() {
	/*
	var ws = new WebSocket("ws://localhost:8080/ws/command");
	
	ws.onopen = function() {
       ws.send("Message to send");
       console.log("Message is sent...");
    };
    
    ws.onmessage = function (evt) {
       var received_msg = evt.data;
       console.log("Message is received...");
    };
    
    ws.onclose = function()
    { 
       console.log("Connection is closed..."); 
    };
    */
    connect();
});
</script>
</head>
<body>

<input type="button" id="connect" value="접속" onclick="connect();" />

<div id="terminal"></div>

</body>
</html>