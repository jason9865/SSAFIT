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
                <RouterLink :to="`/board/${article.articleId}`">
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
      <router-link :to="`/board/create`">글 작성</router-link>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const articleList = ref([]);

onMounted(() => {
    axios({
        url : `http://localhost:8080/board/2`,
        method : "GET"
    })
      .then((res) => {
        articleList.value = res.data
      })
      .catch((err) => {
            console.log(err)
      })
})
</script>

<style lang="scss" scoped>

</style>