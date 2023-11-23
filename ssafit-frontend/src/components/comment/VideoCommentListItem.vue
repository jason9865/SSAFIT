<template>
    <div class="card p-2">
        <div v-show="!modifyMode">
            <div style="display:flex; justify-content: space;">
                <FollowItem :user-seq="videoComment.userSeq" :nick-name="videoComment.nickName" style="cursor:pointer">
                    {{ videoComment.nickName }}
                </FollowItem>
            </div>
            <p style="font-size: medium; margin: 0; padding: 5px">{{ videoComment.content  }}</p>
            <p style="font-size: small; margin: 0; padding: 5px">{{ videoComment.createdAt }}</p>
            <div v-show="currUserSeq === videoComment.userSeq" style="padding:5px;">
                <button title="Button fade blue/green" class="button btnFade btnBlueGreen" @click="modifyModeChange">수정</button>
                <button title="Button fade orange" class="button btnFade btnRed" @click="deleteComment(videoComment.videoCommentId)">삭제</button>
            </div>
        </div>
        <div v-show="modifyMode" style="padding:5px;">
            <textarea class="form-control" v-model="videoComment.content"></textarea>
            <button title="Button fade lightblue" class="button btnFade btnLightBlue" @click="modifyComment(videoComment.videoCommentId,videoComment.content)">수정</button>    
            <button title="Button fade orange" class="button btnFade btnRed" @click="modifyModeChange">취소</button>
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
button.button {

width: 50px;
/* padding: 0; */
margin: 5px 5px 5px 0;
font-weight: 600;
text-align: center;
/* line-height: 50px; */
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

.btnOrange {
background: #FFAA40;
}

.btnPurple {
background: #A74982;
}

.btnRed{
    background: #B70404;
}

.btnYellow.btnFloat:before {
  background: #8D336A;
}


.btnFloat:before {
  box-shadow: 0px 0px 0px 0px rgba(0, 0, 0, 0.4);
}


.btnFloat:hover:before {
  margin-top: -2px;
  margin-left: 0px;
  transform: scale(1.1,1.1);
  -ms-transform: scale(1.1,1.1);
  -webkit-transform: scale(1.1,1.1);
  box-shadow: 0px 5px 5px -2px rgba(0, 0, 0, 0.25);
}
/* FADE */
.btnFade.btnBlueGreen:hover {
background: #21825B;
}

.btnFade.btnLightBlue:hover {
background: #01939A;
}

.btnFade.btnOrange:hover {
background: #FF8E00;
}

.btnFade.btnPurple:hover {
background: #6D184B;
}

.btnFade.btnRed:hover{
    background: #CD1818;
}

</style>