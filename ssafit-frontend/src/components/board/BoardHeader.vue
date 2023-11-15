<template>
    <div>
        <!-- boardList에 있는 모든 board들을 순회한다.-->
        <span v-for="board in boardList" :key="board.boardId">
            <RouterLink :to="`/board/${board.name}`" @click="getArticles(board.boardId)">
                {{ board.description }}
            </RouterLink> | 
        </span>
        <!-- <RouterLink to="/board/announce" @click="announce">공지사항</RouterLink> |
        <RouterLink to="/board/free" @click="free">자유게시판</RouterLink> -->
    </div>
</template>

<script setup>
import { ref,computed,onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useBoardStore } from '@/stores/board.js'

const boardStore = useBoardStore()

// const announce = () => {
//     boardStore.getArticleList(2)
//     boardStore.getBoard(2)
// }

// const free = () => {
//     boardStore.getArticleList(1)
//     boardStore.getBoard(1)
// }

// boardId를 넣으면 boardId에 해당하는(게시판에 속한) articleList로 갱신한다.
const getArticles = (boardId) => {
    boardStore.getArticleList(boardId)
    boardStore.getBoard(boardId)
}

// useBoardStore에 있는 boardList를 caching하기 위한 코드
const boardList = computed(() => JSON.parse(localStorage.getItem("boardList")))

</script>

<style lang="scss" scoped>

</style>