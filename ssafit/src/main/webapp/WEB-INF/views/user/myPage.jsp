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
		<div>
			<h2>마이페이지</h2>				
			<ul>
				<li>${user.userSeq }번째로 가입한 유저</li>
				<li>${user.id }</li>
				<li>${user.userPwd }</li>
				<li>${user.userName }</li>
				<li>${user.nickName }</li>
				<li>${user.email}</li>
				<li>${user.joinedAt }</li>
			</ul>
				<a href="/user/myPage/${user.userSeq}/update" class="btn btn-primary">회원 정보 수정</a>
		</div>
	</div>
</body>
<%@ include file="../include/footer.jsp" %>
</html>