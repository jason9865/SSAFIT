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
      <router-link to="/board/create">글 작성</router-link>
    </div>
    <nav aria-label="Page navigation">
    <ul class="pagination d-flex justify-content-center">
      <li class="page-item"><a class="page-link" :class="{ disabled: currentPage <= 1 }" href="#"
          @click.prevent="clickPage(--boardStore.currentPage)">&lt;</a></li>
      <li :class="{ active: currentPage === page+weight }" v-for="page in pagePerGroupComputed" :key="page">
        <a class="page-link" href="#" @click.prevent="clickPage(page+weight)">{{ page+weight }}</a>
      </li>
      <li class="page-item"><a class="page-link" :class="{ disabled: currentPage >= pageCount }" href="#"
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
const pageCount = computed(() => {
  return Math.ceil(entireArticleLength.value / articlePerPage)
})

// pagination group 개수
const pagePerGroup = 5;
// 실제 쓰이는 값
const pagePerGroupComputed = computed(() => {
  if(entireArticleLength.value/articlePerPage < pagePerGroup) {     // 전체 게시글이 50개보다 작을 때,
    if(entireArticleLength.value/articlePerPage > 4) {
      return 5
    }
    return (Math.ceil(entireArticleLength.value/articlePerPage)%5)
  } else if((entireArticleLength.value / articlePerPage)%5 == 0) {    // (전체 게시글/한페이지에 출력될 게시글 수) % 5가 0일 때,
    return 5
  } else {    // 그 외
    return currentPage.value > Math.floor((Math.floor(entireArticleLength.value / articlePerPage))/pagePerGroup)*pagePerGroup ? (Math.ceil(entireArticleLength.value / articlePerPage)%5) : 5;
  }
})


// 페이지 이동 시 currentPage를 기반으로 그 페이지에 해당하는 게시글을 불러와서 articleList에 저장.
const clickPage = function (page) {
  boardStore.currentPage = page
  if(boardStore.searchCondition == null) {
    boardStore.getArticlesByPage(currentPage.value, 1)
  } else {
    boardStore.getArticlesBySearchInfoWithPage(currentPage.value)
  }
}

const API_URL = `http://localhost:8080/board/1`
// mount와 동시에 currentPage = 1로 게시글 호출해서 articleList에 저장.
onMounted(() => {
    boardStore.getArticlesByPage(1, 1)
    boardStore.boardId = 1
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

</style>