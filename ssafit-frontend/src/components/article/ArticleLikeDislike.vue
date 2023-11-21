<template>
    <div class="py-3">
        <!-- <p>articleId => {{ props.articleId }} {{ articleId }}</p> -->
        <button v-show="isLiked===1" class="btn btn-danger" @click="undoLike">좋아요 {{ articleLikeCount }}</button>
        <button v-show="isLiked!==1" class="btn btn-outline-danger" @click="doLike">좋아요 {{ articleLikeCount }}</button>&nbsp;
        <button v-show="isDisliked===1" class="btn btn-info" @click="undoDislike"> 싫어요 {{ articleDislikeCount }}</button>
        <button v-show="isDisliked!==1" class="btn btn-outline-info" @click="doDislike"> 싫어요 {{ articleDislikeCount }}</button>
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

function doLike () {
    console.log("좋아요 누르기")
    articleStore.doArticleLike(props.articleId)
}

function undoLike() {
    console.log("좋아요 취소")
    articleStore.undoArticleLike(props.articleId)
}

function doDislike() {
    console.log("싫어요 누르기")
    articleStore.doArticleDislike(props.articleId)
}

function undoDislike() {
    console.log("싫어요 취소")
    articleStore.undoArticleDislike(props.articleId)
}


onMounted(() => {
    // console.log("ArticleLikeDislike onMounted",props.articleId)
    articleStore.getArticleLike(props.articleId)
    articleStore.getArticleDislike(props.articleId)
    articleStore.checkLiked(props.articleId)
    articleStore.checkDisliked(props.articleId)
})


</script>

<style lang="scss" scoped>

</style>