<template>
  <div>
    <h2>제목 : {{ store.article.title }}</h2>
    <h3>작성자 : {{ store.article.nickName }}</h3>
    <h4>최종 수정 일자 : {{ store.article.createdAt }}</h4>
    <h4>조회 수 : {{ store.article.viewCnt }}</h4>
    <p>{{ store.article.content }}</p>
    
    <ArticleLikeDislike :article-id="articleId"/>

    <!-- 게시글 작성자와 로그인 유저가 동일인이어야 게시글 수정/삭제 버튼이 뜬다.. -->
    <div class="buttons" v-if="currUserSeq && (currUserSeq === store.article.userSeq)">
      <button class="btn btn-success m-3" @click="modifyArticle"> 게시글 수정</button>
      <button class="btn btn-danger m-3" @click="deleteArticle"> 게시글 삭제</button>
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
import ArticleLikeDislike from '@/components/article/ArticleLikeDislike.vue'

const route = useRoute();
const router = useRouter();
const article =ref({});
const store = useArticleStore()
const articleId = ref(route.params.articleId)

const articleStore = useArticleStore()

// 헌데 로그아웃을 해버리면 ._value를 찾을 수 없다며 에러가 뜬다. -> 문제해결
const loginUser = ref(JSON.parse(window.localStorage.getItem("loginUser"))?._value )

// console.log("currUserSeq => ", JSON.parse(sessionStorage.getItem("userSeq")))
const currUserSeq = ref(JSON.parse(sessionStorage.getItem("userSeq")))

function modifyArticle() {
  router.push({name:"ArticleModify"})
}

function deleteArticle() {
  if (confirm("게시글을 삭제하시겠습니까?") === true){
    articleStore.deleteArticle(articleId.value)
  }
  return;
}

onMounted(() => {
  store.getArticle(route.params.articleId)
})

</script>
  
<style scoped>

</style>