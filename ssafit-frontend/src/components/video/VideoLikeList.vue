<template>
    <div class="container">
        <div class="m-5">
            <h1 style="color : white; font-weight: bold; text-align: center; margin:50px;">내가 좋아요 한 비디오</h1>
            <div v-if="videoLikeList.length">
            <table class="table table-hover text-center">
                <thead>
                <tr>
                    <td>번호</td>
                    <td>제목</td>
                    <td>채널명</td>
                    <td>조회수</td>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(video,index) in videoLikeList" :key="video.videoId">
                    <td>{{ index + 1}}</td>
                    <td>
                    <RouterLink  :to="`/video/${video.videoId}`">
                        {{ video.title }}   
                    </RouterLink>
                    </td>
                    <td>{{ video.channelTitle }}</td>
                    <td>{{ video.viewCnt }}</td>
                </tr>
                </tbody>
            </table>
            </div>
            <div v-else>등록된 게시글이 없습니다.</div>
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
a {
    text-decoration-line: none;
    color:black;
}
</style>