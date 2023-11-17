<template>
    <div class="container">
      <h4>게시글 수정</h4>
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
          <button class="btn btn-success" @click="modifyArticle">게시글 수정</button>
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

const boardStore = useBoardStore()
const articleStore = useArticleStore()

const {article} = storeToRefs(articleStore)

const modifyArticle = () => {
  console.log(article.value)
  if(confirm("게시글을 수정하시겠습니까?") === true) {
    articleStore.updateArticle(article.value)
  }
  return;
}

onMounted(() => {
  articleStore.getArticle(route.params.articleId)
})

</script>

<style scoped>

</style>