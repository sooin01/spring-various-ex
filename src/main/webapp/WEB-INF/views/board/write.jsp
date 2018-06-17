<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script type="text/javascript" src="/resources/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript" src="/resources/js/angular/angular.min.js"></script>
<script type="text/javascript" src="/resources/js/angular/tinymce/tinymce.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
var myAppModule = angular.module('myApp', ['ui.tinymce']);
myAppModule.controller('TinyMceController', function($scope) {
	$scope.tinymceModel = 'Initial content';

	$scope.getContent = function() {
		console.log('Editor content:', $scope.tinymceModel);
	};

	$scope.setContent = function() {
		$scope.tinymceModel = 'Time: ' + (new Date());
	};

	$scope.tinymceOptions = {
		plugins : 'link image code',
		toolbar : 'undo redo | bold italic | alignleft aligncenter alignright | code'
	};
});

function goWrite() {
	var content = $('#content').val();
	console.log(content);
	
	var data = {
		content: content	
	};
	
	$.ajax({
		method: 'POST',
		url: '/board/write',
		contentType: 'application/json; charset=UTF-8' ,
		data: JSON.stringify(data)
	}).done(function(msg) {
		console.log(msg);
	});
}
</script>
</head>
<body ng-app="myApp">

	<h2>게시판 수정</h2>

	<form method="post" ng-controller="TinyMceController">
		<textarea ui-tinymce="tinymceOptions" ng-model="tinymceModel"></textarea>
		<button ng-click="getContent()">Get content</button>
		<button ng-click="setContent()">Set content</button>
	</form>
	
	<br><br>
	
	<button onclick="goWrite();">저장</button>
	<textarea id="content" rows="10" cols="10"><script>alert(1);</script></textarea>
	
</body>
</html>