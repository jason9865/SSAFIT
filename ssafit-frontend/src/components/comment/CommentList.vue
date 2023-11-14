<template>
    <div>
      <h3>댓글 리스트</h3>
      <!-- <h3>id : {{ props.articleId }}</h3> -->
      <hr>
      <div v-for="comment in commentList" :key="comment.commentId">
        <h4>{{ comment.userId }} - {{ comment.nickName }}</h4>
        <div>댓글 내용 : {{ comment.content  }}</div>
        <div> 작성날짜 : {{ comment.createdAt }}</div>
        <hr>
      </div>
    </div>
  </template>
  
  <script setup>
  import {ref,computed, onMounted} from 'vue';
  import {useCommentStore} from '../../stores/comment'
  import { useArticleStore } from '../../stores/article';

  const props = defineProps({
    articleId : String
  })

  const commentStore = useCommentStore()
  const articleStore = useArticleStore()

  const commentList = computed(() => commentStore.commentList)

  onMounted(() => {
    console.log("CommentList OnMounted");
    console.log("ArticleId => ",props.articleId)
    commentStore.getCommentList(props.articleId)
  })

  </script>
  
  <style scoped>
  
  </style>