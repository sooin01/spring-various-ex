<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/resources/js/jquery.terminal/jquery-1.7.1.min.js"></script>
<script src="/resources/js/jquery.terminal/jquery.mousewheel-min.js"></script>
<script src="/resources/js/jquery.terminal/jquery.terminal.min.js"></script>
<link href="/resources/js/jquery.terminal/jquery.terminal.min.css" rel="stylesheet"/>
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
		console.log(data);
		terminal(data);
	});
}

function terminal(val) {
	$('body').terminal(function(command, term) {
    	term.pause();
        $.post('/command/write', {command: command}).then(function(data) {
        	console.log(data);
        	term.echo('').resume();
        	term.set_prompt(data);
        });
    }, {
        greetings: 'Web CLI Emulator',
        onBlur: function() {
            return false;
        },
        prompt: val
    });
}
</script>
</head>
<body>

<input type="button" id="connect" value="접속" onclick="connect();" />

</body>
</html>