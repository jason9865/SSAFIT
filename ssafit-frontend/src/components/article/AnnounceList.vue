<template>
  <div class="container">
    <BoardSearchInput/>
    <div v-if="articleList.length">
      <table class="table table-hover text-center">
        <thead>
          <tr>
            <td>게시글ID</td>
            <td>제목</td>
            <td>작성자</td>
            <td>조회수</td>
            <td>등록일자</td>
          </tr>
        </thead>
        <tbody>
          <tr v-for="article in articleList" :key="article.articleId">
            <td>{{ article.articleId }}</td>
            <td>
              <RouterLink  :to="`/board/${article.articleId}`">
                {{ article.title }}   
              </RouterLink>
            </td>
            <td>{{ article.nickName }}</td>
            <td>{{ article.viewCnt }}</td>
            <td>{{ article.modifiedAt }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-else>등록된 게시글이 없습니다.</div>
    <router-link to="/board/create" title="Button border purple" class="button btnFloat btnPurple"></router-link>
  </div>
  <nav aria-label="Page navigation">
  <ul class="pagination d-flex justify-content-center">
    <li class="page-item"><a class="page-link" :class="{ disabled: currentPage <= 1 }" href="#"
        @click.prevent="clickPage(--boardStore.currentPage)">&lt;</a></li>
    <li :class="{ active: currentPage === page+weight }" v-for="page in pagePerGroupComputed" :key="page">
      <a class="page-link" href="#" @click.prevent="clickPage(page+weight)">{{ page+weight }}</a>
    </li>
    <li class="page-item"><a class="page-link" :class="{ disabled: currentPage >= totalPageCount }" href="#"
        @click.prevent="clickPage(++boardStore.currentPage)">&gt;</a></li>
  </ul>
</nav>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';
import { useArticleStore } from '../../stores/article';
import { useBoardStore } from '../../stores/board';
import { storeToRefs } from 'pinia';
import BoardSearchInput from '../board/BoardSearchInput.vue';

const articleStore = useArticleStore()
const boardStore = useBoardStore()

// 게시판의 전체 개시글 수
const entireArticleLength = computed(() => {
return boardStore.articleListLength
});

// paging된 게시글 list
const articleList = computed(() => {
return boardStore.articleList
})

// pagination ui를 위한 변수.
const weight = computed(() => {
return Math.floor((boardStore.currentPage-1) / pagePerGroup)*5
})

// 현재 페이지
const currentPage = computed(() => {
return boardStore.currentPage
})
// 한 페이지에 출력되는 게시글의 수
const articlePerPage = 10;
// pagination 개수, ex. 게시글이 67개면 7개.
const totalPageCount = computed(() => {
return Math.ceil(entireArticleLength.value / articlePerPage)
})

// pagination group 개수
const pagePerGroup = 5;

// 현재 속한 페이지 그룹
const currentGroup = computed(() => {
return Math.floor((boardStore.currentPage - 1) / pagePerGroup) + 1
})

// 그룹의 시작 페이지 번호
const startPageNum = computed(() => {
return (currentGroup.value - 1) * pagePerGroup + 1
})

// 그룹의 끝 페이지 번호
const endPageNum = computed(() => {
return Math.min(currentGroup.value * pagePerGroup, totalPageCount.value)
})

// 실제 쓰이는 값
const pagePerGroupComputed = computed(() => {
return endPageNum.value-startPageNum.value+1
})


// 페이지 이동 시 currentPage를 기반으로 그 페이지에 해당하는 게시글을 불러와서 articleList에 저장.
const clickPage = function (page) {
boardStore.currentPage = page
if(boardStore.searchCondition == null) {
  boardStore.getArticlesByPage(currentPage.value, 2)
} else {
  boardStore.getArticlesBySearchInfoWithPage(currentPage.value)
}
}

const API_URL = `http://localhost:8080/board/2`
// mount와 동시에 currentPage = 1로 게시글 호출해서 articleList에 저장.
onMounted(() => {
  boardStore.getArticlesByPage(1, 2)
  boardStore.boardId = 2
  axios({url: API_URL, method: "GET"})
  .then((res) => {boardStore.articleListLength = res.data.length})
  .catch((err) => {console.log(err)})
})

</script>

<style scoped>
a {
  text-decoration-line: none;
  color: black;
}

a.button {
  width: 120px;
  padding: 0;
  margin: 10px 20px 10px 0;
  font-weight: 600;
  text-align: center;
  line-height: 50px;
  color: #FFF;
  border-radius: 5px;
  transition: all 0.2s ;
}

.btnFloat {
  background: none;
  box-shadow: 0px 0px 0px 0px rgba(0, 0, 0, 0.5);
}


.btnFloat:before {
  content: '글쓰기';
  display: block;
  top: 0;
  left: 0;
  width: 120px;
  height: 50px;
  border-radius: 5px;
  transition: all 0.2s ;
}
.btnPurple.btnFloat:before {
    background:#3D1766;
}

.btnFloat:before {
  box-shadow: 0px 0px 0px 0px rgba(0, 0, 0, 0.4);
}

.btnFloat:hover:before {
  margin-top: -2px;
  margin-left: 0px;
  transform: scale(1.1,1.1);
  -ms-transform: scale(1.1,1.1);
  -webkit-transform: scale(1.1,1.1);
  box-shadow: 0px 5px 5px -2px rgba(0, 0, 0, 0.25);
}

</style>