<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script type="text/javascript" src="/resources/js/angular/angular.min.js"></script>
<script type="text/javascript">
var boardApp = angular.module('boardApp', []);
boardApp.controller('boardCtrl', function($scope, $http) {
	$http.get('/board/list')
	.then(function(response) {
		$scope.list = response.data;
	});
});
</script>
</head>
<body>
<div ng-app="boardApp" ng-controller="boardCtrl">

	<h2>게시판 목록</h2>

    <table id="boardList" style="width:800px;">
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>날짜</th>
            </tr>
        </thead>
        <tbody>
            <tr ng-repeat="item in list">
                <td>{{item.seq}}</td>
                <td>{{item.title}}</td>
                <td>{{item.userId}}</td>
                <td>{{item.createDt}}</td>
            </tr>
        </tbody>
    </table>

</div>
</body>
</html>