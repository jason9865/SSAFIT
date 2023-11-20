<template>
    <hr>
    <div class="container text-center">
        <RouterLink :to="{ name: 'free' }" @click="getFree">자유게시판</RouterLink> | 
        <RouterLink :to="{ name: 'announce' }" @click="getAnnounce">공지사항</RouterLink> | 
    </div>
    <hr>
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

</style>