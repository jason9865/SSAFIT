<template>
  <div>
    <div class="row">
      <div class="card my-2 col-12 col-sm-6 col-md-3" v-for="(video, index) in videoList" :key="video.videoId">
        <img :src="`https://img.youtube.com/vi/${video.videoId}/0.jpg`" class="card-img-top" alt="...">
        <div class="card-body">
          <RouterLink :to="`/video/${video.videoId}`">
            {{ video.title }}   
          </RouterLink>
          <p class="card-text text-truncate">{{ video.description }}</p>
          <ul class="list-group list-group-flush">
            <li class="list-group-item">부위 : 팔</li>
            <li class="list-group-item">조회수 : {{ video.viewCnt }}</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';

const videoList = ref([]);

onMounted(() => {
  axios({
    url : "http://localhost:8080/video/arm",
    method : "GET", 
  })
    .then((res) => {
      videoList.value = res.data
    })
    .catch((err) => {
      console.log(err)
    })
})
</script>
  
<style scoped>
  
</style>