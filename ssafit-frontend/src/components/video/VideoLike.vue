<template>
    <div class="pt-2 text-center">
        <button v-show="isVideoLike!==1" title="Button border yellow" class="button btnFade btnYellow" @click="doLike">찜하기</button>
        <button v-show="isVideoLike===1" title="Button border orange" class="button btnFade btnOrange" @click="undoLike">찜하기 취소</button>
    </div>
</template>

<script setup>
import { storeToRefs } from 'pinia';
import { useVideoStore } from '../../stores/video';
import { onMounted,computed } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter()

const videoStore = useVideoStore()
const {isVideoLike} = storeToRefs(videoStore)

const props = defineProps({
    videoId:String
})

const currUserSeq = JSON.parse(sessionStorage.getItem("userSeq"))

function doLike () {
    if (!currUserSeq) {
        if (confirm("로그인이 필요한 서비스입니다. 로그인하시겠습니까?") === true) {
            router.push("/login")
        }
        return;
    }
    if(confirm("해당 비디오를 찜하시겠습니까?") === true){
        console.log("찜하기 누르기")
        videoStore.doVideoLike(props.videoId)
    }
}

function undoLike() {
    if(confirm("해당 비디오 찜하기를 취소하시겠습니까?") === true) {
        console.log("찜하기 취소")
        videoStore.undoVideoLike(props.videoId)
    }
}
onMounted(() => {
    if (currUserSeq){
      videoStore.checkVideoLike(props.videoId)
    }
})

</script>

<style scoped>

button.button {
width: 100px;
/* padding: 0; */
margin: 5px 5px 5px 0;
font-weight: 600;
text-align: center;
line-height: 50px;
color: #FFF;
border-radius: 5px;
transition: all 0.2s ;
text-decoration: none;
border:none
}

.btnYellow {
  background: #FFB84C;
}

.btnOrange {
  background: #FFAA40;
}

.btnFade.btnYellow:hover {
    background: #FFAA40;
}

.btnFade.btnOrange:hover {
    background: #FFB84C;
}

.btnFloat {
  background: none;
  box-shadow: 0px 0px 0px 0px rgba(0, 0, 0, 0.5);
}


.btnFloat:before {
  content: '찜하기';
  display: block;
  top: 0;
  left: 0;
  width: 120px;
  height: 50px;
  border-radius: 5px;
  transition: all 0.2s ;
}
.btnYellow.btnFloat:before {
    background:#F7D060;
}
.btnOrange.btnFloat:before {
  background: #FFAA40;
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


</style>