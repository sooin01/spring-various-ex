<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/resources/js/jquery.terminal/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/resources/js/sockjs/sockjs.min.js"></script>
<script type="text/javascript" src="/resources/js/stomp/stomp.min.js"></script>
<script type="text/javascript">
var socket = new SockJS('/stomp');
var stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
    $('#console').append('Connected: ' + frame + '<br>');
    stompClient.subscribe('/topic/websocket/test1', function(message) {
        $('#console').append(message.body + '<br>');
    });
});

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }

    $('#console').append('Disconnected<br>');
}

function messageSend() {
    stompClient.send("/app/websocket/test1", {}, $('#message').val());
}

socket.onclose = function() {
    console.log('close');
};
</script>
</head>
<body>

<input type="text" id="message" />
<input type="button" value="전송" onclick="messageSend();" />
<input type="button" value="종료" onclick="disconnect();" />
<div id="console" />

</body>
</html>