<template>
    <div>
        <div v-show="!modifyMode">
            <h4>{{ comment.userId }} - {{ comment.nickName }}</h4>
            <div>댓글 내용 : {{ comment.content  }}</div>
            <div> 작성날짜 : {{ comment.createdAt }}</div>
            <button class="btn btn-primary" @click="modifyModeChange">댓글 수정</button>
            <button class="btn btn-danger" @click="deleteComment(comment.commentId)">댓글 삭제</button>
            <hr>
        </div>
        <div v-show="modifyMode">
            <textarea class="form-control" v-model="comment.content"></textarea>
            <button class="btn btn-success" @click="modifyComment(comment.commentId,comment.content)">댓글 수정하기</button>    
            <button class="btn btn-outline-danger" @click="modifyModeChange">댓글 수정 취소</button>
            <hr>
        </div>
    </div>
</template>

<script setup>
import {ref} from 'vue'
import {useCommentStore} from '../../stores/comment'

const props = defineProps({
    comment:Object,
    articleId : String
})

const modifyMode = ref(false)
const commentStore = useCommentStore()

function modifyModeChange() {
    modifyMode.value = !modifyMode.value
}

function modifyComment(commentId,content) {
    console.log("히히 댓글 수정 발사")
    let updatedComment = {
        commentId : commentId,
        content : content
    }
    console.log("commentId -> ",commentId)
    console.log("바뀐 내용 ->",content)
    commentStore.updateComment(updatedComment,props.articleId)
    modifyModeChange()
}

function deleteComment(commentId) {
    commentStore.deleteComment(commentId,props.articleId)
  }

</script>

<style lang="scss" scoped>

</style>