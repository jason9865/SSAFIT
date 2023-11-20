<template>
    <div class="container">
      <h3>댓글 리스트</h3>
      <hr>
      <div v-for="comment in commentList" :key="comment.commentId">
        <CommentListItem :comment="comment" :article-id="articleId"/>
      </div>
      <CommentWrite :article-id = "articleId"/>
      <hr>
    </div>
  </template>
  
  <script setup>
  import {ref,computed, onMounted} from 'vue';
  import {useCommentStore} from '../../stores/comment'
  import { useArticleStore } from '../../stores/article';
  import { storeToRefs } from 'pinia';
  import CommentWrite from './CommentWrite.vue';
  import CommentListItem from "./CommentListItem.vue"


  const props = defineProps({
    articleId : String
  })

  const commentStore = useCommentStore()
  const articleStore = useArticleStore()

  const {commentList} = storeToRefs(commentStore)


  onMounted(() => {
    commentStore.getCommentList(props.articleId)
  })

  </script>
  
  <style scoped>
  
  </style>