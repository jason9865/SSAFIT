<template>
<aside class="side-bar m-5">
  <ul>
    <li>
      <a href="#"><i class="fa-solid fa-cat"></i>회원 정보</a>
      <ul class="second">
        <li><RouterLink to="/mypage/update">회원 정보 수정</RouterLink></li>
        <li><RouterLink to="/mypage/myFollowList">팔로우 중인 유저</RouterLink></li>
      </ul>
    </li>
    <li>
      <a href="#"><i class="fa-solid fa-cat"></i>저장한 목록</a>
      <ul class="second">
        <li><RouterLink to="/mypage/myArticleList">내가 쓴 게시글</RouterLink></li>
        <li><RouterLink to="/mypage/myArticleLikeList">좋아요한 게시글</RouterLink></li>
        <li><RouterLink to="/mypage/videoLikeList">찜한 비디오</RouterLink></li>
      </ul>
    </li>
    
  </ul>
</aside>

</template>

<script setup>

</script>

<style scoped>
body, ul {
  margin: 0;
  padding: 0;
  list-style: none;   	    /* 해당 태그의 list-style을 none으로 하는 것으로 ●을 제거한다 */
}


a {
  color: inherit;   	    /* 부모 엘리먼트의 값을 물려받는다 */
  text-decoration: none;    /* 해당 태그의 text-decoration 속성을 none 값으로 하는 것으로 밑줄을 제거한다 */
}


/* 모든 메뉴의 a에 속성값 부여 */
.side-bar ul > li > a {
  display: block;
  color: white;
  font-size: 1.4rem;
  font-weight: bold;
  /* padding-top: 20px; */
  /* padding-bottom: 20px; */
  /* padding-left: 10px; */
}

.side-bar ul.second > li > a {
  display: block;
  color: white;
  font-size: 1rem;
  font-weight: bold;
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 50px
}

/* 자식의 position이 absolute일 때 자식을 영역 안에 가두어 준다 */
.side-bar > ul > li {
  position: relative;
}

/* 모든 메뉴가 마우스 인식 시 반응 */
.side-bar ul.second > li:hover > a {
  background-color: #cb99c5;
  border-bottom: 1px solid rgb(170, 182, 250);
}

/* 1차 메뉴의 항목이 마우스 인식 시에 2차 메뉴 등장 */
/* .side-bar > ul > li:hover > ul {
  display: block;
  position: absolute;
  background-color: rgb(170, 182, 250);
  top: 0%;         
  left: 100%;  
  width: 100%;    
} */

</style>