<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="/resources/lib/bootstrap-4.1.3-dist/css/bootstrap.min.css">
<title>게시판</title>
<script type="text/javascript" src="/resources/js/angular/angular.min.js"></script>
<script type="text/javascript" src="<c:url value='/resources/js/board/board.js' />"></script>
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

<div ng-app="boardApp" ng-controller="boardCtrl" class="container">

	<h2>게시판 목록</h2>

    <table id="boardList" class="table table-hover">
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>날짜</th>
            </tr>
        </thead>
        <tbody>
            <tr ng-repeat="item in list" class="active">
                <td>{{item.seq}}</td>
                <td>{{item.title}}</td>
                <td>{{item.userId}}</td>
                <td>{{item.createDt}}</td>
            </tr>
        </tbody>
    </table>
</div>

<script src="/resources/js/jquery/jquery-3.3.1.min.js"></script>
<script src="/resources/lib/bootstrap-4.1.3-dist/js/bootstrap.min.js"></script>

</body>
</html>