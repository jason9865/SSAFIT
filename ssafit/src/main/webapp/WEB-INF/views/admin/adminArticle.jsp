<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 관리</title>
</head>
<%@include file="../include/header.jsp"  %>
<body>
	<div class="container">
		<h2 class="text-center">게시글 관리</h2>
		<table class="table text-center">
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach items="${articleList}" var="article" varStatus="vs">
			<tr>
				<td>${vs.count}</td>
				<td>${article.title }</td>
				<td>${article.userTable.userName }</td>
				<td>${article.createdAt }</td>
				<td>${article.viewCnt }</td>
				<td><button class="btn btn-outline-danger" 
							onclick="location.href='/board/article/delete?articleId=${article.id}'">
					게시물 삭제
					</button>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
<%@include file="../include/footer.jsp" %>
</html>