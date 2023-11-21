<template>
    <hr style="margin: 0px;">
    <div class="container text-center py-3">
        <RouterLink :to="{ name: 'free' }" @click="getFree">자유게시판</RouterLink> | 
        <RouterLink :to="{ name: 'announce' }" @click="getAnnounce">공지사항</RouterLink> | 
    </div>
    <hr style="margin: 0px;">
</template>

<script setup>
import { useBoardStore } from '../../stores/board';

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