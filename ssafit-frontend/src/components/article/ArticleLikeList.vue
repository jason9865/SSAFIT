<template>
    <div class="container">
        <div class="m-5">
            <h1 style="color : white; font-weight: bold; text-align: center; margin:50px;">내가 좋아요 한 게시글 리스트</h1>
            <div v-if="articleLikeList.length">
            <table class="table table-hover text-center">
                <thead>
                <tr>
                    <td>게시글ID</td>
                    <td>제목</td>
                    <td>작성자</td>
                    <td>조회수</td>
                    <td>등록일자</td>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(article,index) in articleLikeList" :key="article.articleId">
                    <td>{{ index + 1}}</td>
                    <td>
                    <RouterLink  :to="`/board/${article.articleId}`">
                        {{ article.title }}   
                    </RouterLink>
                    </td>
                    <td>{{ article.nickName }}</td>
                    <td>{{ article.viewCnt }}</td>
                    <td>{{ article.modifiedAt }}</td>
                </tr>
                </tbody>
            </table>
            </div>
            <div v-else>등록된 게시글이 없습니다.</div>
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
    color:black;
}
</style>