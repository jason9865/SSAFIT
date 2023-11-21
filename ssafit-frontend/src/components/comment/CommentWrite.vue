<template>
    <div>
        <b>댓글 작성</b>
        <textarea class="form-control" v-model="comment.content"></textarea>
        <button class="btn btn-success btn-sm" @click="writeComment">댓글 등록</button>
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
    if(comment.value.content == ""){
        alert("내용을 입력해주세요.")
        return ;
    }
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