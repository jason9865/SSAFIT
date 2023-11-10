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
import{ref, onMounted} from 'vue';
import axios from 'axios';
const articleList = ref([]);


onMounted(() => {
    console.log("페이지가 로드 되었나요??")
    axios({
        url : "http://localhost:8080/board/1",
        method : "GET"
    })
      .then((res) => {
        console.log(res.data)
        articleList.value = res.data
      })
      .catch((err) => {
        console.log(err)
        alert("서버 에러")
      })
})

</script>
  
<style scoped>
  
</style>