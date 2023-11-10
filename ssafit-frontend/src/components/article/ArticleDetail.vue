<template>
  <div>
    <h4>게시글 상세</h4>
    <div>{{ store.article.title }}</div>
    <div>{{ store.article.nickName }}</div>
    <div>{{ store.article.createdAt }}</div>
    <div>{{ store.article.viewCnt }}</div>
    <div>{{ store.article.content }}</div>
  </div>
</template>
  
<script setup>
import axios from 'axios';
import {ref,onMounted} from 'vue'
import {useRouter, useRoute} from 'vue-router'
import {useArticleStore} from '@/stores/article'

const route = useRoute();
const router = useRouter();
const article =ref({});
const store = useArticleStore()

onMounted(() => {
  store.getArticle(route.params.articleId)
})

</script>
  
<style scoped>
  
</style>