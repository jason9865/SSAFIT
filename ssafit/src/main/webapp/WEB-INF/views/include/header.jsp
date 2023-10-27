<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
		<div class="p-3 m-3 d-flex justify-content">
			<c:if test="${empty loginUser }">
				<div class="col">
					<a href="/user/login" class="btn btn-outline-primary">로그인</a>
					<a href="/user/signup" class="btn btn-primary">회원가입</a>
				</div>
			</c:if>
			<c:if test="${!empty loginUser }">
				<div class="col">
						${loginUser.userName }(${loginUser.nickName })님 반갑습니다.
					<a href="/user/myPage/${loginUser.userSeq }" class="btn btn-outline-success">마이페이지</a>
					<a href="/user/logout" class="btn btn-outline-warning">로그아웃</a>
				</div>
			</c:if>

			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<a class="navbar-brand" href="/">SSAFIT</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNavDropdown">
					<ul class="navbar-nav">
						<li class="nav-item">
							<a class="nav-link" href="/board/1">자유게시판</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/board/2">공지사항</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/board/3">유머게시판</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/board/9">동영상 게시판</a>
						</li>

					</ul>
				</div>
			</nav>

		</div>
</div>
<hr>
<script>
	let msg = "${msg}"
	if(msg) {
		alert(msg)
	}
</script>
