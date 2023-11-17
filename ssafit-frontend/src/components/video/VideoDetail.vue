<template>
  <div class="container">
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
</template>
  
<script setup>
import {ref,onMounted} from 'vue'
import {useRouter, useRoute} from 'vue-router'
import {useVideoStore} from '@/stores/video'
import axios from 'axios';

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