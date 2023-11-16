<template>
  <div>
    <h2 class="text-center"><i class="bi bi-youtube" style="color: red;"></i>유튜브 영상</h2>
    <hr>
    <VideoHeader/>
    <RouterView/>
  </div>
</template>

<script setup>
import VideoHeader from '../components/video/VideoHeader.vue';
</script>

<style scoped>
</style>
