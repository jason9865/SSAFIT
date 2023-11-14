<template>
  <div>
    <h4>게시글 상세</h4>
    <div>{{ store.article.title }}</div>
    <div>{{ store.article.nickName }}</div>
    <div>{{ store.article.createdAt }}</div>
    <div>{{ store.article.viewCnt }}</div>
    <div>{{ store.article.content }}</div>
    
    <!-- 게시글 작성자와 로그인 유저가 동일인이어야 게시글 수정/삭제 버튼이 뜬다.. -->
    <div v-if="loginUser && (loginUser.userSeq === store.article.userSeq)">
      <span> 게시글 수정</span>
      <span> 게시글 삭제</span>
    </div>

    <hr>

    <CommentList :article-id="articleId"/>
    
  </div>
</template>
  
<script setup>
import axios from 'axios';
import {ref,onMounted} from 'vue'
import {useRouter, useRoute} from 'vue-router'
import {useArticleStore} from '@/stores/article'
import CommentList from '@/components/comment/CommentList.vue';

const route = useRoute();
const router = useRouter();
const article =ref({});
const store = useArticleStore()
const articleId = ref(route.params.articleId)

// 헌데 로그아웃을 해버리면 ._value를 찾을 수 없다며 에러가 뜬다. -> 문제해결
const loginUser = ref(JSON.parse(window.localStorage.getItem("loginUser"))?._value )

onMounted(() => {
  store.getArticle(route.params.articleId)
})

</script>
  
<style scoped>
  
</style>