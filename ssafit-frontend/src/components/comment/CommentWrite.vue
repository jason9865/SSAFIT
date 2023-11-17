<template>
    <div>
        <b>댓글 작성하기</b>
        <textarea class="form-control" v-model="comment.content"></textarea>
        <button class="btn btn-success" @click="writeComment">댓글 등록</button>
    </div>
</template>

<script setup>
import {ref} from 'vue'
import { useCommentStore } from '../../stores/comment';

const props = defineProps({
    articleId:String
})

const commentStore = useCommentStore()

const aId = props.articleId

const comment = ref({
    articleId : aId,
    content : "",
})

function writeComment() {
    console.log("WriteComment!")
    console.log("Comment의 articleId => ",comment.value.articleId)
    console.log("Comment - content => ", comment.value.content)
    if(confirm("댓글을 등록하시겠습니까?") === true){
        commentStore.createComment(comment.value);
        comment.value.content=""
    }
    else{
        return;
    }
}

</script>

<style scoped>

</style>