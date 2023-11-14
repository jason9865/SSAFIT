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
        <RouterView/>
    </div>
</template>

<script setup>
import { ref,computed,onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useBoardStore } from '@/stores/board.js'

const boardStore = useBoardStore()

// const announce = () => {
//     boardStore.getArticleList(2)
// }

// const free = () => {
//     boardStore.getArticleList(1)
// }

// boardId를 넣으면 boardId에 해당하는(게시판에 속한) articleList로 갱신한다.
const getArticles = (boardId) => {
    console.log("getArticles",boardId)
    boardStore.getArticleList(boardId)
}

// useBoardStore에 있는 boardList를 caching하기 위한 코드
const boardList = computed(() => boardStore.boardList);

onMounted(() => {
    console.log("BoardHeader OnMounted");
    boardStore.getBoardList(); // 페이지가 mount될 때 getBoardList()를 통해 boardList를 갱신한다.
})

</script>

<style lang="scss" scoped>

</style>