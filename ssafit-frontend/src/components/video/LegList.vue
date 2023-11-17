<template>
  <div>
    <div class="row">
      <div class="card my-2 col-12 col-sm-6 col-md-3" v-for="(video) in currentPageVideoList" :key="video.videoId">
        <img :src="`https://img.youtube.com/vi/${video.videoId}/0.jpg`" class="card-img-top" alt="...">
        <div class="card-body">
          <RouterLink :to="`/video/${video.videoId}`">
            {{ video.title }}
          </RouterLink>
          <p class="card-text text-truncate">{{ video.description }}</p>
          <ul class="list-group list-group-flush">
            <li class="list-group-item">조회수 : {{ video.viewCnt }}</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <nav aria-label="Page navigation">
    <ul class="pagination d-flex justify-content-center">
      <li class="page-item"><a class="page-link" :class="{ disabled: currentPage <= 1 }" href="#"
          @click.prevent="currentPage--">&lt;</a></li>
      <li :class="{ active: currentPage === page+weight }" v-for="page in pagePerGroupComputed" :key="page">
        <a class="page-link" href="#" @click.prevent="clickPage(page+weight)">{{ page+weight }}</a>
      </li>
      <li class="page-item"><a class="page-link" :class="{ disabled: currentPage >= pageCount }" href="#"
          @click.prevent="currentPage++">&gt;</a></li>
    </ul>
  </nav>
</template>
  
<script setup>
import axios from 'axios';
import { ref, onMounted, computed } from 'vue';

const videoList = ref([]);

onMounted(() => {
  axios({
    url: "http://localhost:8080/video/leg",
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

const pagePerGroupComputed = computed(() => {
  if((videoList.value.length/perPage) < pagePerGroup) {
    if(videoList.value.length/perPage > 4) {
      return 5
    }
    return (Math.ceil(videoList.value.length / perPage)%5)
  } else if((videoList.value.length / perPage)%5 == 0) {
    return 5
  } else {
    return currentPage.value > Math.floor((Math.floor(videoList.value.length / perPage))/pagePerGroup)*pagePerGroup ? (Math.ceil(videoList.value.length / perPage)%5) : 5;
  }
})

const weight = computed(() => {
  return Math.floor((currentPage.value-1) / pagePerGroup)*5
})

const currentPage = ref(1)

const pageCount = computed(() => {
    return Math.ceil(videoList.value.length / perPage)
})
const clickPage = function (page) {
    currentPage.value = page
    console.log(currentPage.value)
}

const currentPageVideoList = computed(() => {
    return videoList.value.slice(
        (currentPage.value - 1) * perPage,
        currentPage.value * perPage
    )
})


</script>
  
<style scoped></style>