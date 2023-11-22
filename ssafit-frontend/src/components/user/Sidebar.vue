<template>
<aside class="side-bar">

  <ul>
    <li>
      <a href="#"><i class="fa-solid fa-cat"></i>회원 정보</a>
      <ul>
        <li><RouterLink to="/update">회원 정보 수정</RouterLink></li>
        <li><RouterLink to="/followList">팔로우 중인 유저 보기</RouterLink></li>
      </ul>
    </li>
    <li>
      <a href="#"><i class="fa-solid fa-cat"></i>저장한 목록</a>
      <ul>
        <li><RouterLink to="/myArticleList">내가 쓴 게시글</RouterLink></li>
        <li><RouterLink to="/articleLikeList">좋아요 게시글</RouterLink></li>
        <li><RouterLink to="/videoLikeList">좋아요 비디오</RouterLink></li>
      </ul>
    </li>
    
  </ul>
</aside>

</template>

<script setup>

</script>

<style scoped>
body, ul, li {
  margin: 0;
  padding: 0;
  list-style: none;   	    /* 해당 태그의 list-style을 none으로 하는 것으로 ●을 제거한다 */
}

a {
  color: inherit;   	    /* 부모 엘리먼트의 값을 물려받는다 */
  text-decoration: none;    /* 해당 태그의 text-decoration 속성을 none 값으로 하는 것으로 밑줄을 제거한다 */
}

/* 2차 이상의 메뉴를 숨기기 */

/* 사이트의 높이를 5000px로 만들어 스크롤 생성 */
body {
  height: 5000px;
  background-color: #444;
  opacity:0.9;
}

/* 사이드바의 너비와 높이를 변수를 통해 통제 */
:root {
  --side-bar-width: 270px;
  --side-bar-height: 90vh;
}
.side-bar > ul ul {
  display: none;
}

.side-bar {
  /* position: fixed;    스크롤을 따라오도록 지정 */
  background-color: rgb(170, 182, 250);
  width: var(--side-bar-width);
  min-height: var(--side-bar-height);   /* 사이드바의 높이를 전체 화면 높이의 90%로 지정 */
  margin-top: calc((100vh - var(--side-bar-height)) / 2);    /* 사이드바 위와 아래의 마진을 동일하게 지정 */
}

/* 모든 메뉴의 a에 속성값 부여 */
.side-bar ul > li > a {
  display: block;
  color: white;
  font-size: 1.4rem;
  font-weight: bold;
  padding-top: 20px;
  padding-bottom: 20px;
  padding-left: 10px;
}

/* 자식의 position이 absolute일 때 자식을 영역 안에 가두어 준다 */
.side-bar > ul > li {
  position: relative;
}

/* 모든 메뉴가 마우스 인식 시 반응 */
.side-bar ul > li:hover > a {
  background-color: rgb(170, 182, 250);
  border-bottom: 1px solid rgb(170, 182, 250);
}

/* 1차 메뉴의 항목이 마우스 인식 시에 2차 메뉴 등장 */
.side-bar > ul > li:hover > ul {
  display: block;
  position: absolute;
  background-color: rgb(170, 182, 250);
  top: 0%;         /* 2차 메뉴의 상단을 1차 메뉴의 상단에 고정 */
  left: 100%;     /* 2차 메뉴를 1차 메뉴의 너비만큼 이동 */
  width: 100%;    /* 1차 메뉴의 너비를 상속 */
}
</style>