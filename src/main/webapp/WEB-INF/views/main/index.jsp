<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/resources/js/jquery.terminal/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	var citys = [
		[ "서울", "대구" ],
		[ "서울", "부산" ],
		[ "홍콩", "부산" ],
		[ "부산", "도쿄" ],
	];

	// 출발선택
	var depts = [];
	$.each(citys, function(index, value) {
		if (depts[value[0]] == null) {
			depts[value[0]] = new Array();
		}
		depts[value[0]].push(value[1]);
	});
	console.table(depts);

	// 도착선택
	var dests = [];
	$.each(citys, function(index, value) {
		if (dests[value[1]] == null) {
			dests[value[1]] = new Array();
		}
		dests[value[1]].push(value[0]);
	});
	console.table(dests);

	function deptCity(obj) {
		var arr = depts[$(obj).text()];
		$('#dest').empty();
		$.each(arr, function(index, value) {
			$('#dest').append('<button onclick="destCity(this);">' + value + '</button>');
		});
	}

	function destCity(obj) {
		var arr = dests[$(obj).text()];
		$('#dept').empty();
		$.each(arr, function(index, value) {
			$('#dept').append('<button onclick="deptCity(this);">' + value + '</button>');
		});
	}
</script>
</head>
<body>

출발: <div id="dept"><button onclick="deptCity(this);">서울</button></div> ~ 도착: <div id="dest"><button onclick="destCity(this);">부산</button></div>

</body>
</html>
