<template>
  <div>
      <select class="form-select" v-model="boardId">
        <option selected>게시판을 고르세요</option>
        <option v-for="board in boardStore.boardList" :value="board.boardId" >{{board.description}}</option>
      </select>


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
import { ref, computed,onMounted } from "vue";  
import { useArticleStore } from "@/stores/article";
import { useBoardStore } from "@/stores/board";

const boardId = ref("");

const article = ref({
  boardId : boardId,
  title: "",
  content: "",
})

const boardStore = useBoardStore();
const articleStore = useArticleStore();

const writeArticle = function () {
  // console.log(article.value.boardId)
  if(confirm("게시글을 작성하시겠습니까?") === true){
    articleStore.writeArticle(article.value)
  }
  else{
    return;
  }
};

onMounted(() => {
  boardStore.getBoardList()
})
</script>

<style scoped></style>