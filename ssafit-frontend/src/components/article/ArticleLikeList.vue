<template>
    <div class="container">
        <div class="card p-2" style="width: 18rem;">
            <h3>게시글 좋아요 리스트</h3>
            <ul class="list-group list-group-flush" v-for="article in articleLikeList" :key="article.articleId" >
                <RouterLink :to="`/board/${article.articleId}`">
                    <li class="list-group-item">{{ article.title }}</li>
                </RouterLink>
            </ul>
        </div>
    </div>
</template>

<script setup>
import { ref,onMounted } from 'vue';
import { storeToRefs } from 'pinia';
import {useArticleStore} from '@/stores/article'

const articleStore = useArticleStore()
const {articleLikeList} = storeToRefs(articleStore);

onMounted(() => {
    // console.log("히히 게시글 좋아요 리스트 발사")
    articleStore.getArticleLikeList()
})
</script>

<style scoped>
a {
    text-decoration-line: none;
}
</style>