<template>
    <div class="container">
      <div v-if="articleList.length">
        <table class="table table-hover text-center">
          <thead>
            <tr>
              <td>제목</td>
              <td>작성자</td>
              <td>조회수</td>
              <td>등록일자</td>
            </tr>
          </thead>
          <tbody>
            <tr v-for="article in articleList" :key="article.articleId">
              <td>
                <RouterLink :to="`/board/${article.boardName}/${article.articleId}`">
                  {{ article.title }}
                </RouterLink>
              </td>
              <td>{{ article.nickName }}</td>
              <td>{{ article.viewCnt }}</td>
              <td>{{ article.createdAt }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-else>등록된 게시글이 없습니다.</div>
      <div v-if="boardName != null">
        <router-link :to="`/board/${boardName}/create`">글 작성</router-link>
      </div>
    </div>
</template>
  
<script setup>
import{ref, computed, onMounted, onUpdated } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';
import { useBoardStore } from '@/stores/board.js'
import { useArticleStore } from '../../stores/article';
import { storeToRefs } from 'pinia';

const boardStore = useBoardStore()
const {articleList} = storeToRefs(boardStore)

// url에 board이름을 넣어주기 위한 코드 -> 근데 근본없이 계속 바뀜 -> routeParam에 따라 변화하기 때문이다
const boardName = computed(()=>boardStore.board?.name) // 있을때만 boardName 돌려줌

onMounted(() => {
  console.log("ArticleList onMounted")
  boardStore.getArticleList(boardStore.board == null ? 1 : boardStore.board.Id)
})

</script>
  
<style scoped>
  
</style>