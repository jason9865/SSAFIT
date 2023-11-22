<template>
  <div class="row" style="font-size: large;">
    <div class="col-3"></div>
    <div class="container text-center py-2 row col-9 nav nav-underline" style="font-weight: bold;">
      <div class="col-1 nav-item">
        <RouterLink class="nav-link" :class="videoList" :to="{ name: 'VideoList' }" style="color: white;">전체</RouterLink>
      </div>
      <div class="col-1 nav-item">
        <RouterLink class="nav-link" :class="chestList" :to="{ name: 'ChestList' }" style="color: white;">가슴</RouterLink>
      </div>
      <div class="col-1 nav-item">
        <RouterLink class="nav-link" :class="backList" :to="{ name: 'BackList' }" style="color: white;">등</RouterLink>
      </div>
      <div class="col-1 nav-item">
        <RouterLink class="nav-link" :class="absList" :to="{ name: 'AbsList' }" style="color: white;">복부</RouterLink>
      </div>
      <div class="col-1 nav-item">
        <RouterLink class="nav-link" :class="shoulderList" :to="{ name: 'ShoulderList' }" style="color: white;">어깨</RouterLink>
      </div>
      <div class="col-1 nav-item">
        <RouterLink class="nav-link" :class="armList" :to="{ name: 'ArmList' }" style="color: white;">팔</RouterLink>
      </div>
      <div class="col-1 nav-item">
        <RouterLink class="nav-link" :class="legList" :to="{ name: 'LegList' }" style="color: white;">하체</RouterLink>
      </div>
      <div class="col-1 nav-item">
        <RouterLink  class="nav-link" :class="youtube" :to="{ name: 'Youtube' }" style="color: white;">검색</RouterLink>
      </div>
    </div>
  </div>
  <!-- <ul class="nav nav-underline">
    <li class="nav-item">
      <RouterLink class="nav-link active" :to="{ name: 'VideoList' }" style="color: white;">전체영상</RouterLink>
    </li>
  </ul> -->

  <hr style="margin: 0px;">
</template>

<script setup>
import { useRoute } from 'vue-router';
import { computed } from 'vue'
const route = useRoute()

const videoList = computed(() => {
  if (route.name === 'videoList') return { active: true }
})
const chestList = computed(() => {
  if (route.name === 'ChestList') return { active: true }
})
const backList = computed(() => {
  if (route.name === 'BackList') return { active: true }
})
const absList = computed(() => {
  if (route.name === 'AbsList') return { active: true }
})
const shoulderList = computed(() => {
  if (route.name === 'ShoulderList') return { active: true }
})
const armList = computed(() => {
  if (route.name === 'ArmList') return { active: true }
})
const legList = computed(() => {
  if (route.name === 'LegList') return { active: true }
})
const youtube = computed(() => {
  if (route.name === 'youtube') return { active: true }
})

</script>

<style scoped>
a {
  text-decoration-line: none;
  color: black;
}
</style>