<template>
    <div class="pt-2 text-center">
        <button v-show="isVideoLike!==1" class="btn btn-secondary" @click="doLike">찜하기</button>
        <button v-show="isVideoLike===1" class="btn btn-warning" @click="undoLike">찜하기 취소</button>
    </div>
</template>

<script setup>
import { storeToRefs } from 'pinia';
import { useVideoStore } from '../../stores/video';
import { onMounted,computed } from 'vue';
const videoStore = useVideoStore()
const {isVideoLike} = storeToRefs(videoStore)

const props = defineProps({
    videoId:String
})

function doLike () {
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
    videoStore.checkVideoLike(props.videoId)
})

</script>

<style scoped>

</style>