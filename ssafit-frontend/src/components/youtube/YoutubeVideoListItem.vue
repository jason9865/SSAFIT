<template>
  <div class="carousel-item text-center" :class="active">
      <iframe
        width="800"
        height="500"
        :src="videoURL"
        title="YouTube video player"
        frameborder="1"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        allowfullscreen
      ></iframe>
      <h5 class="text-center">{{ videoTitle }}</h5>
  </div>
</template>

<script setup>
import { computed } from "vue";
import _ from "lodash";
const props = defineProps({
  video: {
    type: Object,
    required: true,
  },
  index: Number,
  current: Number,
});
const videoURL = computed(() => {
  const videoId = props.video.id.videoId;
  return `https://www.youtube.com/embed/${videoId}`;
});

const active = computed(() => {
  if (props.current == props.index) return { active: true};
});

const videoTitle = computed(() => {
  return _.unescape(props.video.snippet.title);
});
</script>

<style scoped>
</style>
