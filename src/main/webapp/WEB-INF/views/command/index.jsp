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
		terminal2(data);
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
	var term = new Terminal({
		cursorBlink: true
	});
	term.open(document.getElementById('terminal'));
	term.write(val);
	term.focus();

	var commands = [];
	term.textarea.onkeydown = function (e) {
		console.log('User pressed key with keyCode: ', e.keyCode);
		
		if (e.keyCode == 13) {
			var command = commands.join('');
			commands = [];
		    $.post('/command/write', {command: command}).then(function(data) {
		    	term.write(data);
		    	term.focus();
		    	
		    	var shellprompt = '$ ';

		    	  term.prompt = function () {
		    	    term.write('\r\n' + shellprompt);
		    	  };
		    });
		} else if (e.keyCode == 8) {
			if (commands.length > 0) {
				commands.splice(commands.length - 1, 1);
				term.write('\b \b');
			}
		} else if (e.keyCode == 9) {
			
		} else if (e.keyCode == 16) {
			
		} else {
			commands.push(e.key);
			term.write(e.key);
		}
   	}
}
</script>
</head>
<body onload="connect();">

<input type="button" id="connect" value="접속" onclick="connect();" />

<div id="terminal"></div>

</body>
</html>