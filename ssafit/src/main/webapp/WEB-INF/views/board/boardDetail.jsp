<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@include file="../include/header.jsp" %>
<body>
	<div class="container">
		<h2 class="text-center">${board.name}</h2>
		<table class="table text-center">
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>좋아요</th>
			</tr>
			<c:forEach items="${articleList}" var="article" varStatus="vs">
			<tr>
				<td>${vs.count}</td>
				<td onclick="location.href='/article/${article.id}'">${article.title }</td>
				<td>${article.userTable.userName }</td>
				<td>${article.createdAt }</td>
				<td>${article.viewCnt }</td>
				<td>아직 만들어지지 않음</td>
			</tr>
			</c:forEach>
		</table>
				
		<div class="d-flex justify-content-end">
			<a href="/article/regist?boardId=${board.id}"class="btn btn-outline-primary">게시물 등록</a>
		</div>
		
	</div>

</body>
<%@include file="../include/footer.jsp" %>
</html>