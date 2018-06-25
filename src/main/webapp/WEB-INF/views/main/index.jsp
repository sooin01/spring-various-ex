<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/resources/js/jquery.terminal/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	function aaa(callback) {
		$.ajaxSetup({async: false});
		$.ajax({
			url: '/aaa',
			data: 'get',
			headers: {
				'Accept': 'application/json; charset=UTF-8',
				'Content-Type': 'application/json; charset=UTF-8'
			},
			success: function(data) {
				callback(data);
			}
		});
	}

	// ajax 응답이 늦게 와도 순서대로 찍힘(data, 111 순으로 찍힘)
	aaa(function(data) {
		console.log(1, data);
		console.log(2, '111');
	});
</script>
</head>
<body>

</body>
</html>
