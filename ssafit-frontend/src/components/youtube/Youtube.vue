<template>
    <div>
        <YoutubeVideoSearch />
        <YoutubeVideoDetail/>
        <hr />
        <YoutubeVideoList />
    </div>
</template>

<script setup>
import YoutubeVideoSearch from '@/components/youtube/YoutubeVideoSearch.vue';
import YoutubeVideoList from '@/components/youtube/YoutubeVideoList.vue';
import YoutubeVideoDetail from '@/components/youtube/YoutubeVideoDetail.vue';
import { useYoutubeStore } from '@/stores/youtube'
import { onMounted } from 'vue';

const store = useYoutubeStore()

onMounted(() => {
    store.youtubeSearch("페이커" + "운동")
})

</script>

<style lang="scss" scoped>

</style>