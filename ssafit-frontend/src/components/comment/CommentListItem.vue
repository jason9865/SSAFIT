<template>
    <div>
        <div v-show="!modifyMode">
            <h4>{{ comment.userId }} - {{ comment.nickName }}</h4>
            <div>댓글 내용 : {{ comment.content  }}</div>
            <div> 작성날짜 : {{ comment.createdAt }}</div>
            <div v-show="currUserSeq === comment.userSeq">
                <button class="btn btn-primary" @click="modifyModeChange">댓글 수정</button>
                <button class="btn btn-danger" @click="deleteComment(comment.commentId)">댓글 삭제</button>
            </div>
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

const currUserSeq = ref(JSON.parse(sessionStorage.getItem("userSeq")))
const modifyMode = ref(false)
const commentStore = useCommentStore()

function modifyModeChange() {
    modifyMode.value = !modifyMode.value
}

function modifyComment(commentId,content) {
    let updatedComment = {
        commentId : commentId,
        content : content
    }
    if(confirm("댓글을 수정하시겠습니까?") === true){
        commentStore.updateComment(updatedComment,props.articleId)
        modifyModeChange()
    }
    else{
        return;
    }
}

function deleteComment(commentId) {
    if (confirm("댓글을 삭제하시겠습니까") === true){
        commentStore.deleteComment(commentId,props.articleId)
        alert("댓글 삭제 완료")
    }
    else{
        return;
    }
  }

</script>

<style lang="scss" scoped>

</style>