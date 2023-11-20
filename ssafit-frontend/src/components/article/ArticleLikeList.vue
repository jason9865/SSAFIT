<template>
    <div>
        <h3>히히 게시글 좋아요 리스트 발사</h3>
    </div>
</template>

<script setup>
import { onMounted } from 'vue';
import {useArticleStore} from '../../stores/article'
const articleStore = useArticleStore()

const userSeq = JSON.parse(sessionStorage.getItem("userSeq"))


</script>

<style scoped>

</style>