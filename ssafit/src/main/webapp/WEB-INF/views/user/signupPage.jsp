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
				<h2>회원가입</h2>
			</div>
			<form action="signup"  method="post">
				<div>
					<label for="id">회원 ID</label>
					<input type="text" id="id" name="id" class="form-control">
				</div>
				<div>
					<label for="userPwd">비밀번호</label>
					<input type="password" id="userPwd" name="userPwd" class="form-control">
				</div>
				
				<div>
					<label for="confirmedPwd">비밀번호 확인</label>
					<input type="password" id="confirmedPwd" name="confirmedPwd" class="form-control">
				</div>
				
				<div>
					<label for="userName">이름</label>
					<input type="text" id="userName" name="userName" class="form-control">
				</div>
				
				<div>
					<label for="nickName">닉네임</label>
					<input type="text" id="nickName" name="nickName" class="form-control">
				</div>
				
				<div>
					<label for="email">이메일</label>
					<input type="email" id="email" name="email" class="form-control">
				</div>
				
				
				<hr class="my-4">
				<div class="row">
					<div class="col">
						<button class="w-100 btn btn-primary btn-lg" type="submit">
							회원가입
						</button>
					</div>
					<div class="col">
						<a href="/ws" class="w-100 btn btn-primary btn-lg">취소</a>
					</div>
				</div>
			</form>
			
		</div> <!-- /container -->
	</body>
<script>
	let msg = "${msg}"
	if(msg) {
		alert(msg)
	}
</script>
<%@ include file="../include/footer.jsp" %>
</html>