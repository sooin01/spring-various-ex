<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/resources/js/jquery.terminal/jquery-1.7.1.min.js"></script>
<script src="/resources/js/xterm/xterm.js"></script>
<script src="/resources/js/xterm/addons/attach/attach.js"></script>
<script src="/resources/js/xterm/addons/fit/fit.js"></script>
<script src="/resources/js/xterm/addons/winptyCompat/winptyCompat.js"></script>
<script src="/resources/js/terminal.js"></script>
<link href="/resources/js/jquery.terminal/jquery.terminal.min.css" rel="stylesheet"/>
<link href="/resources/js/xterm/xterm.css" rel="stylesheet" />
<script>
function connect() {
	var data = {
		host: "192.168.1.29",
		port: 22,
		username: "stack",
		password: "admin123"
	};
	
	$.post('/command/connect', data, function(data) {
		$('#connect').hide();
		terminal();
	});
}

$(function() {
    connect();
});
</script>
</head>
<body>

<input type="button" id="connect" value="접속" onclick="connect();" />

<div id="terminal"></div>

</body>
</html>