<template>
    <div class="container">
      <h4 style="color : white; font-weight: bold; margin-top:20px; margin-bottom: 20px;">게시글 수정</h4>
      <div class="form-floating mb-3">
          <input type="text" class="form-control" id="title" placeholder="제목" v-model="article.title">
          <label for="title">Title</label>
      </div>
      <div class="form-floating mb-3">
          <textarea class="form-control" id="content" placeholder="내용" style="height: 200px"
              v-model="article.content"></textarea>
          <label for="content">Content</label>
      </div>
      <div class="d-flex justify-content-end">
          <button title="Button fade blue/green" class="button btnFade btnBlueGreen" @click="modifyArticle" style="margin-right:10px;">
            게시글 수정
          </button>
          <button title="Button fade red" class="button btnFade btnRed" @click="goBack">취소</button>
      </div>
    </div>
  </template>
  
<script setup>
import { ref, computed, onMounted } from "vue";  
import {useRouter, useRoute} from 'vue-router'
import { useArticleStore } from "@/stores/article";
import { useBoardStore } from "@/stores/board";
import { storeToRefs } from "pinia";
const route = useRoute()
const router = useRouter()

const boardStore = useBoardStore()
const articleStore = useArticleStore()

const {article} = storeToRefs(articleStore)

const modifyArticle = () => {
  if(confirm("게시글을 수정하시겠습니까?") === true) {
    articleStore.updateArticle(article.value)
  }
  return;
}

const goBack = function () {
  if(confirm("게시글 수정을 취소하시겠습니까?") === true) {
    router.go(-1)
  }
}


onMounted(() => {
  articleStore.getArticle(route.params.articleId)
})

</script>

<style scoped>
button.button {
  width: 100px;
  /* padding: 0; */
  margin: 10px 0px 10px 0;
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

.btnFade.btnBlueGreen:hover {
background: #21825B;
}

.btnRed {
  background:#D83F31;
}

.btnFade.btnRed:hover {
  background: #B31312 ;
}


</style>