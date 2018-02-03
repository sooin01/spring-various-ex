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
$(function() {
    $('body').terminal(function(command, term) {
    	term.pause();
        $.post('/command', {command: command}).then(function(data) {
        	term.echo('').resume();;
        	term.set_prompt(data);
        });
    }, {
        greetings: 'Web CLI Emulator',
        onBlur: function() {
            return false;
        }
    });
});
</script>
</head>
<body>
</body>
</html>