<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/resources/js/jquery.terminal/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
$(function() {
	dup(function(data) {
		alert(data);
	});
});

function dup(callback) {
	$.ajax({
		url: '/main/dup',
		type: 'get',
		success: function(data) {
			callback(data);
		}
	});
}
</script>
</head>
<body>

</body>
</html>