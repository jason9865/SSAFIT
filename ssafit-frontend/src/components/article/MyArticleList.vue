<template>
    <div class="container">
        <div style="margin-top:100px;margin-bottom:300px;">
            <h1 style="color : white; font-weight: bold; text-align: center; margin:50px;">내가 쓴 게시글 리스트</h1>
            <div v-if="myArticleList.length">
            <table class="table table-hover text-center">
                <thead>
                <tr>
                    <td>번호</td>
                    <td>제목</td>
                    <td>작성자</td>
                    <td>조회수</td>
                    <td>등록일자</td>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(article,index) in myArticleList" :key="article.articleId">
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
            <div v-else>
                <h3 style="color : white; font-weight: bold; text-align: center; margin:50px;">등록된 게시글이 없습니다.</h3>
            </div>
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
    color:black;
}
</style>