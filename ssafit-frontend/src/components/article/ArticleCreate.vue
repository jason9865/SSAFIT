<template>
  <div>
      <h4>게시글 작성</h4>
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
          <button class="btn btn-outline-primary" @click="writeArticle">등록</button>
      </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";  
import { useArticleStore } from "@/stores/article";
import { useBoardStore } from "@/stores/board";



const article = ref({
  boardId : computed(() => boardStore.board?.boardId),
  title: "",
  content: "",
})

const boardStore = useBoardStore();
const articleStore = useArticleStore();

const writeArticle = function () {
  articleStore.writeArticle(article.value)
};
</script>

<style scoped></style>