<template>
  <div class="container p-5">
      <select class="form-select" v-model="boardId">
        <option value="" selected>게시판 선택</option>
        <option v-for="board in boardStore.boardList" :value="board.boardId" >{{board.description}}</option>
      </select>


      <h4 style="color : white; font-weight: bold; margin-top:10px; margin-bottom: 10px;">게시글 작성</h4>
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
          <button title="Button fade BlueGreen" class="button btnFade btnBlueGreen" @click="writeArticle" style="margin-right:10px">
            등록
          </button>
          <button title="Button fade lightblue" class="button btnFade btnLightBlue" @click="goBack">취소</button>
      </div>
  </div>
</template>

<script setup>
import { ref, computed,onMounted } from "vue";  
import { useArticleStore } from "@/stores/article";
import { useBoardStore } from "@/stores/board";
import { useRouter } from "vue-router";

const boardId = ref("");
const router = useRouter()

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

const goBack = function () {
  if(confirm("게시글 작성을 취소하시겠습니까?") === true) {
    router.go(-1)
  }
}

onMounted(() => {
  boardStore.getBoardList()
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

.btnLightBlue {
background: #5DC8CD;
}

.btnFade.btnBlueGreen:hover {
background: #21825B;
}

.btnFade.btnLightBlue:hover {
background: #01939A;
}

</style>