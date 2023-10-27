<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<%@include file="../include/header.jsp" %>
</head>
<body>
	<div class="container">
		<h1>Admin Home</h1>
		<div class="col">
			<a href="/admin/board" class="btn btn-primary">게시판 관리</a>
			<a href="/admin/article" class="btn btn-primary">게시글 관리</a>
			<a href="/admin/user" class="btn btn-primary">사용자 관리</a>
		</div>
	</div>
</body>
<%@ include file="../include/footer.jsp" %>
</html>