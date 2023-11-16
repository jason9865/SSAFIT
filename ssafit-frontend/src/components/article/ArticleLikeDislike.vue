<template>
    <div>
        <b>히히 좋아요 싫어요 발사</b>
        <p>articleId => {{ props.articleId }} {{ articleId }}</p>
    </div>
</template>

<script setup>
const props = defineProps({
    articleId:String
})



</script>

<style lang="scss" scoped>

</style>