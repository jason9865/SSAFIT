<template>
  <div class="container py-2">
    <img :src="video.snippet.thumbnails.default.url" >
    <span @click="clickVideo" style="font-size: large; padding-left: 15px; cursor: pointer;">
      {{ video.snippet.title }}, 
      {{ video.snippet.channelTitle }}
    </span>
    <p style="font-size: small;" class="">{{ video.snippet.description }}</p>
  </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube';
const store = useYoutubeStore();

const props = defineProps({
  video: {
    type: Object,
    required: true,
  },
});


const clickVideo = function() {
    store.clickVideo(props.video)
}


</script>

<style scoped></style>
