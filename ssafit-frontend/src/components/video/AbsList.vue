<template>
  <div class="row">
    <div class="col-3"></div>
    <div class="row col-7">
      <div class="card col-5 p-3 bg-body-tertiary m-3"
          v-for="(video) in currentPageVideoList" :key="video.videoId">
        <img :src="`https://img.youtube.com/vi/${video.videoId}/0.jpg`" class="card-img-top" alt="...">
        <div class="card-body">
          <RouterLink :to="`/video/${video.videoId}`" class="link-secondary" style="font-size: large;">
            {{ video.title }}
          </RouterLink>
          <p>-----------------------------------------------------------</p>
          <p class="card-text text-truncate">{{ video.description }}</p>
          <p class="card-text"> {{ video.channelTitle }}</p>
          <p class="card-text">조회수 {{ video.viewCnt }} 회</p>
        </div>
      </div>
    </div>
  </div>
  <nav aria-label="Page navigation">
    <ul class="pagination d-flex justify-content-center">
      <li class="page-item"><a class="page-link" :class="{ disabled: currentPage <= 1 }" href="#"
          @click.prevent="clickPage(--currentPage)">&lt;</a></li>
      <li :class="{ active: currentPage === page+weight }" v-for="page in pagePerGroupComputed" :key="page">
        <a class="page-link" href="#" @click.prevent="clickPage(page+weight)">{{ page+weight }}</a>
      </li>
      <li class="page-item"><a class="page-link" :class="{ disabled: currentPage >= totalPageCount }" href="#"
          @click.prevent="clickPage(++currentPage)">&gt;</a></li>
    </ul>
  </nav>
</template>
  
<script setup>
import axios from 'axios';
import { ref, onMounted, computed } from 'vue';

const videoList = ref([]);

onMounted(() => {
  axios({
    url: "http://localhost:8080/video/abs",
    method: "GET",
  })
    .then((res) => {
      videoList.value = res.data
    })
    .catch((err) => {
      console.log(err)
    })
})

const perPage = 4;
const pagePerGroup = 5;
const currentPage = ref(1)

const currentGroup = computed(() => {
  return Math.floor((currentPage.value - 1) / pagePerGroup) + 1
})

const startPageNum = computed(() => {
  return (currentGroup.value - 1) * pagePerGroup + 1
})

const endPageNum = computed(() => {
  return Math.min(currentGroup.value * pagePerGroup, totalPageCount.value)
})

const pagePerGroupComputed = computed(() => {
  return endPageNum.value-startPageNum.value+1
})

const weight = computed(() => {
  return Math.floor((currentPage.value-1) / pagePerGroup)*5
})

const totalPageCount = computed(() => {
    return Math.ceil(videoList.value.length / perPage)
})
const clickPage = function (page) {
    currentPage.value = page
}

const currentPageVideoList = computed(() => {
    return videoList.value.slice(
        (currentPage.value - 1) * perPage,
        currentPage.value * perPage
    )
})


</script>
  
<style scoped></style>