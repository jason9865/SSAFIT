<template>
    <div class="container" style="background-color: white;">
        <YoutubeVideoListItem v-for="video in store.videos" :key="video.id.videoId" :video="video" />
    </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube'
import YoutubeVideoListItem from './YoutubeVideoListItem.vue';

const store = useYoutubeStore()
</script>

<style  scoped></style>