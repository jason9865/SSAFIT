<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 관리</title>
</head>
<%@include file="../include/header.jsp"  %>
<body>
	<div class="container">
		<h2 class="text-center">게시판 관리</h2>
		<table class="table text-center">
			<tr>
				<th>번호</th>
				<th>게시판 이름</th>
				<th>상세</th>
				<th>생성일자</th>
			</tr>
			<c:forEach items="${boardList}" var="board" varStatus="vs">
			<tr>
				<td>${vs.count}</td>
				<td>${board.name }</td>
				<td>${board.description }</td>
				<td>${board.createdAt }</td>
				<td>
					<button class="btn btn-outline-danger"
							onclick="location.href='/board/${board.id}/delete'">
						게시판 삭제
					</button>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
<%@include file="../include/footer.jsp" %>
</html>