<template>
    <div class="container">
        <div class="card p-2" style="width: 18rem;">
            <h3>비디오 좋아요 리스트</h3>
            <ul class="list-group list-group-flush" v-for="video in videoLikeList" :key="video.videoId" >
                <RouterLink :to="`/video/${video.videoId}`">
                    <li class="list-group-item">{{ video.title }}</li>
                </RouterLink>
            </ul>
        </div>
    </div>
</template>

<script setup>
import {ref,onMounted} from 'vue'
import {storeToRefs} from 'pinia'
import {useVideoStore} from "../../stores/video"

const videoStore = useVideoStore()
const {videoLikeList} = storeToRefs(videoStore)
const userSeq = JSON.parse(sessionStorage.getItem("userSeq"))

onMounted(()=>{
    // console.log("userSeq => ",userSeq)
    videoStore.getVideoLikeList()
})

</script>

<style scoped>

</style>