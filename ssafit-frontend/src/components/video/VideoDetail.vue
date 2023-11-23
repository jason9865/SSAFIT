<template>
  <div class="container text-center pt-4">
    <iframe
    width="1000"
    height="500"
    :src="videoURL"
    title="YouTube video player"
    frameborder="1"
    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
    allowfullscreen
    ></iframe>
  </div>
  <div class="container">
    <VideoLike :video-id="route.params.videoId"/>
  </div>
  <div class="container">
    <hr>
    <VideoCommentList :video-id="route.params.videoId"/>
  </div>
</template>
  
<script setup>
import {ref,onMounted} from 'vue'
import {useRouter, useRoute} from 'vue-router'
import {useVideoStore} from '@/stores/video'
import axios from 'axios';
import VideoLike from './VideoLike.vue';
import VideoCommentList from '../comment/VideoCommentList.vue';

const route = useRoute();
const store = useVideoStore()

const video = ref(null)
const videoURL = "https://www.youtube.com/embed/" + route.params.videoId

onMounted(() => {
  axios({
    url : `http://localhost:8080/video/${route.params.videoId}/detail`,
    method : "GET",
  }).then((res) => { video.value = res.data }).catch((err) => {console.log(err)})
})

</script>
  
<style scoped>
  
</style>