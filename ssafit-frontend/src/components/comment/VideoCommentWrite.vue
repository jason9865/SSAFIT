<template>
    <div>  
        <b>댓글 작성</b>
        <textarea class="form-control" v-model="videoComment.content"></textarea>
        <button class="btn btn-success btn-sm" @click="writeVideoComment">댓글 등록</button>
    </div>
</template>

<script setup>
import {ref} from 'vue'
import { useVideoCommentStore } from '../../stores/videoComment';

const props = defineProps({
    videoId : String
})

const videoCommentStore = useVideoCommentStore()

const videoComment = ref({
    videoId : props.videoId,
    content : "",
})

function writeVideoComment() {
    if(confirm("비디오 댓글을 등록하시겠습니까?") === true){
        videoCommentStore.writeVideoComment(videoComment.value);
        videoComment.value.content=""
    }
}

</script>

<style scoped>

</style>