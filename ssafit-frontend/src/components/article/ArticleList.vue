<template>
    <div>
        <div v-for="article in articleList" key="article.articleId">
            {{ article.title }}
        </div>
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