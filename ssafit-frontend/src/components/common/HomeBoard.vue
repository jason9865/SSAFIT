<template>
    <div class="container">
        <div class="row">
            <div class="col-6">
                <div id="videoCards" class="carousel slide" data-bs-ride="carousel" style="width:600px; height:300px;">
                    <div class="d-flex justify-content-between">
                        <h2 class="mt-3 mb-3">인기 동영상</h2>
                        <div>
                            <a class="btn btn-outline-primary mt-3 mb-3" href="#videoCards" role="button"
                                style="color:white; border-color: white; font-weight:bold;" data-bs-slide="prev">
                                <b>PREV</b>
                            </a>
                            <a class="btn btn-outline-primary mt-3 mb-3" href="#videoCards" role="button"
                                style="color:white; border-color: white; font-weight:bold;" data-bs-slide="next">
                                <b>NEXT</b>
                            </a>
                        </div>
                    </div>
                    <div id="image-container" class="carousel-inner" v-for="(video, index) in videoList.slice(0, 5)"
                        :key="video.videoId">
                        <div class="carousel-item" :class="{ 'active': index === 0 }">
                            <iframe width="600" height="350" :src="`https://www.youtube.com/embed/${video.videoId}`"
                                title="YouTube video player" frameborder="1"
                                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                                allowfullscreen></iframe>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-6 mb-3">
                <h2 class="mt-3 mb-3">인기 게시글</h2>
                <div class="row" style="width:600px;height:400px;">
                    <div class="col card p-4">
                        <h4>자유게시판</h4>
                        <hr>
                        <ul v-for="free in freeList.slice(0, 5)">
                            <RouterLink :to="`/board/${free.articleId}`">
                                <li>{{ free.title }}</li>
                            </RouterLink>
                        </ul>
                    </div>
                    <div class="col card p-4">
                        <h4>공지사항</h4>
                        <hr>
                        <ul v-for="announce in announceList.slice(0, 5)">
                            <RouterLink :to="`/board/${announce.articleId}`">
                                <li>{{ announce.title }}</li>
                            </RouterLink>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted, computed } from 'vue'
import { storeToRefs } from 'pinia';
import { useBoardStore } from '@/stores/board'

const videoList = ref([]);
const boardStore = useBoardStore()
const freeList = ref([])
const announceList = ref([])

onMounted(() => {
    axios({
        url: "http://localhost:8080/video",
        method: "GET",
    })
        .then((res) => {
            videoList.value = res.data
        })
        .catch((err) => {
            console.log(err)
        })

    axios({
        url: "http://localhost:8080/board/1",
        method: "GET",
    })
        .then((res) => {
            freeList.value = res.data
        })
        .catch((err) => {
            console.log(err)
        })

    axios({
        url: "http://localhost:8080/board/2",
        method: "GET",
    })
        .then((res) => {
            announceList.value = res.data
        })
        .catch((err) => {
            console.log(err)
        })


})

</script>

<style scoped>
a {
    text-decoration-line: none;
    color: black;
    border-color: black
}

h2 {
    color: white;
    font-weight: bold;
}

h4 {
    font-weight: bold;
    text-align: center;
}
</style>