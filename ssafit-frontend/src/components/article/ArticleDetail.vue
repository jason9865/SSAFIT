<template>
  <div class="container mt-3 p-3" style="background-color: white; border-radius: 0.3cm;">
    <h2>{{ store.article.title }}</h2>
    <p style="font-size: medium; margin: 0;" >{{ store.article.nickName }}</p>
    <span style="font-size: small; margin: 0;">{{ store.article.createdAt }} 조회 수 {{ store.article.viewCnt }}</span>
    <hr>
    <div class="px-3 py-5 my-5" style="white-space: pre-line;">{{ store.article.content }}</div>
    <ArticleLikeDislike :article-id="articleId" class="text-center"/>
  </div>
  <div>
    <!-- 게시글 작성자와 로그인 유저가 동일인이어야 게시글 수정/삭제 버튼이 뜬다.. -->
    <div class="container buttons text-center" v-if="currUserSeq && (currUserSeq === store.article.userSeq)">
      <button title="Button fade lightblue" class="button btnFade btnLightBlue" @click="modifyArticle"> 게시글 수정</button>
      <button title="Button fade red" class="button btnFade btnRed" @click="deleteArticle"> 게시글 삭제</button>
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
button.button {

width: 100px;
/* padding: 0; */
margin: 10px 10px 10px 0;
font-weight: 600;
text-align: center;
line-height: 50px;
color: #FFF;
border-radius: 5px;
transition: all 0.2s ;
text-decoration: none;
border:none
}

.btnBlueGreen {
background: #00AE68;
}

.btnLightBlue {
background: #5DC8CD;
}

.btnOrange {
background: #FFAA40;
}

.btnPurple {
background: #A74982;
}

.btnRed {
  background:#D83F31;
}

/* FADE */
.btnFade.btnBlueGreen:hover {
background: #21825B;
}

.btnFade.btnLightBlue:hover {
background: #01939A;
}

.btnFade.btnOrange:hover {
background: #FF8E00;
}

.btnFade.btnPurple:hover {
background: #6D184B;
}

.btnFade.btnRed:hover {
  background: #B31312 ;
}

</style>