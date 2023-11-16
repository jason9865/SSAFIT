<template>
    <div>
        <button class="btn btn-outline-success"> 좋아요 {{ articleLikeCount }}</button>
        <!-- <p>articleId => {{ props.articleId }} {{ articleId }}</p> -->
        <button class="btn btn-outline-danger"> 싫어요 {{ articleDislikeCount }}</button>
    </div>
</template>

<script setup>
import { storeToRefs } from 'pinia';
import { useArticleStore } from '../../stores/article';
import { onMounted } from 'vue';
const articleStore = useArticleStore()
const {articleLikeCount,articleDislikeCount} = storeToRefs(articleStore);

const props = defineProps({
    articleId:String
})


onMounted(() => {
    console.log("ArticleLikeDislike onMounted",props.articleId)
    articleStore.getArticleLike(props.articleId)
    articleStore.getArticleDislike(props.articleId)
})


</script>

<style lang="scss" scoped>

</style>