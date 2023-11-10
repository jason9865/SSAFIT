<template>
    <div>
        <div v-for="article in articleList" key="article.articleId">
            {{ article.title }}
        </div>
    </div>
</template>
  
<script setup>
import axios from 'axios';
import{ref, onMounted} from 'vue';
const articleList = ref([])

onMounted(() => {
    axios({
        url : "http://localhost:8080/board/1",
        method : "GET"
    })
      .then((res) => {
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