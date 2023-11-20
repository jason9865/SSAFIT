<template>
    <div class="container">
        <div class="card p-2" style="width: 18rem;">
            <h3>내가 작성한 게시글 리스트</h3>
            <ul class="list-group list-group-flush" v-for="article in myArticleList" :key="article.articleId" >
                <RouterLink :to="`/board/${article.articleId}`">
                    <li class="list-group-item">{{ article.title }}</li>
                </RouterLink>
            </ul>
        </div>

    </div>
</template>

<script setup>
import {onMounted} from 'vue'
import { storeToRefs } from 'pinia';
import {useArticleStore} from '@/stores/article'

const articleStore = useArticleStore()
const {myArticleList} = storeToRefs(articleStore)

onMounted(() => {
    articleStore.getMyArticleList()
})

</script>

<style scoped>
a {
    text-decoration-line: none;
}
</style>