<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>메인페이지</title>
<%@ include file="include/header.jsp"%>
</head>
<body>
    <div class="container">

        <h2 class="text-center">환영합니다.</h2>
        <c:if test="${not empty loginUser && loginUser.userRank eq 2}">
	        <a href="board/regist" class="btn btn-primary">게시판 만들기</a>
        </c:if>
<%--        <c:forEach items="${list }" var="board">--%>
<%--     		<div class="m-3 p-3 row border border-5 rounded" --%>
<%--     			 onclick="location.href='board/${board.id}'">--%>
<%--	        	<div><b>${board.name }</b></div>--%>
<%--	        	<div>${board.description }</div>--%>
<%--     		</div>--%>
<%--        </c:forEach> --%>
        <section class="shadow pt-5 pb-5">
            <div class="container">
                <div class="row">
                    <div class="col-6">
                        <h3 class="mb-3">운동 영상 추천</h3>
                    </div>
                    <div class="d-inline-flex justify-content-end">
                        <a class="btn btn-primary mb-3 mr-1" href="#carouselExampleIndicators2" role="button"
                           data-bs-slide="prev">
                            <b>PREV</b>
                        </a>
                        <a class="btn btn-primary mb-3 " href="#carouselExampleIndicators2" role="button" data-bs-slide="next">
                            <b>NEXT</b>
                        </a>
                    </div>
                    <div class="col-12">
                        <div id="carouselExampleIndicators2" class="carousel slide" data-bs-ride="carousel">

                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <div class="row">

                                        <div class="col-md-4 mb-3">
                                            <div class="card">
                                                <img class="img-fluid" alt="100%x280" src="/img/thumb-nail1.jpg">
                                                <div class="card-body">
                                                    <h4 class="card-title">전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]</h4>
                                                    <span class="card-detail bg-dark text-white" style="border-radius:20%;">전신</span>

                                                </div>

                                            </div>
                                        </div>
                                        <div class="col-md-4 mb-3">
                                            <div class="card">
                                                <img class="img-fluid" alt="100%x280" src="/img/thumb-nail2.jpg">
                                                <div class="card-body">
                                                    <h4 class="card-title">하루 15분! 전신 칼로리 불태우는 다이어트 운동</h4>
                                                    <span class="card-detail bg-dark text-white" style="border-radius:20%;">전신</span>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4 mb-3">
                                            <div class="card">
                                                <img class="img-fluid" alt="100%x280" src="/img/thumb-nail3.jpg">
                                                <div class="card-body">
                                                    <h4 class="card-title">상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]</h4>
                                                    <span class="card-detail bg-dark text-white" style="border-radius:20%;">상체</span>

                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="carousel-item">
                                    <div class="row">

                                        <div class="col-md-4 mb-3">
                                            <div class="card">
                                                <img class="img-fluid" alt="100%x280" src="/img/thumb-nail4.jpg">
                                                <div class="card-body">
                                                    <h4 class="card-title">상체비만 다이어트 최고의 운동 [상체 핵매운맛]"</h4>
                                                    <span class="card-detail bg-dark text-white" style="border-radius:20%;">상체</span>

                                                </div>

                                            </div>
                                        </div>
                                        <div class="col-md-4 mb-3">
                                            <div class="card">
                                                <img class="img-fluid" alt="100%x280" src="/img/thumb-nail5.jpg">
                                                <div class="card-body">
                                                    <h4 class="card-title">하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]</h4>
                                                    <span class="card-detail bg-dark text-white" style="border-radius:20%;">하체</span>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4 mb-3">
                                            <div class="card">
                                                <img class="img-fluid" alt="100%x280" src="/img/thumb-nail6.jpg">
                                                <div class="card-body">
                                                    <h4 class="card-title">저는 하체 식주의자 입니다</h4>
                                                    <span class="card-detail bg-dark text-white" style="border-radius:20%;">하체</span>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="carousel-item">
                                    <div class="row">

                                        <div class="col-md-4 mb-3">
                                            <div class="card">
                                                <img class="img-fluid" alt="100%x280" src="/img/thumb-nail7.jpg">
                                                <div class="card-body">
                                                    <h4 class="card-title">11자복근 복부 최고의 운동 [복근 핵매운맛]</h4>
                                                    <span class="card-detail bg-dark text-white" style="border-radius:20%;">복부</span>

                                                </div>

                                            </div>
                                        </div>
                                        <div class="col-md-4 mb-3">
                                            <div class="card">
                                                <img class="img-fluid" alt="100%x280" src="/img/thumb-nail8.jpg">
                                                <div class="card-body">
                                                    <h4 class="card-title">(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)</h4>
                                                    <span class="card-detail bg-dark text-white" style="border-radius:20%;">복부</span>

                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section>
            <div class="search-back">
                <div class="input-group mb-3 search-box">
                    <input type="text" class="form-control"
                           placeholder="운동 제목 검색"
                           aria-label="Recipient's username" aria-describedby="button-addon2">
                    <button class="btn btn-outline-secondary" type="button"
                            id="button-addon2">SEARCH</button>
                </div>
            </div>
        </section>
    </div>
</body>
<%@ include file="include/footer.jsp"%>

</html>
