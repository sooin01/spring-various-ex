<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/resources/js/jquery.terminal/jquery-1.7.1.min.js"></script>
<script src="/resources/js/jquery.terminal/jquery.mousewheel-min.js"></script>
<script src="/resources/js/jquery.terminal/jquery.terminal.min.js"></script>
<script src="/resources/js/jquery.terminal/unix_formatting.js"></script>
<script src="/resources/js/xterm/xterm.js"></script>
<link href="/resources/js/jquery.terminal/jquery.terminal.min.css" rel="stylesheet"/>
<link href="/resources/js/xterm/xterm.css" rel="stylesheet" />
<script>
function connect() {
	var data = {
		host: "192.168.1.30",
		port: 22,
		username: "stack",
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

function terminal2(val) {
	var term = new Terminal();
	term.open(document.getElementById('terminal'));
    term.write(val);
    
    var command = 'ls -al';
    $.post('/command/write', {command: command}).then(function(data) {
    	term.write(data);
    });
}
</script>
</head>
<body>

<input type="button" id="connect" value="접속" onclick="connect();" />

<!-- <div id="terminal"></div> -->

</body>
</html>