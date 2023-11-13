<template>
    <div>
      <div v-if="articleList.length">
        <table>
          <thead>
            <tr>
              <td>제목</td>
              <td>작성자</td>
              <td>조회수</td>
              <td>등록</td>
            </tr>
          </thead>
          <tbody>
            <tr v-for="article in articleList" :key="article.articleId">
              <td>
                <RouterLink :to="`/board/${article.name}/${article.articleId}`">
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
    </div>
</template>
  
<script setup>
import{ref, computed, onMounted, onUpdated } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';
import { useBoardStore } from '@/stores/board.js'
import { isFunction } from '@vue/shared';

const boardStore = useBoardStore()
const articleList = computed(()=>boardStore.articleList)

onMounted(() => {
  console.log("onMounted")
  boardStore.getArticleList(1)
})

</script>
  
<style scoped>
  
</style>