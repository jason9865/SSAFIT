<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<%@include file="../include/header.jsp" %>
	<body>
		<div class="container">
			<div class="py-5 text-center">
				<h2>로그인</h2>
			</div>
			<form action="/user/login"  method="post">
				<div>
					<label for="id">로그인 ID</label>
					<input type="text" id="id" name="id" class="form-control">
				</div>
				<div>
					<label for="userPwd">비밀번호</label>
					<input type="password" id="userPwd" name="userPwd" class="form-control">
				</div>
				<hr class="my-4">
				<div class="row">
					<div class="col">
						<button class="w-100 btn btn-primary btn-lg" type="submit">
							로그인</button>
					</div>
					<div class="col">
						<a href="../mainPage.jsp" class="w-100 btn btn-primary btn-lg">취소</a>
					</div>
				</div>
			</form>
		</div> <!-- /container -->
	</body>
<%@ include file="../include/footer.jsp" %>
</html>