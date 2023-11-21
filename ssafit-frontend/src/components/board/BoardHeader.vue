<template>
    <div class="row " style="font-size: large;">
        <div class="container text-center py-3 row">
            <div class="col-1"></div>
            <div class="col-5">
                <RouterLink :to="{ name: 'free' }" @click="getFree" style="color: white">자유게시판</RouterLink>
            </div>
            <div class="col-5">
                <RouterLink :to="{ name: 'announce' }" @click="getAnnounce" style="color: white">공지사항</RouterLink>
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