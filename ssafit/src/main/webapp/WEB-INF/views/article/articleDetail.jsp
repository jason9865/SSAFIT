<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물</title>
</head>
<%@include file="../include/header.jsp" %>
<body>
	<div class="container">
		<h3>제목 : ${article.title }</h3>
		<span>조회수 : ${article.viewCnt}</span>
		<h3>사용자 이름 : ${article.userTable.userName}</h3>
		<h4>사용자 닉네임 : ${article.userTable.nickName}</h4>
		<p>본격적인 내용 : ${article.content }</p>
		
		<c:if test=
		"${(loginUser.userRank eq 2) || (loginUser.userSeq eq article.userTable.userSeq)}">
		<div class="row">
			<div class="d-flex justify-content-end">
				<a href="${article.id }/update"class="btn btn-outline-success">수정</a>
			</div>
			<div class="d-flex justify-content-end">
				<a href="${article.id }/delete"class="btn btn-outline-danger">삭제</a>
			</div>
		</div>
		</c:if>

		<hr>
		<h4>댓글</h4>
		<hr>

		<c:forEach items="${commentList}" var="comment">
			<div class="border border-5">
				<div>
					<span>작성자 : ${comment.userTable.nickName} || </span>
					<span>작성날짜 : ${comment.createdAt}</span>
				</div>
				<div>
					<span>내용 : ${comment.content}</span>
				</div>
				<c:if test="${loginUser.userSeq eq comment.userSeq}">
					<a href="${article.id}/comments/remove/${comment.id}" class="btn btn-outline-warning">댓글 삭제</a>
				</c:if>
			</div>

		</c:forEach>


		<form action="${article.id}/comments/write" method="post">
			<input type="hidden" id="boardId" name="boardId" value="${article.boardId}">
			<div>
				<label for="content">댓글 작성</label><br>
				<textarea id="content" name="content" cols="3" class="form-control"></textarea>
			</div>
			<span>
				<button class="btn btn-primary" type="submit">댓글 등록</button>
			</span>
		</form>

	</div>
</body>
<%@ include file="../include/footer.jsp" %>
</html>