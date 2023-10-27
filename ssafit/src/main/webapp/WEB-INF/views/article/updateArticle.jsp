<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="../include/header.jsp" %>
<body>
	<div class="container">
		<h2 class="text-center">게시글 수정</h2>
		<form action="/article/${article.id }/update" method="post">
			<div class="py-5 form-group">
				<div>
					<label for="title">제목</label>
					<input type="text" id="title" name="title" class="form-control" value="${article.title }">			
				</div>
				<div>
					<label for="content">내용</label>
					<textarea id="content" rows="20" cols="5" name="content" class="form-control" >${article.content }</textarea>
				</div>
				
				<div>
					<button class="btn btn-primary btn-lg" type="submit">
						게시물 수정
					</button>
				</div>
			</div>
		</form>
	</div>
</body>
<%@ include file="../include/footer.jsp" %>
</html>