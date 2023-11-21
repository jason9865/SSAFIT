<template>
    <div class="card p-2">
        <div v-show="!modifyMode">
            <div style="display:flex; justify-content: space;">
                <h4>{{ videoComment.nickName }}</h4>
                <FollowItem :user-seq="videoComment.userSeq" />
            </div>
            <div>댓글 내용 : {{ videoComment.content  }}</div>
            <div> 작성날짜 : {{ videoComment.createdAt }}</div>
            <div v-show="currUserSeq === videoComment.userSeq">
                <button class="btn btn-primary" @click="modifyModeChange">댓글 수정</button>
                <button class="btn btn-danger" @click="deleteComment(videoComment.videoCommentId)">댓글 삭제</button>
            </div>
        </div>
        <div v-show="modifyMode">
            <textarea class="form-control" v-model="videoComment.content"></textarea>
            <button class="btn btn-success" @click="modifyComment(videoComment.videoCommentId,videoComment.content)">댓글 수정하기</button>    
            <button class="btn btn-outline-danger" @click="modifyModeChange">댓글 수정 취소</button>
        </div>
    </div>
</template>

<script setup>
import {ref} from 'vue'
import { useVideoCommentStore } from '../../stores/videoComment';
import FollowItem from '../follow/FollowItem.vue';

const props = defineProps({
    videoComment : Object,
    videoId : String,
})

const currUserSeq = ref(JSON.parse(sessionStorage.getItem("userSeq")))
const modifyMode = ref(false)
const videoCommentStore = useVideoCommentStore()

function modifyModeChange() {
    modifyMode.value = !modifyMode.value
}

function modifyComment(videoCommentId,content) {
    let updatedVideoComment = {
        videoCommentId : videoCommentId,
        content : content,
        videoId : props.videoId
    }
    if(confirm("비디오 댓글을 수정하시겠습니까?") === true){
        console.log(updatedVideoComment.videoCommentId)
        videoCommentStore.updateVideoComment(updatedVideoComment)
        modifyModeChange()
    }

}

function deleteComment(videoCommentId) {
    if (confirm("댓글을 삭제하시겠습니까") === true){
        videoCommentStore.deleteVideoComment(videoCommentId,props.videoId)
    }
    else{
        return;
    }
  }



</script>

<style scoped>

</style>