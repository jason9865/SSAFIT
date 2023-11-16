<template>
    <div>
        <h4>{{ isDisliked + 1 }}</h4>
        <button class="btn btn-outline-success"> 좋아요 {{ articleLikeCount }}</button>
        <!-- <p>articleId => {{ props.articleId }} {{ articleId }}</p> -->
        <button class="btn btn-outline-danger"> 싫어요 {{ articleDislikeCount }}</button>
    </div>
</template>

<script setup>
import { storeToRefs } from 'pinia';
import { useArticleStore } from '../../stores/article';
import { onMounted,computed } from 'vue';
const articleStore = useArticleStore()
const {articleLikeCount,articleDislikeCount,isLiked,isDisliked} = storeToRefs(articleStore);

const props = defineProps({
    articleId:String
})

console.log("좋아요 눌렀니?",isLiked === 1?true:false)
console.log("싫어요 눌렀니",isDisliked === 1?true:false)



onMounted(() => {
    console.log("ArticleLikeDislike onMounted",props.articleId)
    articleStore.getArticleLike(props.articleId)
    articleStore.getArticleDislike(props.articleId)
    articleStore.checkLiked(props.articleId)
    articleStore.checkDisliked(props.articleId)
})


</script>

<style lang="scss" scoped>

</style>