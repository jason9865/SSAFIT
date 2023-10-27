<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 관리</title>
</head>
<%@include file="../include/header.jsp"  %>
<body>
	<div class="container">
		<h2 class="text-center">유저 관리</h2>
		<table class="table text-center">
			<tr>
				<th>번호</th>
				<th>아이디</th>
				<th>유저 이름</th>
				<th>닉네임</th>
				<th>이메일</th>
				<th>가입 날짜</th>
			</tr>
			<c:forEach items="${userList}" var="user" varStatus="vs">
			<tr>
				<td>${vs.count}</td>
				<td>${user.id }</td>
				<td>${user.userName}</td>
				<td>${user.nickName}</td>
				<td>${user.email}</td>
				<td>${user.joinedAt}</td>
				<td><button class="btn btn-outline-danger"
				onclick="location.href='/delete/${user.userSeq}'">
					유저 삭제</button>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
<%@include file="../include/footer.jsp" %>
</html>