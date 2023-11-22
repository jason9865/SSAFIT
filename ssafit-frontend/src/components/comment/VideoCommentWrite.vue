<template>
    <div class="form-control">  
        <b>댓글 작성</b>
        <textarea class="form-control" v-model="videoComment.content"></textarea>
        <button title="Button fade green" class="button btnFade btnGreen" @click="writeVideoComment">댓글 등록</button>
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
button.button {
width: 100px;
/* padding: 0; */
margin: 10px 10px 10px 0;
font-weight: 600;
text-align: center;
line-height: 50px;
color: #FFF;
border-radius: 5px;
transition: all 0.2s ;
text-decoration: none;
border:none
}

.btnGreen {
background: #219C90;
}

.btnFade.btnGreen:hover {
background: #557C55;
}

</style>