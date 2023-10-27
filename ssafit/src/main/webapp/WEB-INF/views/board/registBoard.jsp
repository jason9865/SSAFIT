<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@include file="../include/header.jsp" %>
<body>
	<div class="container">
		<h2 class="text-center">게시판 등록</h2>
		<form action="/board/regist" method="post">
			<div>
				<label for="name" >게시판 이름</label>
				<input type="text" id="name" name="name" class="form-control">
			</div>
			<div>
				<label for="description">상세 설명</label>
				<input type="text" id="description" name="description" class="form-control">
			</div>
			<div>
				<button class="btn btn-primary" type="submit">
					게시판 만들기
				</button>
			</div>

		</form>
	</div>

</body>
<%@include file="../include/footer.jsp" %>
</html>