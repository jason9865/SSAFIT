<template>
    <div>
        <RouterLink to="/board/announce" @click="announce">공지사항</RouterLink> |
        <RouterLink to="/board/free" @click="free">자유게시판</RouterLink>
        <RouterView/>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useBoardStore } from '@/stores/board.js'

const boardStore = useBoardStore()

const announce = () => {
    boardStore.getArticleList(2)
}

const free = () => {
    boardStore.getArticleList(1)
}

</script>

<style lang="scss" scoped>

</style>