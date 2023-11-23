<template>
    <div class="row " style="font-size: large;">
        <div class="container text-center py-3 row nav nav-underline">
            <div class="col-3"></div>
            <div class="col-2 nav-item">
                <RouterLink class="nav-link" :class="free" :to="{ name: 'free' }" @click="getFree" style="color: white">자유게시판</RouterLink>
            </div>
            <div class="col-2"></div>
            <div class="col-2 nav-item">
                <RouterLink class="nav-link" :class="announce" :to="{ name: 'announce' }" @click="getAnnounce" style="color: white">공지사항</RouterLink>
            </div>
        </div>
    </div>
    <!-- <div class="container text-center py-3">
        <RouterLink :to="{ name: 'free' }" @click="getFree">자유게시판</RouterLink>
        <RouterLink :to="{ name: 'announce' }" @click="getAnnounce">공지사항</RouterLink>
    </div> -->
    <hr style="margin: 0px;">
</template>

<script setup>
import { useBoardStore } from '../../stores/board';
import { useRoute } from 'vue-router';
import { computed } from 'vue'
const route = useRoute()

const free = computed(() => {
  if (route.name === 'free') return { active: true }
})
const announce = computed(() => {
  if (route.name === 'announce') return { active: true }
})

const boardStore = useBoardStore()

const getFree = () => {
    boardStore.getArticlesByPage(1, 1)
    boardStore.boardId = 1
    boardStore.currentPage = 1
    boardStore.searchCondition = null
}

const getAnnounce = () => {
    boardStore.getArticlesByPage(1, 2)
    boardStore.boardId = 2
    boardStore.currentPage = 1
    boardStore.searchCondition = null
}
</script>

<style scoped>
a {
  text-decoration-line: none;
  color: black;
}
</style>